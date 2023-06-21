# Games_Shop

# Overview

The project is based on two components:
- Spring Boot backend server
- Angular frontend server

# How to run

## Prerequisites

- `git`
- `java`
- `npm`
- `ng` command-line tool
- `mvn`
- `docker`

## Steps

Run a local database by using Docker:

PowerShell command:
`docker run --name postgres -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -d postgres`

Execute the following steps in order to run the Spring Boot server:

1. `git clone https://github.com/LRusinov/Games_Shop`
2. `cd Games_Shop/games-shop-app`
3. `mvn clean install`
4. `cd target`
5. `java -jar Games_Shop-0.0.1-SNAPSHOT.jar`

> Backend server should now run at: http://localhost:8080

Execute the following steps in order to run the Angular server:

1. Go back to where the repository was cloned
2. `cd Games_Shop/games-shop-app/src/main/resources/webapp/games-shop-app`
3. `npm install`
4. `ng serve`

> Frontend server should now run at: http://localhost:4200