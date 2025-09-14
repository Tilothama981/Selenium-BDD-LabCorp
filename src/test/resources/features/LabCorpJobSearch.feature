Feature: Verify Labcorp Job Posting and Application Page

  Scenario: Verify Senior QA Analyst job details and apply page
    Given I launch the Labcorp website
    When I navigate to the Careers page
    And I search for "Sr QA Analyst"
    And I select the job posting "Senior QA Analyst"
    Then I verify the job title, location, and job id
    And I verify minimum requirements
    And I verify the third paragraph requirement
    And I verify Selenium is listed as a tool
    When I click on Apply and switch to application tab
    Then I verify the application page job title "Senior QA Analyst"
    And I navigate back to Careers Home
