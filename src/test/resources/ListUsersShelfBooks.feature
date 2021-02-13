Feature: ListUsersShelfBooks

  @ListUsersShelfBooks
  Scenario Outline: Call API to get all the books of the user's shelf
    Given call the endpoint with userID and 1001 shelf ID
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

  @GetBookShelf
  Scenario: Call API to get the user's shelf info
    Given call the endpoint with 1001 shelf ID
    Then the response status code for shelf is 200
    And the response header "Content-Type" should be "application/json; charset=UTF-8"
    And the response header "Content-Encoding" should be "gzip"
    And the shelf title should be "testShelf"
    Then the shelf kind should be "books#bookshelf"
    And the shelf volume count should be 3
    And the shelf access level is "PUBLIC"

  @GetAllBookShelvesInfo
  Scenario: Call API to get all the bookShelves of the user
    Given call the endpoint with userID
    Then the response status code for bookshelves is 200
    And in the response header "Content-Type" should be "application/json; charset=UTF-8"
    And in the response header "Content-Encoding" should be "gzip"
    Then the user has 2 bookshelves
    And the booksShelves IDs are 1001 and 1002
    And the bookShelves access levels are "PUBLIC"
    And the first bookShelf has 3 books
    Then the second bookShelf has 4 books
    And the booksShelves titles are "testShelf" and "testShelf2"





 #     |totalItems 	 	                   | 3			                                               |
 #     |kind				                   |books#volumes	                                           |
 #     |items.kind[0]                       |books#volume                                               |
 #     |items.volumeInfo.title[0]           |Stephen King                                               |
 #     |items.volumeInfo.title[1]           |Carrie                                                     |
 #     |items.volumeInfo.title[2]           |Alice's Adventures in Wonderland                           |
 #     |items.accessInfo[0].country         |NL                                                         |







