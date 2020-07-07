Feature: Jira Create Issue

  Scenario Template: Create and Validate Jira Issues
    When user creates a Jira issues by providing "<summary>", "<description>" and "<issue type>"
    Then validate id, "<summary>" and "<description>" on UI
    Examples:
      | summary                                            | description                                                                           | issue type |
      | Help Button                                        | Help button must be in each page of website.                                          | Story      |
      | No Help Button On Log In Page                      | After navigating to log in page, user supposed to see help button.                    | Bug        |
      | Test Checkout Page                                 | Check out page must contain cart details calculated                                   | Story      |
      | Total price calculation is wrong                   | Wrong calculation once user has discount or deal                                      | Bug        |
      | Test Deal Linked Images                            | Clicking of linked images, must take user to deals page                               | Story      |
      | Discount amount incorrect                          | Discount amount on description and linked image discount amount does not match        | Bug        |
      | Chat-unable to rename group name                   | Members of chat group are not able to rename the group conversation                   | Bug        |
      | Perform SAT                                        | SAT test for China market                                                             | Story      |
      | Raise performance of website prior to Black Friday | Load test, scalability test and stress test                                           | Story      |
      | Failed to log in                                   | Log in functionality is failing once increased number of users sign in simultaneously | Bug        |