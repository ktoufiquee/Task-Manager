# Installation Guide

Following version of the software/tools were used in developing this project:
1. JDK 17
2. MySQL 8.3
3. Spring Boot 3.2.2

Follow the instruction below for installation:
1. Create a schema in MySQL eg: taskmanagerdb
2. Clone the repository.
3. Set following values in ***src/main/resources/application.properties***:
```
spring.datasource.url=	//mysql database url eg: jdbc:mysql://localhost:3306/taskmanagerdb (Note: here taskmanagerdb is the schema created in Step 1)
spring.datasource.username=		//mysql database username
spring.datasource.password=		//mysql database password
webserver.origin=		//endpoint for web (fronted) eg: http://127.0.0.1:5050
```
3. Build the project.
4. Run the built project/ .jar file, this is our server.
5. Set following values in ***web/config.js***:
```
endpoint: \\this value will be the endpoint the server is using eg: http://localhost:8080/
```
6. Start a server on web directory. (How to: [Link 1 (Using VS Code) Tested](https://www.geeksforgeeks.org/how-to-enable-live-server-on-visual-studio-code/)], [Link 2 Not Tested)](https://attacomsian.com/blog/local-web-server) )
7. Start the application using ***web_endpoint/Start.html*** eg: 127.0.0.1:5050/Start.html

# API Documentation
| endpoint |  receiving value | return value | Method |
|------------|------------------|------------|------|
|api/authenticate | |boolean | GET |
|api/username | | username | GET |
|api/login | {username: "", password: ""} | uuid/access token| POST |
|api/registar| {username: "", mail: "", password: ""} | {username: "", mail: "", password: ""}| POST |
|api/logout | |boolean | DELETE |
|api/tasks | |[{taskid: "", title: "", description: "", status: "", username: ""}, ...] | GET |
|api/tasks/{taskid} | {status: ""} |{taskid: "", title: "", description: "", status: "", username: ""} | PATCH |
|api/tasks |{taskid: "", title: "", description: "", status: "", username: ""} | {taskid: "", title: "", description: "", status: "", username: ""}| POST |
|api/tasks/{taskid} | | | DELETE |

# Testing
Testing was done using Mockito to validate if data validation for User entity works or not. Two scenario was tested: wrong formatted email, and weak password.
```
    @Test
    public void shouldNotInsertForMail() {
        User user = new User("ktouf", "invalid", "pass@12A");
        userController.createUser(user);
        assertFalse(repository.findById(user.getUsername()).isPresent());
    }

    @Test
    public void shouldNotInsertForPassword() {
        User user = new User("ktouf", "valid@gmail.com", "badpass");
        userController.createUser(user);
        assertFalse(repository.findById(user.getUsername()).isPresent());
    }
```
These tests can be run from ***src/test/java/com.ktoufiquee.taskamanger/controller/UserControllerTest***. This script was ran in Intellij IDEA.

# Screenshots
<img src="/screenshots/Screenshot 2024-02-01 122558.png" alt="image" width="300" height="auto"><img src="/screenshots/Screenshot 2024-02-01 123247.png" alt="image" width="300" height="auto"> <br>
<img src="/screenshots/Screenshot 2024-02-01 123314.png" alt="image" width="600" height="auto"><img src="/screenshots/Screenshot 2024-02-01 123417.png" alt="image" width="600" height="auto"> <br>

