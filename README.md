# RecipesPoint [![CodeFactor](https://www.codefactor.io/repository/github/el-f/recipespoint/badge)](https://www.codefactor.io/repository/github/el-f/recipespoint)

Full description and requirements are located in `Task.pdf`.

## Demo:
https://github.com/el-f/RecipesPoint/assets/39451680/9b0747ef-11dc-4868-92ad-3e843a74518d


## Tech Stack:

| Backend     | Frontend    | Database | Deployment              |
| ----------- | ----------- | -------- | ----------------------- |
| Java 17     | Typescript  | MySQL    | Docker + Docker Compose |
| Spring Boot | React       |          | Amazon EC2              |
| Lombok      | React Query |          |                         |
| MapStruct   | Jotai       |          |                         |
|             | MUI         |          |                         |
|             | Formik      |          |                         |
|             | Yup         |          |                         |

## Installation:

- Prerequisites:
  - Docker

1. Open a terminal at the same path as `docker-compose.yml`.
2. Enter `docker-compose up` and wait for it to finish.
3. The react client should now be available at [`http://localhost/`](http://localhost/), and the server at [`http://localhost:8081`](http://localhost:8081).

## Usage:

Use the client interface and / or send api requests to the server.

#### Available endpoints:

| URI                                        | Method | what?                                         |
| ------------------------------------------ | ------ | --------------------------------------------- |
| recipes/add                                | POST   | Add a recipe (used for testing).              |
| recipes/get                                | POST   | Submit a query and get resulting recipes.     |
| users/favorite-recipes/{userId}            | GET    | Get user's favorite recipes.                  |
| users/favorite-recipes/{userId}/{recipeId} | POST   | Add a recipe to user's favorite recipes.      |
| users/favorite-recipes/{userId}/{recipeId} | DELETE | Remove a recipe from user's favorite recipes. |
