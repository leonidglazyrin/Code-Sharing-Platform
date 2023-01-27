# Code Sharing Platform API made with Spring Boot

Deployed on AWS Elastic Beanstalk with a PostgreSQL database on AWS RDS. [Link](https://codesharingplatform.com/)

## Description

This API allows users to send and retrieve snippets of code using unique identifiers (UUIDs). Similar to pastebin.com, users can use this API to store and share their code with others.

To send a snippet of code, users can make a POST request to the API with their code as the body of the request. The API will then return a UUID that can be used to retrieve the code at a later time.

To retrieve a snippet of code, users can make a GET request to the API using the UUID as a path parameter. The API will then return the corresponding snippet of code.

In addition to the API, our service also includes a web interface that allows users to easily send and retrieve code snippets through their web browser. The web interface makes it easy for users to interact with the API without having to make requests directly.

Overall, the web interface provides a user-friendly way for users to interact with the API and easily share their code with others.

<!-- Users can also delete code snippets by making a DELETE request to the API using the UUID as a path parameter. -->

## Endpoints

### `POST api/code/new`

Send a code snippet with

```sh
{"code": "{your code}", "views": {view limit}, "time": {time in seconds limit}}
```

as the request body.

The `views` parameter specifies the maximum number of times that a code snippet can be retrieved before it is automatically deleted. For example, if a user sets the views parameter to 10, the code snippet will be deleted after it has been retrieved 10 times.

The `time` parameter specifies the number of seconds that a code snippet should be kept before it is automatically deleted. For example, if a user sets the expiration parameter to 86400 (the number of seconds in a day), the code snippet will be deleted after one day.

### `GET api/code/{uuid}`

Retrieve the specified snippet of code by sending

```sh
{"id" : "{uuid}"}
```

as the request body.

### `GET api/code/latest`

Return the 10 last snippets of code excluding the ones with restrictions. No request body. Return a JSON array of objects :

```sh
[{"code":"...", "date":"...", "time":0, "views":0},
 {"code":"...", "date":"...", "time":0, "views":0},
 {"code":"...", "date":"...", "time":0, "views":0}]
```

### [`/code/new`](http://codesharingplatformwithspringboot-env.eba-kym3bz5b.us-east-1.elasticbeanstalk.com/code/new)

Web interface to create and send a code snippet.

To send a code snippet, users can simply enter their code into a text field and click a button to submit it. The web interface will then make a request to the API on the user's behalf and display the corresponding UUID.

### `/code/{uuid}`

Web inteface view of a specified snippet of code.

To retrieve a code snippet, users can enter the UUID into as a path parameter. The web interface will then make a request to the API and display the corresponding code snippet on the screen.

### [`/code/latest`](http://codesharingplatformwithspringboot-env.eba-kym3bz5b.us-east-1.elasticbeanstalk.com/code/latest)

Web inteface view of the 10 latest snippets of code

## Author

Leonid Glazyrin
