Feature: Get books
  Scenario: User calls web service to get a book by its ISBN
    Given a book exists with an isbn of 9781451648546
    When a user retrieves the book by isbn
    Then the status code is 200
    And response includes the following
      | totalItems 	 	    | 1 				|
      | kind				| books#volumes		|
    And response includes the following in any order
      | items.volumeInfo.title 		| Steve Jobs		  |
      | items.volumeInfo.publisher 	| Simon and Schuster  |
      | items.volumeInfo.pageCount 	| 630			      |

  Scenario: User calls web service to get books by single author
    Given Author "Stephen King" exists
    When A user retrieves his books
    Then the status code is 200
    Then hhhh

  Scenario: User calls web service to get a shelf by its ID
    Given shelves exists with an id of 1001
    When a user retrieves the shelf by id
    Then the status code for shelf is 200
    Then response for shelf includes the following
      | totalItems 	 	    | 4				    |
      | kind				| books#volumes	|
      |items.kind[0]        |   books#volume           |
      |items.volumeInfo.title[0]|   The sentiment of flowers; or, Language of flora, by R. Tyas|




