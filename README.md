# Java-Spingboot3-project

This is a Java application that retrieves from Github API repositories, their branches and last commit based on username.

## Prerequisites

- Java 17 or later
- Maven build tools
- Internet connection

## How to use

- Clone the repository

`git clone https://github.com/jfgtf/Java-Spingboot3-project.git`

- Navigate to directory

`cd Java-Spingboot3-project`

- Build the application

`mvn clean package`

- Run the application

`mvn spring-boot:run`

- The application is accesilble at http://localhost:8080/api/repositories/{username} where {username} is a Github username.

## API

`GET /api/repositories/{username}` 

Retrieves user's Github repositories and their branch information.

1. Request Parameters
- "username": The Github username for which to retrieve repositories.

2. Request Headers
- "Accept": The desired response format. To get a proper response value should be "application/json".

1. Response Status Code: 200 (OK)

```JSON
[
    {
        "name": "repository1",
        "ownerLogin": "user1",
        "branches": [
            {
                "name": "branch1",
                "commit":{
                    "sha": "hb243kj"
                }
            },
            {
                "name": "branch2",
                "commit":{
                    "sha": "23j312n"
                }
            }
        ]
    },
    {
        "name": "repository2",
        "ownerLogin": "user1",
        "branches": [
            {
                "name": "branch1",
                "commit":{
                    "sha": "12432h"
                }
            }
        ]
    }
]
```

2. Error Response Status Code: 404 (Not Found)

```JSON
{
    "status": "NOT_FOUND",
    "message": "User not found"
}
```

3. Error Response Status Code: 406 (Not Acceptable)

```JSON
{
    "status": "NOT_ACCEPTABLE",
    "message": "Unsupported media type"
}
```
