
Feature: Purchase the order from eCommerce Website
  
  Background:
	Given I landed on eCommerce Page

	@SubmitOrder
  Scenario: Positive test of Submitting the order
    Given logged in with username <username> and password <password>
    When I add product <productName> to Cart
    And checkout product <productName> and submit order
    Then "THANKYOU FOR THE ORDER." message is displayed

    Examples: 
      | username  				 | password  | productName     | 
      | boratest@gmail.com | P@ssword1 | iphone 13 pro   |
      | boratest@gmail.com | P@ssword1 | ZARA COAT 3     |
      | boratest@gmail.com | P@ssword1 | ADIDAS ORIGINAL |
