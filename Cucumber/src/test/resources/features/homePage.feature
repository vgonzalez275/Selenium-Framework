#GHERKIN
#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Title of your feature
  I want to use this template for my feature file

  @tag1 @tag3 @regression @smoke
  Scenario: Check Home Page Title
    Given I open the home page
    Then I check the home page title is correct
    
  @tag3 @regression
  Scenario: Check login Failure
  Given I open the home page
  And I click on the account button
  When I enter the username and password
  Then I check the login error message 
  
  @regression @smoke
  Scenario: Check login Failure2
  Given I open the home page
  And I click on the account button
  When I enter the username "xyz" and password "ghj" 
  Then I check the login error message
  
  @tag2 @regression
  Scenario Outline: Check Multiple failed login
  Given I open the home page
  And I click on the account button
  When I enter username <usrname> and password <pass>
  Then I check the login error message

    Examples: 
      | username | pass |
      | iop      | iop  | 
      | jkl      | jkl  | 
