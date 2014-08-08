
## Demo Space Root Page

Move along, nothing to see here...

## Link Tests

[Demo Page 1](DEMO/Demo%20Page%201)
[Demo Page 1, Section 1](DEMO/Demo%20Page%201#Section1)
[Demo Page 1, Subsection 1-2](DEMO/Demo%20Page%201#Section12)

[Demo Page 1/Child Page 1](DEMO/Demo%20Page%201/Child%20Page%201)
[Child Page 1](DEMO/Child%20Page%201)

[New Page Test](DEMO/New%20Page%20Test)

[Moqui Website](http://www.moqui.org)

## TODO Lists

### HiveMind General

* email invitation to new user with link to setup account

* better permission settings (tasks visible to just current user, all users in a group, entire project, etc)
* more testing and improvements of non-admin users with access to just certain projects, wikis, etc

* volume testing
    * script to create large numbers of clients, projects, tasks, invoices, payments, etc
    * review screens and performance with lots of data in place

* Team support
    * Team assoc with Project and Task
    * Team assoc with Request?
    * easy assign to team lead/manager

* easier screens for rate amount setup (in context of user, project, etc)

* improve wiki page and task update notification emails; show more specific fields, only send if supported fields changed, etc

* charts with flot (see https://github.com/flot/flot/blob/master/API.md)

* worker time invoices: support vendor worker organizations, create invoice for all workers in org (current supports one worker
  per invoice, most common scenario where a project vendor org hires out work to individuals as opposed to organizations)

* add VendorSummary (like dashboard... reuse dashboard forms?), Projects, Tasks, TimeEntries, Requests, Invoices
* add UserSummary (like dashboard for another user... reuse dashboard forms?), UserProjects, UserTasks, UserTimeEntries, UserRequests
    * timesheet for user (table with columns for days, rows for tasks)
    * public view for other users to see assignments, history, etc

* general activity feed for specific project, wikis, or all projects/wikis a user has access to

* expense item attachments (PDF, image, etc)
* expense mark-up on client invoice (configure mark-up percent by client invoice item type)

* non-billabe time entries (a different rate amount type)
* time entries not associated with tasks? probably better to use general tasks for this...

### HiveMind Wiki

* add URL path support for page attachments instead of plain transition with form parameters

* comment list and add comment form (files in a subdir like _attachments?, no use communication event)
* generate PDF from wiki source
* space page tree - change to use AJAX so entire space tree isn't loaded each time (maybe make page view change without reloading?)

### HiveMind Projects and Tasks

* limit projects visible in FindProject/etc for non-admin users

* task parent/child tree view by milestone, project
* show child tasks with status/etc on task summary
* create sub-task form (pre-populate parentWorkEffortId)

* task comments
    * add comments to HmTask DataDocument (for searching, etc)
    * send comment notification emails to associated users with notifications on
    * send mentioned notifications for usernames or first/last name of user mentioned in comment (flexible parse? special syntax?)

* improve find task screen
    * saved find options (filters)
    * configurable displayed columns

* user defined fields using moqui.entity.UserField (in edit task, find task, etc)
* task tags

* project template (project clone: milestones, tasks, assignments? (team only?), )
* project and task budgets for time (by hours, client rate, vendor rate) and project budget for expenses (configure and report against)

* Gantt chart based on dependencies and/or due dates
* project calendar with milestone dates, task dates
* user leave calendar entries, show on project calendar

* all upcoming tasks/requests list (all projects, clients, users, etc); filter by date range, other filter/sort
* recent activity list (comments, tasks created/updated, requests created/updated, other?)
* do both on same screen?

* add reports
    * time entries by user and team for time periods (month, week)
    * estimated remaining time by project, task/subtask, team, user
    * task aging (by priority, then by created date)

## HiveMind Requests

* add request summary screen similar to task one
* add request comments

                        
