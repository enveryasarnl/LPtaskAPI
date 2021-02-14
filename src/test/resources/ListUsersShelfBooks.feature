Feature: ListUsersShelfBooks

  @ListUsersShelfBooks @smokeTest
  Scenario Outline: Call API to get all the books of the user's shelf
    Given call the GET endpoint with userID and 1001 shelf ID
    Then the status code for shelf is 200
    And the header "Content-Type" should be "application/json; charset=UTF-8"
    And the header "Content-Encoding" should be "gzip"
    Then the shelf has 3 books
    And the books "<titles>" should be "<in order>"as follows
    Examples:
      | titles                           |in order|
      | Stephen King                     |0       |
      | Carrie                           |1       |
      | Alice's Adventures in Wonderland |2       |

  @GetBookShelf @smokeTest
  Scenario: Call API to get the user's shelf info
    Given call the endpoint with 1001 shelf ID
    Then the response status code for shelf is 200
    And the response header "Content-Type" should be "application/json; charset=UTF-8"
    And the response header "Content-Encoding" should be "gzip"
    And the shelf title should be "testShelf"
    Then the shelf kind should be "books#bookshelf"
    And the shelf volume count should be 3
    And the shelf access level is "PUBLIC"

  @GetAllBookShelvesInfo @smokeTest
  Scenario: Call API to get all the bookShelves of the user
    Given call the endpoint with userID
    Then the response status code for bookshelves is 200
    And in the response header "Content-Type" should be "application/json; charset=UTF-8"
    And in the response header "Content-Encoding" should be "gzip"
    And the booksShelves IDs are 1001 and 1002
    And the bookShelves access levels are "PUBLIC"
    And the first bookShelf has 3 books
    Then the second bookShelf has 4 books
    And the booksShelves titles are "testShelf" and "testShelf2"

  @GetTheBookByItsId @smokeTest
  Scenario Outline: Call API to get one book by its ID
    Given call the endpoint with "<Book ID>"
    Then the response status code is success
    And in the header Content-Type should be application/json
    And in the header Content-Encoding should be gzip
    And the title "<title>" is follows
    Then the page count "<page count>" is as in the list below
    And the published date "<published date>" is as in the list below
    Examples:
      | Book ID     | title       |page count|published date|
      |8U2oAAAAQBAJ | Steve Jobs  |630       |2011          |
      |4bo0c6nSGsMC |Les Possedes |740       |2009-04       |
      |_rYpAAAAYAAJ |Russia       |422       |1917          |
      |SkZPAQAAMAAJ |Travelling   |196       |1822          |

  @ListTheBooksBySearch @smokeTest
  Scenario Outline: Call the API to search books on google
    Given call search endpoint with "<searching keyword>"
    Then the status code is success
    And in the response header Content-Type should be application/json
    And in the response header Content-Encoding should be gzip
    And the found books count is greater than "<books count>"
    Then the response body items should contain "<searching keyword>"
    And the "<kind>" should be for the kind key
    Examples:
      | searching keyword     | books count       |kind         |
      |Helicopter             |253                |books#volumes|
      |Aerodynamics           |310                |books#volumes|
      |Moscow                 |625                |books#volumes|


  @NonexistentShelf @smokeTest @Negative
  Scenario: Call API try to get nonexistent shelf
    Given call the endpoint with nonexistent shelf ID
    Then the response status code for nonexistent shelf request is 404
    And the message should be "The bookshelf ID could not be found."

  @GetAllBookShelvesInfo @smokeTest @Negative
  Scenario: Call API with invalid UserID
    Given call the endpoint with invalid userID
    Then the status code for invalid userID request is 503
    And the received message is "Service temporarily unavailable."














