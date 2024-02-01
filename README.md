# Installation Guide

1. Clone the repository.
2. Set following values in src/main/resources/application.properties:
```
spring.datasource.url=	//mysql database url
spring.datasource.username=		//mysql database username
spring.datasource.password=		//mysql database password
webserver.origin=		//endpoint for web (fronted) eg: http://127.0.0.1:5050
```
3. Build the project.
4. Run the built project/ .jar file, this is our server.
5. Set following values in web/config.js:
```
endpoint: \\this value will be the endpoint the server is using eg: http://localhost:8080/
```
6. Start a server on web directory. (How to: [Link 1 (Using VS Code) Tested](https://www.geeksforgeeks.org/how-to-enable-live-server-on-visual-studio-code/)], [Link 2 Not Tested)](https://attacomsian.com/blog/local-web-server) )
7. Start the application using web_endpoint/Start.html eg: 127.0.0.1:5050/Start.html

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


