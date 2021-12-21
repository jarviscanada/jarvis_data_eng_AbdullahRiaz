# JAVA TWITTER APP

## Introduction
Designed a java based Twitter application that allows the user to post a tweet, show a tweet and delete
a tweet through the use of Twitter's RESTful API's.

### Technologies Used:
* Java
* Twitter REST API's
* OAuthConsumer
* HttpClient
* Jackson
* JUnit4
* Spring Boot Framework
* Maven
* Docker
* Git
* IntelliJ IDEA

# Quick Start
**Package the app using Maven:**
```bash
mvn clean package
```
**Run the application using Docker:**
```bash
docker run --rm \
-e consumerKey=YOUR_VALUE \
-e consumerSecret=YOUR_VALUE \
-e accessToken=YOUR_VALUE \
-e tokenSecret=YOUR_VALUE \
therealskyz/twitter "post|show|delete" [options]
```
***The following are arguments to the Twitter App:***
* `consumerKey`: Consumer Key for Twitter REST API authentication.
* `consumerSecret:` Consumer Secret Key for Twitter REST API authentication.
* `consumerSecret:` Consumer Secret Key for Twitter REST API authentication.
* `accessToken`: Access Token for Twitter REST API authentication.
* `tokenSecret`: Secret Token for Twitter REST API authentication.
* `post|show|delete`: Post a tweet, show a tweet or delete a tweet.
* `[options]`: Various options depending on tweet argument:
  * `"post" "tweet text" "latitude:longitude"`
  * `"show" "tweet ID" "tweet fields"`
  * `"delete" "tweet ID's"`
  
# Design
The Twitter app follows the well known MVC(minus V) design pattern. The application consists of multiple layers that make up the 
architecture, namely the DAO Layer, Service Layer, Controller Layer and the Main Layer.
## UML Diagram
![uml](./assets/uml.png)

## TwitterDAO
The Data Access Object layer (DAO) is where the standard CRUD operations are executed against the underlying storage, and in this case
the Twitter REST API. It is in this layer where HTTP requests are made to the Twitter API endpoints by using an Http client implemented in the `TwitterHttpHelper` class. Endpoints like `/1.1/statuses/update.json` to make a `POST` request to post a `Tweet`, `/1.1/statuses/show.json` to make a `GET` request to show a `Tweet`, and lastly `/1.1/statuses/destroy/` endpoint to make a `POST` request to delete a `Tweet`. This layer also handles Tweet models (POJO's) that are utilized to make `Tweet` objects when making requests and receiving responses.

## TwitterService
The Service layer is where the business logic of a `Tweet` is implemented. This layer also calls the DAO layer in order to interact with the Twitter API. In order to post a `Tweet`, the tweet text string cannot exceed a character limit of 280 and the longitude and latitude values had to be in the range of -90 to 90 degrees and -180 to 180 degrees respectively. As for showing or deleting a `Tweet`, the corresponding tweet id's had to be 19 digits long.

## TwitterController
The Controller layer takes the user inputs from CLI and parses it. It then calls the service layer which returns the results as `Tweet` objects.

## TwitterCLIApp
The App/Main layer has the main method that creates the necessary components and chains the dependencies by calling the constructors of the underlying layers, i.e. `TwitterController`, `TwitterService`, `TwitterDAO`. In this layer, there is a run method that executes certain methods by calling the controller layer based on user inputs.

## Models
The `Tweet` models are designed as POJO's/Data Transfer Objects (DTO's) for data persistence. The `Tweet` object implemented does not encapsulate all the fields of the official `Tweet` object from Twitter, rather a simplified version of it. As according to the Twitter's version 1.1 API's documentation, the Tweet object is the parent object of the `entities` object and `coordinates` object. Furthermore, in conjunction with Jackson, the http response objects were able to be parsed and deserialized into `Tweet` objects.

**The Tweet object fields of the simplified Tweet model as JSON:**
```json
//Simplified Tweet Object 
{
   "created_at":"Mon Feb 18 21:24:39 +0000 2019",
   "id":1097607853932564480,
   "id_str":"1097607853932564480",
   "text":"test with loc223",
   "entities":{
      "hashtags":[],      //Object definition in official Twitter Docs
      "user_mentions":[]  //Object definition in official Twitter Docs
   },
   "coordinates":null,    //Object definition in official Twitter Docs
   "retweet_count":0,
   "favorite_count":0,
   "favorited":false,
   "retweeted":false
}
```

## Spring Boot
The dependencies of this application were managed by using Spring Boot, a variation of the Spring framework. The components were set to be the `TwitterCLIApp` and `TwitterHttpHelper`, the controller was set to be `TwitterController`, service was set to be `TwitterService` and repository was set to be `TwitterDAO`.

# Test
The application was tested using JUnit4 by running integration tests. The results of the tests were compared with the expected results for the methods within each layer.

# Deployment
The application was first compiled and packaged using Maven and subsequently dockerized by building an image through a docker file. The image was then uploaded to a docker registry for distribution.

# Improvements
1. Allow the application to post more than one tweet at a time. 
   1. Instead of running the application 3 times to post 3 tweets, the application will run only once.
2. Allow the application to show more than one tweet at a time. 
   1. Instead of running the application 3 times to show 3 tweets, the application will run only once.
3. Have the application utilize latest version of Twitter's REST API's (v2) instead of v1.1.