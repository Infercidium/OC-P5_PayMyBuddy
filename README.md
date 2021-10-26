
# Pay My Buddy
It is a simplified and inexpensive banking transaction application between users.
The application works with Java Spring, stores the data with MySQL and the display with html/css via thymeleaf.

## Composing

### DataBase
The MySQL database is permanent, it is generated automatically via Spring boot in the `paymybuddy` shema.

You can modify the path to the database in `application.properties`, for example to use `prod` instead of the custom shema.
But also to modify the username and password, root and rootroot.

A demonstration script to run in the shema is also available.

Physical data model :
![Modèle Physique de Données](https://user-images.githubusercontent.com/82523651/138967776-b082cfa7-4e5d-48cb-8b84-26bed7164ab5.png)


### APPLICATION
The application is composed of html page allowing various actions and displaying corresponding information.

1. `Home`
  * Displays the Balance and a history of Transactions received.
  * 2 buttons, `Add Money` and `Withdraw Money`, to transfer money from a bank account to the application and vice versa.

2. `Transfer`
  * Display of the list of transactions carried out.
  * Button to add connection and user transaction form.
  
3. `Profil`
  * Display of a rectangle gathering several information of the user and a table of his linked bank accounts.
  * A profile modification button, an add bank account button and a delete button for each row of the table.
 
4. `Contact`
  * Display of all connections, username and associated email.
  * An add connection button and a delete button on each row of the connection table.
  
5. `Login`
  * A rectangle gathering all of the information with the 'Pay My Buddy' logo centered at the top.
  * The login form email password, a Remember me box, the login button and a registration button at the bottom right.
  
There are other Urls allowing various actions, all leading to one of its pages above.

Class diagram :
![Diagramme de Classe P6](https://user-images.githubusercontent.com/82523651/138967853-927211f3-f33c-4e12-b94c-29311ab16b50.png)


## Launch
Application uses spring boot, use the following command to start it:
`./mvnw.cmd spring-boot:run`

### First Launch
During the first launch it will be necessary to create the `paymybuddy` schema or to have modified it in `application.properties`, 
and as mentioned previously to have in mySQL `root` identifier and `rootroot` password or to have also modify them in the same file.

Then do the `./mvnw.cmd spring-boot:run` command.

And finally in the Authorities array, added the id `1` with the name `User`.

### Demo
To activate the demo, you have to perform the first two lines of the first launch.

Then copy the Script to the same folder as this ReadMe in mySQL.

And finally launch the application with `./mvnw.cmd spring-boot:run`.

## Testing
This application has Unit tests written.
It is possible to have access to Surefire Report and Jacoco to visualize the execution time and the coverage of the tests following this path:
`PayMyBuddy/target/site/project-reports.html`

### Test Report

After using the following command in the terminal:
`mvn clean verify site`

### Checkstyle Report

Checkstyle results takes into account the classes generated automatically by the Mapper, adding more than 300 uncorrectable errors.
But it is possible to make them disappear with the command:
`mvn site`

However after that it is possible that the application is having difficulties working correctly, in this case you have to do:
`mvn clean`
