# Maintenance Monitor
We are a small hydro-power electricity supplier near Vienna. Our customers expect electricity around the clock with a very high service level agreement. It is easy to derive that service times are very important to us. Huge monitors were installed that should show the current service message.

"Everything operates as expected" is the default message. But a service operator can set the message manually to something else (e.g., "Service checks: No power until 5:00 pm"). A service operator can also reset the message to its default message.

## Project
A team of max 3 members should implement a REST-based application in Java (using Spring Boot). The service should be able to manage a centrally stored message. It should be able to

* deliver the message to a client
* set it to a specific message
* reset the message

using its API.

## Requirements
Use GitHub for the project and follow the correct DevOps procedure. Use a Kanban board to manage your User Stories and use a git branching model (preferable gitflow) to manage your code. Track your development process by updating your Kanban board and write at least one unit tests for every requirement. A Continuous Integration pipeline that produces the finished software artifact should be implemented as well.

Document

* the whole process
* the user stories
* the repository URL (it has to be public)
* the usage of the software

in a PDF file with screenshots and explanatory text. Submit the code (including the .git folder) as a .zip file.

You can use external resources as long as you mark them: “ // taken from: <URL> ”

## Points
* 20% Documentations of the process
* 15% Requirement Definition (User Stories)
* 15% Correct Status / Linking / Branching (Kanban, git)
* 15% Implementation
* 15% Testing
* 10% Continuous Integration (Pipeline Testing)
* 10% Continuous Delivery (Pipeline Artifact)

All elements must be present in the documentation.

## References
1. /api/message                                                                                       -> "Everything works as expected"
2. /api/message/set?m=Service+checks:+No+power+until+5:00+pm  -> "ok"
3. /api/message                                                                                       -> "Service checks: No power until 5:00 pm"
4. /api/message/reset                                                                              -> "ok"
5. /api/message                                                                                       -> "Everything works as expected"

