---

layout: page
title: N Vijay Narayanan's Project Portfolio Page
---

### Project: onlyFAs

onlyFAs is a desktop client-management application used by high achieving financial advisors looking to maintain and develop good relationships with current and potential clients. THe user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=myc37&breakdown=true)

- **Enhancements implemented**:
  *My main contribution to onlyFAs was introducing all policy-related features. Listed Below are the various classes 
  I have implemented:*
  
  ######Models
  - `Policy`
  - `Premium`
  - `UniquePolicyList`
  - Contributed to `Client` to include UniquePolicyList attribute and all its corresponding functions

  ######Commands
  - `AddPolicyCommand`
  - `EditPolicyCommand`
  - `DeletePolicyCommand`
  - Contributed to `FilterCommand` to include predicates for premium and company
  - Contributed to `SortCommand` to include comparators for premium and number of policies

  ######Parsers
  - `AddPolicyCommandParser`
  - `EditPolicyCommandParser`
  - `DeletePolicyCommandParser`

  ######Exceptions
  - `DuplicatePolicyException`
  - `EmptyPolicyListException`
  - `InvalidPolicyIndexException`
  - `PolicyNotEditedException`
  - `PolicyNotFoundException`

  ######Storage
    - `JsonAdaptedPolicy`
    - Contributed to `JsonAdaptedClient` to include UniquePolicyList attribute and all its corresponding functions

  ######Util
    - `PolicyBuilder`
    - `TypicalPolicies`

  ######Tests
  - `PolicyTest`
  - `PremiumTest`
  - `UniquePolicyListTest`
  - `AddPolicyCommandTest`
  - `EditPolicyCommandTest`
  - `DeletePolicyCommandTest`
  - `AddPolicyCommandParserTest`
  - `EditPolicyCommandParserTest`
  - `DeletePolicyCommandParserTest`
  - `JsonAdaptedPolicyTest`
  
  
- **Contributions to the UG**:

  - Created and added examples for `Add Policy`
  - Created and added examples for `Edit Policy`
  - Created and added examples for `Delete Policy`
  - Contributed to `Sort Clients`
  - Contributed to `Filter Clients`


- **Contributons to the DG**:

  - Created PlantUML Diagrams for `Model` and `Storage`.
  - Created and added examples for `Policy` under the `Implementation` section.

  
- **Review/mentoring contributions**:

  - Reviewed pull requests made by `zechajw` as part of our forking workflow.

