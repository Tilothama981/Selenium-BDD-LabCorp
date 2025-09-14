#Feature: API Testing with REST Assured
#
#  Scenario: Validate GET request response
#    When I send a GET request to "https://echo.free.beeceptor.com/sample-request?author=beeceptor"
#    Then the response should contain path, ip and headers
#
#  Scenario: Validate POST request response
#    When I send a POST request to "https://echo.free.beeceptor.com/sample-request?author=beeceptor"
#    Then the response should contain correct customer information
#    And the response should contain correct payment details
#    And the response should contain correct product information
