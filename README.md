# Google Books APIs

### How to install, run and write new test cases 

It can be cloned with Intellij and can be added new scenarios to the feature file. 
If it is necessary, new steps should be created in particular StepDefs classes.
Since Scenario Outlines is used, the same scenarios can be executed by adding new data under Examples: 

The project was automated inside the GitLab CI. Once push the new code to the repository, it will be triggered automatically and the test results and the artifacts (cucumber-reports and surefire-reports) are accessible under the GitLab CI/CD Pipelines.
(https://gitlab.com/yasarenver/lptaskapi)


You can see the endpoints interactions below;


### Retrieving (by searching) books and list results

**Request**

`GET https://www.googleapis.com/books/v1/volumes?q={seach}&key={yourAPIKey} ` <br/>
**Response**

If the request succeeds, the server responds with a 200 OK HTTP status code and the volume results:

```
200 OK
{
 "kind": "books#volumes",
 "items": [
  {
   "kind": "books#volume",
   "id": "_ojXNuzgHRcC",
   "etag": "OTD2tB19qn4",
   "selfLink": "https://www.googleapis.com/books/v1/volumes/_ojXNuzgHRcC",
   "volumeInfo": {
    "title": "Flowers",
    "authors": [
     "Vijaya Khisty Bodach"
    ],
   ...
  },
  {
   "kind": "books#volume",
   "id": "RJxWIQOvoZUC",
   "etag": "NsxMT6kCCVs",
   "selfLink": "https://www.googleapis.com/books/v1/volumes/RJxWIQOvoZUC",
   "volumeInfo": {
    "title": "Flowers",
    "authors": [
     "Gail Saunders-Smith"
    ],
    ...
  },
  {
   "kind": "books#volume",
   "id": "zaRoX10_UsMC",
   "etag": "pm1sLMgKfMA",
   "selfLink": "https://www.googleapis.com/books/v1/volumes/zaRoX10_UsMC",
   "volumeInfo": {
    "title": "Flowers",
    "authors": [
     "Paul McEvoy"
    ],
    ...
  },
  "totalItems": 3
```


### Retrieving a specific book by BookID 

**Request**

`GET https://www.googleapis.com/books/v1/volumes/{volumeId}` <br/>

**Response**

If the request succeeds, the server responds with the 200 OK HTTP status code and the Volume resource requested:

```
200 OK

{
 "kind": "books#volume",
 "id": "zyTCAlFPjgYC",
 "etag": "f0zKg75Mx/I",
 "selfLink": "https://www.googleapis.com/books/v1/volumes/zyTCAlFPjgYC",
 "volumeInfo": {
  "title": "The Google story",
  "authors": [
   "David A. Vise",
   "Mark Malseed"
  ],
  "publisher": "Random House Digital, Inc.",
  "publishedDate": "2005-11-15",
  "description": "\"Here is the story behind one of the most remarkable Internet
  successes of our time. Based on scrupulous research and extraordinary access
  to Google, ...",
  "industryIdentifiers": [
   {
    "type": "ISBN_10",
    "identifier": "055380457X"
   },
   {
    "type": "ISBN_13",
    "identifier": "9780553804577"
   }
  ],
  "pageCount": 207,
  "dimensions": {
   "height": "24.00 cm",
   "width": "16.03 cm",
   "thickness": "2.74 cm"
  }, ...

```

### Retrieving a list of user's bookshelves

**Request**

`GET https://www.googleapis.com/books/v1/users/{userId}/bookshelves` <br/>
Since a user does not have to be authenticated to retrieve information regarding public bookshelves, you do not have to provide the Authorization HTTP header with the GET request.

**Response**

If the request succeeds, the server responds with the 200 OK HTTP status code and the list of bookshelves:
```
200 OK

{
 "kind": "books#bookshelves",
 "items": [
  {
   ...
  },
  {
   "kind": "books#bookshelf",
   "id": 3,
   "selfLink": "https://www.googleapis.com/books/v1/users/1112223334445556677/bookshelves/3",
   "title": "Reading now",
   "description": "",
   "access": "PUBLIC",
   "updated": "2011-02-02T20:34:20.146Z",
   "created": "2011-02-02T20:34:20.146Z",
   "volumeCount": 2,
   "volumesLastUpdated": "2011-02-02T20:34:20.110Z"
  },
  ...
 ]
}

```


### Retrieving a list of books on a user's bookshelf

**Request**

You can retrieve a list of volumes on a user's public bookshelf by sending an HTTP GET request the URI with the following format:

`GET https://www.googleapis.com/books/v1/users/{userId}/bookshelves/{shelf}/volumes` <br/>

**Response**

If the request succeeds, the server responds with a 200 OK HTTP status code and the list of the user's bookshelves:

```
200 OK

{
 "kind": "books#volumes",
 "items": [
  {
   "kind": "books#volume",
   "id": "AZ5J6B1-4BoC",
   "etag": "kIzQA7IUObk",
   "selfLink": "https://www.googleapis.com/books/v1/volumes/AZ5J6B1-4BoC",
   "volumeInfo": {
    "title": "The Girl Who Kicked the Hornet's Nest",
    "authors": [
     "Stieg Larsson"
    ],
    "publisher": "Knopf",
    "publishedDate": "2010-05-25",
    ...
  },
  {
   "kind": "books#volume",
   "id": "UvK1Slvkz3MC",
   "etag": "otKmdbRgdFQ",
   "selfLink": "https://www.googleapis.com/books/v1/volumes/UvK1Slvkz3MC",
   "volumeInfo": {
    "title": "The Girl who Played with Fire",
    "authors": [
     "Stieg Larsson"
    ],
    "publisher": "Knopf",
    "publishedDate": "2009-07-28",
    ...
  },
  {
   "kind": "books#volume",
   "id": "OBM3AAAAIAAJ",
   "etag": "xb47kTr8HsQ",
   "selfLink": "https://www.googleapis.com/books/v1/volumes/OBM3AAAAIAAJ",
   "volumeInfo": {
    "title": "The Sign of Four",
    "authors": [
     "Sir Arthur Conan Doyle"
    ],
    "publishedDate": "1890",
    ...
  }
 ],
 "totalItems": 3
}
```

###  Retrieving a specific user's bookshelf  


**Request**

You can retrieve a specific public bookshelf by sending an HTTP GET request to the URI with the following format:

`GET https://www.googleapis.com/books/v1/users/{userId}/bookshelves/{shelf}` <br/>

**Response**

If the request succeeds, the server responds with the 200 OK HTTP status code and the bookshelf resource:
```
200 OK

{
  "kind": "books#bookshelf",
  "id": 3,
  "selfLink": "https://www.googleapis.com/books/v1/users/1112223334445556677/bookshelves/3",
  "title": "Reading now",
  "description": "",
  "access": "PUBLIC",
  "updated": "2011-02-02T20:34:20.146Z",
  "created": "2011-02-02T20:34:20.146Z",
  "volumeCount": 2,
  "volumesLastUpdated": "2011-02-02T20:34:20.110Z"
}
```


