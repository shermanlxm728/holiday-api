# Federal Holiday API

This project is a Spring Boot application designed to manage and track federal holidays in Canada and the USA. It uses an H2 in-memory database to store holiday data and provides a RESTful API to add, update, and list holidays. 

The application includes Swagger for API documentation. 

The app is built with Spring Boot. 

The following functionality are exposed via RESTful APIs

Get a list of holidays (max limit of 100 returned): localhost:8080/holidays

Create a new holiday: localhost:8080/holiday

Update a holday (like its name or date change): localhost:8080/holiday/{id} 

Holidays are stored in h2 database. The database are populated with following data on startup:

![image](https://user-images.githubusercontent.com/86971702/124503302-44416700-dd93-11eb-9a5a-a7d35cae0f01.png)

Tests are included with the code.

## Setup
### Requirements
* Should use Java 11 or higher. Previous versions of Java are un-tested.
* Use Maven 3.6.0 or higher
* IntelliJ 2018 or Higher

### Steps:
1) On the command line
    ```
    git clone https://github.com/shermanlxm728/pet-store.git
    ```
    
2) Inside Intellij
    ```
   File -> New -> Project from Exsiting Source..
   ```
   run the application by right clicking on the PetstoreApplication main class and choosing Run 'PetstoreApplication'.
    
3) Navigate to Petstore Swagger UI, then you can perform CRUD operation

    Visit http://localhost:8080/swagger-ui.html in your browser.
    
    ![image](https://user-images.githubusercontent.com/86971702/124506472-b4eb8200-dd99-11eb-9974-2f2f8ac83506.png)

4) Pets are stored in h2 database. The database are populated with following data on startup:

![image](https://user-images.githubusercontent.com/86971702/124503302-44416700-dd93-11eb-9a5a-a7d35cae0f01.png)

To Login to the database, go to link: http://localhost:8080/h2-console with JDBC URL: jdbc:h2:mem:testdb as shown below

![image](https://user-images.githubusercontent.com/86971702/124507498-e1a09900-dd9b-11eb-8beb-9445f7950435.png)


5) Get a specific pet in the database: localhost:8080/pet/101 

![image](https://user-images.githubusercontent.com/86971702/124507784-891dcb80-dd9c-11eb-9a70-e3165f80827b.png)

6) Get a list of pets (max limit of 100 returned): localhost:8080/pets

![image](https://user-images.githubusercontent.com/86971702/124506859-84581800-dd9a-11eb-885c-10b2fe3b830e.png)

7) Create a new pet: localhost:8080/pet

![image](https://user-images.githubusercontent.com/86971702/124506934-b1a4c600-dd9a-11eb-9912-98095b7fc41d.png)

8) Update a pet (like its name or price change): localhost:8080/pet/{id}

![image](https://user-images.githubusercontent.com/86971702/124507907-cc783a00-dd9c-11eb-9617-18215c4ba845.png)

9) Delete a pet: localhost:8080/pet/{id}

![image](https://user-images.githubusercontent.com/86971702/124508026-0a755e00-dd9d-11eb-91bb-5ee992ffb9b9.png)

10) Test are included under the test folder:

![image](https://user-images.githubusercontent.com/86971702/124508207-6d66f500-dd9d-11eb-8c04-2ea51b5cad58.png)


11) Postman file is inside the project: 





