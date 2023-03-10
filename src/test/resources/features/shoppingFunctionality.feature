Feature: Shopping functionality
  @test1
  Scenario: User can select any item from Electronics category
    When user clicks on hunburger menu
    And user click on "Electronics, Computers & Office" option
    And user clicks on "Laptops" option
    And user select "Mac OS" as Operating system
    And user set sort price by "Price: High to Low"
    And user click on "2" item from a list
    Then user must see description of product
