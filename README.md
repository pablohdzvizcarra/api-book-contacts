# Book Contacts API

API that implements CRUD Operations.

## 1. The Idea

Create an API that can implement CRUD operations, any developer can use it with your
front-end projects because this API always returns the same format.

This API simulates a contacts book, where the user can create contacts and add them to the
book directory

## 2. Run Project

This project is run with maven, you can run this project in different ways:

### 2.1 Maven

Run this command by the command line in the root directory to execute the app with maven

```shell
mvn spring-boot:run
```

### 2.2 Maven Script

Execute the script of maven for run the project

```shell
 ./mvnw spring-boot:run
```

### 2.3 Build jar and execute

first: you need build jar file with this command

```shell
mvn package
```

second: you run the jar file before created

```shell
java -jar target/[name-file-jar]
```

the jar file after created is inside the folder target

## 3. Documentation

### 3.1 Swagger

This project has an implementation documentation by swagger,which you can see in this
link:

### 3.2 Entity

We have some entities to handle data provided from the request and data to response to the
client.

Contact

```json
{
  "id": 1,
  "name": "john",
  "phoneNumber": "8745126732",
  "phoneType": "mobile",
  "createdAt": "24-11-2021 09:38",
  "updatedAt": "24-11-2021 09:38"
}
```
### 3.3 Response

we handle a universal response for all API with this format example saved user:

```json
{
  "timeStamp": "24-Nov-2021 09:38",
  "statusCode": 200,
  "status": "CREATED",
  "message": "contact saved",
  "developerMessage": "the contact is created successfully",
  "data": {
    "contact": {
      "id": 3,
      "name": "john",
      "phoneNumber": "8745126732",
      "phoneType": "mobile",
      "createdAt": "24-11-2021 09:38",
      "updatedAt": "24-11-2021 09:38"
    }
  }
}
```

The property data in the response always change depending on the endpoint to you call
response when error is occurred:
```json
{
  "timeStamp": "24-Nov-2021 09:43",
  "statusCode": 409,
  "status": "CONFLICT",
  "reason": "409 CONFLICT",
  "message": "exception occurred",
  "developerMessage": "exception because try save duplicated data",
  "data": {
    "error": "you try save a contact, but the phone number already is registered, remember phone numbers be uniques"
  }
}
```

### 3.4 Request



## 4. Developer Process and Libraries

I like to track one methodology for the development process of my project, which I don't
know, helps me to focus on creating one functionality or doing one task only.

you can see this process with Notion file in this link:
[notion link](https://scalloped-count-223.notion.site/d4caffd10ab74b7894cc803778aa4b9a?v=3a91d342360d4b53b5fa965414d772ec)

### 4.1 Used Libraries

- Spring Boot: framework
- MySQL: database implementation
- OpenAPI: with swagger to document this API
- SpringBoot JPA: to handle logic to persist data in database
- AssertJ: tests more structured
- Javax Validations: bean validation for fields in the request data

## 5. Docker

## 6. Testing / CI

## 7. Flow Chart

## 8. Extras
