Feature: Purchase any Order from Ecommerce Application
  I want to use this template for my feature file
  
  Background: 
  Given I landed on Ecommerce page

  @Regression
  Scenario Outline: Positive flow of Submitting the Order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the Order
    Then "THANKYOU FOR THE ORDER." message is displayed on the confirmation page

    Examples: 
      | name                  | password       | productName  |
      | sameerrizvi@gmail.com | Helloworld123  | ZARA COAT 3  |
