@tag 
Feature: Title of your feature

   Background: User is logged in
    Given I navigate to login "http://somepage.com" page
    When I submit username and password
    Then I should be logged in  

  Scenario: Create Customer
    Given I navigate to "http://somepage.com/somepath" page
    When I fill textboxes 
    	| id  |     value     |
    	| id  |     value     |
    	| id  |     value     |
    And I click buttons 
    	| id  | 
    And I fill comboboxes 
    	| id  |     value     |
    	| id  |     value     |
    	| id  |     value     |
    And I click radioboxes
    	| id  | 
    	| id  |
    	| id  | 
    And I click checkboxes
    	| id  | 
    	| id  |
    	| id  | 
    And I mouseover to
    	| id  | 
    And I scroll down
        | id  |
    And I take screenshots
    And I wait for
    	| id  | 
    	| id  |
    	| id  | 

    
    
    
