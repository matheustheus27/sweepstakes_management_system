## SMS - Sweepstakes Management System
This project was developed for the discipline Software Quality at CEFET MG.

    Professor: Kecia Aline Marques Ferreira
	Student: Matheus Thiago de Souza Ferreira

### Project Description
The project was developed with the intention of applying software patterns learned in the classroom.

The Software Specification (in pt-BR), can be found at: [Docs/SoftwareSpecification.pdf](https://github.com/matheustheus27/sweepstakes_management_system/tree/main/Docs/SoftwareSpecification.pdf)

The Class Diagram (in pt-BR), can be found at: [Docs/ClassDiagram.pdf](https://github.com/matheustheus27/sweepstakes_management_system/tree/main/Docs/ClassDiagram.pdf)

The system uses prompt, for a simple implementation.


#### Components Division
The project is divided into components, each one its controller and objects, separated into folders with their respective files. The orginize of files and folders, is part of the idea of Software Patterns.
##### Items
The `Items` represent the objects drawn on the system, in its folder, there is the `Objects\Item.java`, the Item abstraction, and the `Controllers\ItemController.java`, which is responsible for managing all the Items and persisting them to the DataBase.
##### Response
The `Response.java` is an enumerative class, which is responsible for issuing responses and communicating with all components of the system. It helps to standardize and better understand the service status of each component.
##### Rules
The `Rules` represent the conditions applied to a draw, in its folder, there is the `Objects\RuleObjects.java`, the Rule abstraction, and the `Controllers\RuleController.java`, which is responsible for managing all the Rules and persisting them to the DataBase.
##### Sweepstakes
The `Sweepstakes` represent the draws performed in the system, in its folder, there is the `Objects\Sweepstake.java`, the abstraction of the draw, and the `Controllers\SweepstakeController.java`, which is responsible for managing all the draws and persisting them in the DataBase.
##### Users
Users contains all the user types on the system, `Common` and `Manager`, and the generalized `User`.
The `Objects\User.java` is the generalized abstraction of User, and the `Controllers\UserController.java` is responsible for managing authentication and session for all user types.
The `Objects\Common.java` is the standard user abstraction, and the `Controllers\CommonController.java` is responsible for managing the operations performed for standard users.
The `Objects\Manager.java` is the abstraction of the administrator user, and the `Controllers\ManagerController.java` is responsible for managing the operations performed for the administrator users.
##### Other Files
The `Index.java` is responsible for starting the system. 
The `SMS.java` is responsible for starting all the controllers and showing the system prompts.


#### Programming Language
Java was selected for its familiarity to students.


#### How to Compile and Run
##### Step 1: Compile the file Index.java
    javac Index.java
##### Step 2: Run the Program
    java Index
##### Step 3: Create a User
At the Sign-up prompt, if you want create a manager user, the hash code is `ADMIN`, for common user, you can type any word in the hash.
##### Step 4: Login and Use System
At the Login prompt, you must use the registered credentials. The system will recognize the correct user type and redirect you to the panel.

## Screenshots
<img src="https://github.com/matheustheus27/sweepstakes_management_system/blob/main/Images/index.png?raw=true" alt="Home Screen" width="400"/> <img src="https://github.com/matheustheus27/sweepstakes_management_system/blob/main/Images/auth.png?raw=true" alt="Auth Screen" width="400"/> <img src="https://github.com/matheustheus27/sweepstakes_management_system/blob/main/Images/signup.png?raw=true" alt="Sig-up Screen" width="400"/> <img src="https://github.com/matheustheus27/sweepstakes_management_system/blob/main/Images/login.png?raw=true" alt="Login Screen" width="400"/> <img src="https://github.com/matheustheus27/sweepstakes_management_system/blob/main/Images/common_user_panel.png?raw=true" alt="Common User Panel Screen" width="400"/> <img src="https://github.com/matheustheus27/sweepstakes_management_system/blob/main/Images/manager_user_panel.png?raw=true" alt="Manager User Panel Screen" width="400"/>