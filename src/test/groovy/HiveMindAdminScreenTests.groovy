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

class HiveMindAdminScreenTests extends Specification {
    protected final static Logger logger = LoggerFactory.getLogger(HiveMindAdminScreenTests.class)

    @Shared
    ExecutionContext ec
    @Shared
    ScreenTest screenTest
    @Shared
    long effectiveTime = System.currentTimeMillis()

    def setupSpec() {
        ec = Moqui.getExecutionContext()
        // this is the user created in WorkPlanToCashBasicFlow
        ec.user.loginUser("worker", "moqui1!", null)
        screenTest = ec.screen.makeTest().baseScreenPath("apps/hmadmin")

        ec.entity.tempSetSequencedIdPrimary("mantle.party.communication.CommunicationEvent", 62100, 10)
        ec.entity.tempSetSequencedIdPrimary("mantle.work.effort.WorkEffort", 62100, 10)
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

    def "render HiveMind screens with no required parameters"() {
        when:
        Set<String> screensToSkip = new HashSet()
        List<String> screenPaths = screenTest.getNoRequiredParameterPaths(screensToSkip)
        for (String screenPath in screenPaths) {
            ScreenTestRender str = screenTest.render(screenPath, null, null)
            logger.info("Rendered ${screenPath} in ${str.getRenderTime()}ms, ${str.output?.length()} characters")
        }

        then:
        screenTest.errorCount == 0
    }

    @Unroll
    def "render HiveMind screen (#screenPath, #containsTextList)"() {
        expect:
        ScreenTestRender str = screenTest.render(screenPath, null, null)
        // logger.info("Rendered ${screenPath} in ${str.getRenderTime()}ms, output:\n${str.output}")
        boolean containsAll = true
        for (String containsText in containsTextList) {
            boolean contains = containsText ? str.assertContains(containsText) : true
            if (!contains) {
                logger.info("In ${screenPath} text [${containsText}] not found:\n${str.output}")
                containsAll = false
            }

        }

        // assertions
        !str.errorMessages
        containsAll

        where:
        screenPath | containsTextList
        "Accounting/Invoice/FindInvoice?statusId_op=in&statusId=InvoiceReceived,InvoiceApproved&toPartyId=ORG_ZIZI_RETAIL" |
                ['Ziddleman Incorporated', 'Ziziwork Retail', 'Sales/Purchase']
        "Accounting/Invoice/EditInvoice?invoiceId=55100" | ['Current: Approved', 'Unpaid $1,824.25', 'ORG_ZIZI_RETAIL']
    }
}
