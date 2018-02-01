## Demo Space Root Page

Move along, nothing to see here...

## Link Tests 2

[Demo Page 1](DEMO/Demo+Page+1)
[Demo Page 1, Section 1](DEMO/Demo+Page+1#section-1)
[Demo Page 1, Subsection 1-2](DEMO/Demo+Page+1#subsection-1-2)

[Demo Page 1/Child Page 1](DEMO/Demo+Page+1/Child+Page+1)
[Child Page 1](DEMO/Child+Page+1)

[New Page Test](DEMO/New+Page+Test)

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

* generate PDF from wiki source

### HiveMind Projects and Tasks

* project budget for expenses (configure and report against)
* task estimated complete (by time, manual estimate?), task estimated expected progress (by date range?)

* reports
    * time entries by user and team for time periods (month, week)
    * estimated remaining time by project, task/subtask, team, user
    * task aging (by priority, then by created date)

* calendar
    * project, milestone, and task (by due date, etc) calendar
    * per-user calendar
    * add calendar events
    * event notifications, etc

* task attachments/content upload/download (and preview?); can use wiki pages but nice to have direct attachment without requiring separate wiki page
* task description interpret as markdown, and use md editor?

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

* Gantt chart based on dependencies and/or due dates
* project calendar with milestone dates, task dates
* user leave calendar entries, show on project calendar

* all upcoming tasks/requests list (all projects, clients, users, etc); filter by date range, other filter/sort
* recent activity list (comments, tasks created/updated, requests created/updated, other?)
* do both on same screen?

## HiveMind Requests

* add request summary screen similar to task one
* add request comments
* add request attachments/content, or just use wiki pages?

                        
