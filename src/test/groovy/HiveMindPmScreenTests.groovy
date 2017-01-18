/*
 * This software is in the public domain under CC0 1.0 Universal plus a
 * Grant of Patent License.
 *
 * To the extent possible under law, the author(s) have dedicated all
 * copyright and related and neighboring rights to this software to the
 * public domain worldwide. This software is distributed without any
 * warranty.
 *
 * You should have received a copy of the CC0 Public Domain Dedication
 * along with this software (see the LICENSE.md file). If not, see
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
 */

import org.moqui.Moqui
import org.moqui.context.ExecutionContext
import org.moqui.screen.ScreenTest
import org.moqui.screen.ScreenTest.ScreenTestRender
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class HiveMindPmScreenTests extends Specification {
    protected final static Logger logger = LoggerFactory.getLogger(HiveMindPmScreenTests.class)

    @Shared
    ExecutionContext ec
    @Shared
    ScreenTest screenTest
    @Shared
    long effectiveTime = System.currentTimeMillis()

    def setupSpec() {
        ec = Moqui.getExecutionContext()
        // this is the user created in WorkPlanToCashBasicFlow
        ec.user.loginUser("worker", "moqui1!")
        screenTest = ec.screen.makeTest().baseScreenPath("apps/hm")

        ec.entity.tempSetSequencedIdPrimary("mantle.party.communication.CommunicationEvent", 62200, 10)
        ec.entity.tempSetSequencedIdPrimary("mantle.work.effort.WorkEffort", 62200, 10)
    }

    def cleanupSpec() {
        long totalTime = System.currentTimeMillis() - screenTest.startTime
        logger.info("Rendered ${screenTest.renderCount} screens (${screenTest.errorCount} errors) in ${ec.l10n.format(totalTime/1000, "0.000")}s, output ${ec.l10n.format(screenTest.renderTotalChars/1000, "#,##0")}k chars")

        ec.entity.tempResetSequencedIdPrimary("mantle.party.communication.CommunicationEvent")
        ec.entity.tempResetSequencedIdPrimary("mantle.work.effort.WorkEffort")
        ec.destroy()
    }

    def setup() {
        ec.artifactExecution.disableAuthz()
    }

    def cleanup() {
        ec.artifactExecution.enableAuthz()
    }

    def "render HiveMind PM screens with no required parameters"() {
        when:
        Set<String> screensToSkip = new HashSet(['wiki', 'EditWikiPage', 'WikiCommentNested', 'WikiCommentReply'])
        List<String> screenPaths = screenTest.getNoRequiredParameterPaths(screensToSkip)
        for (String screenPath in screenPaths) {
            ScreenTestRender str = screenTest.render(screenPath, [lastStandalone:"-2"], null)
            logger.info("Rendered ${screenPath} in ${str.getRenderTime()}ms, ${str.output?.length()} characters")
        }

        then:
        screenTest.errorCount == 0
    }

    @Unroll
    def "render HiveMind PM screen (#screenPath, #containsTextList)"() {
        setup:
        ScreenTestRender str = screenTest.render(screenPath, [lastStandalone:"-2"], null)
        // logger.info("Rendered ${screenPath} in ${str.getRenderTime()}ms, output:\n${str.output}")
        boolean containsAll = true
        for (String containsText in containsTextList) {
            boolean contains = containsText ? str.assertContains(containsText) : true
            if (!contains) {
                logger.info("In ${screenPath} text [${containsText}] not found:\n${str.output}")
                containsAll = false
            }

        }

        expect:
        !str.errorMessages
        containsAll

        where:
        screenPath | containsTextList

        // Search
        "search?documentType=HmTask&queryString=dash*" | ['HM-004', 'Dashboard My Tasks', 'Task Summary']

        // Project
        "Project/ProjectSummary?workEffortId=HM" | ['HiveMind PM Build Out', 'HM Milestone 1', 'john.doe']
        "Project/MilestoneSummary?milestoneWorkEffortId=HM-MS-001" | ['HM-004', 'Dashboard My Tasks', 'New Feature']

        // Task
        "Task/TaskSummary?workEffortId=HM-004" | ['HM-MS-001', 'In Progress', 'HM-005', 'DEMO_001',
                '/Demo Page 2/Child Page 1', 'John Doe', 'also display remaining']
        "Task/TaskSummary/TaskCommentReply?workEffortId=HM-004&parentCommEventId=HM-004-01" |
                ['Re: Remaining hours question']
        "Task/EditTask?workEffortId=HM-004" | ['For each task include a link', 'Dashboard My Tasks']
        "Task/EditTimeEntries?workEffortId=HM-004" | ['4.25', '8.00', 'Create Task and My Tasks first pass']
        "Task/EditUsers?workEffortId=HM-004" | ['john.doe', 'Ziziwork Dev Team A']
        "Task/EditRelated?workEffortId=HM-004" | ['HM-010', 'HM-005', 'Dashboard Create Task']
        "Task/EditRequests?workEffortId=HM-004" | ['DEMO_001', 'Add Create Request', 'New Feature']
        "Task/EditWikiPages?workEffortId=HM-004" | ['DEMO', 'Demo Page 2/Child Page 1']

        // Request
        "Request/EditRequest?requestId=DEMO_001" | ['Add Create Request', 'New Feature']
        "Request/EditUsers?requestId=DEMO_001" | ['John', 'Assignee']
        "Request/EditTasks?requestId=DEMO_001" | ['HM-004', 'Dashboard My Tasks', 'New Feature']
        "Request/EditWikiPages?requestId=DEMO_001" | ['Add Wiki Page']

        // wiki
        "wiki/DEMO" | ['Move along, nothing to see here', 'john.doe']
        "wiki/DEMO/Demo+Page+1" | ['Demo Page 1', 'Section 1']
        "EditWikiPage?wikiSpaceId=DEMO" | ['## Demo Space Root Page']
        "EditWikiPage?wikiSpaceId=DEMO&pagePath=Demo+Page+1" | ['## Demo Page 1']
    }
}
