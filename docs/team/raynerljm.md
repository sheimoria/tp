---
layout: page
title: Loh Jia Ming, Rayner's Project Portfolio Page
---

### Project: onlyFAs

onlyFAs is a desktop client-management application used by beginning to high-achieving financial advisors looking to maintain and develop good relationships with current and potential clients. THe user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=raynerljm&breakdown=true)

- **Enhancements implemented**:
## Meetings ##
My primary contribution to the onlyFAs application is the meetings' functionality. The following is the new classes I introduced into the application.
### Models: ###
- [x] `Meeting`
- [x] `NonOverlappingMeetingList`

### Commands: ###
- [x] `AddMeetingCommand`
- [x] `ListMeetingCommand`
- [x] `EditMeetingCommand`
- [x] `DeleteMeetingCommand`

### Parsers: ###
- [x] `AddMeetingCommandParser`
- [x] `ListMeetingCommandParse`
- [x] `EditMeetingCommandParse`
- [x] `DeleteMeetingCommandParser`

### Exceptions: ###
- [x] `MeetingNotFoundException`
- [x] `OverlappingMeetingsException`

### Tests: ###
- [x] `MeetingTest`
- [x] `NonOverlappingMeetingListTest`
- [x] `AddMeetingCommandTest`
- [x] `ListMeetingCommandTest`
- [x] `DeleteMeetingCommandTest`

### Test Utils: ###
- [x] `TypicalMeetings`
- [x] `MeetingBuilder`

- **Contributions to the UG**:
  - Improving the introduction of the UG
  - Adding all guides and examples related to commands I've implemented

- **Contributions to the DG**:
  - Explanation of the implementation of the entire Meetings feature

- **Review/mentoring contributions**:
  - We have a round robin system of reviewing code. Therefore I was responsible for reviewing all of Joel's code and PRs

- **Contributions beyond the project team:**:
