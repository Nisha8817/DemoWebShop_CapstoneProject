Feature: Product Search and Shopping Cart Management

  Scenario: Search for a product and add it to the cart
    Given the user is on the Demo Web Shop homepage
    When the user searches for "14.1-inch Laptop"
    And clicks on the product from the search results
    And clicks the "Add to Cart" button
    Then the product should be successfully added to the cart
   
   Scenario: Checkout Process with Invalid Data Handling
    Given the user is on the Demo Web Shop homepage
    And the user has a product inside the shopping cart
    When the user opens the shopping cart
    And checks terms of service and clicks checkout
    And selects checkout as guest option
    And attempts to continue billing with empty required fields
    Then a billing validation error message should appear
    When the user enters valid checkout billing data
    And completes all remaining checkout steps
    Then the order confirmation message "Your order has been successfully processed!" should be displayed
    
   Scenario: User Registration with Invalid Data Handling
    Given the user is on the Demo Web Shop homepage
    When the user navigates to the Registration page
    And attempts to register with missing required data fields
    Then a registration form field validation error should appear
    When the user registers with valid unique customer credentials
    Then the registration confirmation message "Your registration completed" should be displayed
    
   Scenario: Login with Valid & Invalid Credentials
    Given the user is on the Demo Web Shop homepage
    When the user navigates to the Login page
    And attempts to log in with invalid customer credentials
    Then a login validation error message should be displayed
    When the user enters the valid credentials of the registered account
    Then the "My account" email link should be displayed at the top
    
   Scenario Outline: Data Driven Login Validation

    Given the user is on the Demo Web Shop homepage
    When the user navigates to the Login page
    And authenticates using credentials ID "<RowID>" out of the excel sheet
	Then the login result should be captured
	Examples:
	| RowID  |
	| Row_01 |
	| Row_02 |
	| Row_03 |
	| Row_04 |
	| Row_05 |
	| Row_06 |

   Scenario: Adding Multiple Products to Cart & Validating Cart Summary
    Given the user is on the Demo Web Shop homepage
    When the user searches for "Computer" items and adds "Build your own cheap computer" to cart
    And the user searches for "Computer" items and adds "Simple Computer" to cart
    And navigating to the checkout shopping cart page view
    Then the cart summary totals grid should display correct items and price calculations

   Scenario: Perform Check-out with Coupon Discount Verification
    Given the user is on the Demo Web Shop homepage
    When the user navigates to a product details page view and adds an item to cart
    And navigates back to the cart page to apply coupon code "test_coupon"
    Then the cart summary should reflect the applied discount calculation

   Scenario: Logging Out and Verifying Session End
    Given the user is on the Demo Web Shop homepage
    When the user ensures they are logged in with valid credentials
    And clicks the logout button from the account navigation links
    Then the user should be logged out and redirected to the home landing page
    
   Scenario: Forgot Password Password Recovery Process
    Given the user is on the Demo Web Shop homepage
    When the user navigates to the Login page
    And clicks on the "Forgot password?" hyperlink
    And enters the registered account email address to recover password
    And clicks the recovery submission button
    Then a notification message "Email with instructions has been sent to you." should be displayed
    
   Scenario: Update Shopping Cart Quantity Controls and Product Removal
    Given the user is on the Demo Web Shop homepage
    When the user searches for "14.1-inch Laptop" items inside the store catalogue
    And clicks on the product link to open the product details page
    And clicks the "Add to Cart" button
    And navigates to the shopping cart workbench page
    And changes the product quantity field value to "3"
    And clicks the update action button
    Then the cart total should reflect the updated quantity calculations
    When the user checks the remove selection box for the product
    And clicks the update action button again
    Then the product should be completely removed and the cart should display "Your Shopping Cart is empty!"
    
   Scenario: Wishlist Operations for Authenticated Users
    Given the user is on the Demo Web Shop homepage
    When the user logs into their active account if not already authenticated
    And searches for "Health Book" and navigates to its product details page
    And clicks the Add to Wishlist action button
    And navigates to the Wishlist storage page view
    Then the product should be successfully visible inside the wishlist table
    When the user checks the remove checkbox next to the product item
    And clicks the Update Wishlist button
    Then the wishlist page should update and display "The wishlist is empty!"
    
   Scenario: Product Comparison Engine Operations
    Given the user is on the Demo Web Shop homepage
    When the user searches for "Computer" items and opens "Build your own cheap computer"
    And clicks the Add to Comparison List action button
    And the user searches for "Computer" items and opens "Simple Computer"
    And clicks the Add to Comparison List action button again
    Then the product comparison table grid should display both computer products successfully

   Scenario: Registered User Checkout Process Completion via Gateway Registration
    Given the user is on the Demo Web Shop homepage
    When the user ensures they are signed out to verify the checkout gateway path
    And the user searches for "Computer" items and adds "Simple Computer" to cart
    And navigating to the checkout shopping cart page view
    And checks terms of service and clicks checkout
    And registers a new user inside the gateway screen and completes the checkout process
    Then the order confirmation message "Your order has been successfully processed!" should be displayed

   Scenario: Checkout with New Shipping Address Configuration
    Given the user is on the Demo Web Shop homepage
    When the user registers with valid unique customer credentials
    And the user searches for "Computer" items and adds "Simple Computer" to cart
    And navigating to the checkout shopping cart page view
    And checks terms of service and clicks checkout
    And completes billing address confirmation fields
    And configures a brand new shipping address location details
    And completes the remaining registered checkout steps
    Then the registered user order confirmation message "Your order has been successfully processed!" should be displayed
    
  Scenario: Checkout with Different Billing & Shipping Address Setup
    Given the user is on the Demo Web Shop homepage
    When the user registers with valid unique customer credentials
    And the user searches for "Computer" items and adds "Simple Computer" to cart
    And navigating to the checkout shopping cart page view
    And checks terms of service and clicks checkout
    And completes billing address confirmation fields
    And selects a completely different separate address for the shipping parameters
    And completes the remaining registered checkout steps
    Then the registered user order confirmation message "Your order has been successfully processed!" should be displayed
    
   Scenario: Verify Order History and Invoice Validation after placing order

	Given the user is on the Demo Web Shop homepage
	When the user registers with valid unique customer credentials
	And the user searches for "Computer" items and adds "Simple Computer" to cart
	And navigating to the checkout shopping cart page view
	And checks terms of service and clicks checkout
	And completes billing address confirmation fields
	And configures a brand new shipping address location details
	And completes the remaining registered checkout steps

	And the user logs into their active account if not already authenticated
	And the user navigates to My Account page
	And opens the Orders history section
	Then the recently placed order should be visible in the tracking list
	When the user clicks on the order details link
    And selects the PDF invoice option from the dashboard controls
    Then the invoice should be generated successfully
	
  Scenario: My Account Management - Update Personal Details
    Given the user is on the Demo Web Shop homepage
    When the user registers with valid unique customer credentials
    And the user navigates to My Account page
    And updates the customer profile information
    And saves the modified profile details
    Then the profile information should be updated successfully
    
  Scenario: Newsletter Subscription Management
    Given the user is on the Demo Web Shop homepage
    When the user registers with valid unique customer credentials
    And the user navigates to My Account page
    And subscribes to the newsletter service
    Then the newsletter subscription should be enabled successfully
    When the user unsubscribes from the newsletter service
    Then the newsletter subscription should be disabled successfully

  Scenario: Submit Contact Us Form Successfully
    Given the user is on the Demo Web Shop homepage
    When the user navigates to the Contact Us page
    And enters contact name "Alex Tester"
    And enters contact email "alex.tester@test.com"
    And enters contact enquiry message "Need support regarding product information"
    And submits the Contact Us form
    Then the contact request should be submitted successfully

  Scenario: Category Navigation Through Categories and Subcategories
    Given the user is on the Demo Web Shop homepage
    When the user navigates to the "Computers" category
    And opens the "Desktops" subcategory
    Then products related to the selected subcategory should be displayed
    When the user navigates to the "Books" category
    Then products related to the selected category should be displayed

    Scenario: Product Sorting Validation
    Given the user is on the Demo Web Shop homepage
    When the user searches for "Computer"
    And sorts products by "Name: A to Z"
    Then the products should be sorted correctly by name
    When the user sorts products by "Price: Low to High"
    Then the products should be sorted correctly by price
    When the user sorts products by "Price: High to Low"
    Then the products should be sorted correctly by price descending
    
   Scenario: Product Filter Validation
    Given the user is on the Demo Web Shop homepage
    When the user navigates to the "Desktops" category
    And applies available product filters
    Then only products matching the selected filters should be displayed
    When the user clears all applied filters
    Then all products should be displayed again

  Scenario: Recently Viewed Products Validation Engine
    Given the user is on the Demo Web Shop homepage
    When the user views the product "14.1-inch Laptop"
    And the user views the product "Health Book"
    And the user navigates to the Recently Viewed Products page
    Then both recently viewed products should be displayed successfully

  Scenario: Product Return Request Submission for Eligible Items
	Given the user is on the Demo Web Shop homepage
    When the user registers with valid unique customer credentials
    And the user searches for "Computer" items and adds "Simple Computer" to cart
    And navigating to the checkout shopping cart page view
    And checks terms of service and clicks checkout
    And completes the registered user checkout process
    And the user navigates to My Account page
    And opens the Orders history section
    And selects the details of the eligible completed order
    And initiates a product return request form submission
    Then the return request summary text "Why are you returning this item?" should be verified successfully













