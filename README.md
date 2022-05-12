# Playframework
In this repository I demonstrate how to use Scala play framework to create basic form and use it to insert data and search data in database.
In home page there is a table filled with data from the database and you can delete data rows by clicking trash button.

### Required files and folders

For this project I have created and use folowing files,
  1. build.sbt
  2. conf/application.conf
  3. conf/routes
  4. app/controllers/HomeController
  5. app/views/home.scala.html
  6. public/stylesheets/main.css
  7. public/javascripts/main.js

#### 1. build.sbt
To add all the necessary dependencies to the project we will use this file.
Here we have add database library dependencies. 
Since we use `mysql` we need java database connection pool `jdbc` to manage databases and `mysql connector` to import database drivers.

>libraryDependencies ++= Seq(
  jdbc
)

>libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "5.1.41"
)

For other database connections visit [Play framework documentation](https://www.playframework.com/documentation/2.8.x/AccessingAnSQLDatabase#Databases-configuration)

#### 2. application.conf
To add configuration files such as database connection we will use this file.
This is importnent you need to change values according to your settings.

>db.default.driver=com.mysql.jdbc.Driver<br>
db.default.url="jdbc:mysql://localhost/playdb"<br>
db.default.username="db user"<br>
db.default.password="db user password"

update **url** value `playdb` to your database name I have use `playframework`,
**username** to your database user default `root`,
**password** to password of the above user default `""`.

Import this [Database file](../playframework.sql) to your MySQL database(prefered XAMMP).
And start `Apache` and `MySQL`

#### 3. routes
Used to tell which html file to load into the browser for specific url.
For example, `localhost:9000` to display root that means index.html page while `localhost:9000/home` to display home.html page.

Here what happens is it call the specific controller function mention to perform the task for each **GET** and **POST** request.
I have user default `HomeController` define functions in this project.

You need to define new routes accourding to your need.
And form data(query params) and path params will be passed to parameters of that function.

#### 4. HomeController.scala
We create functions here to give proper responses to user by communicating with view and model.
This is the most important file in the project which makes database connection,
execute queries, filter out required data and send them into the view.

#### 5. home.scala.html
To develop the webpage together with Scala codes, we have create this file.
Since this project build in concept of **SPA** you see all the CRUD in this page itself. 
This file support both Scala and html languages.

### Aditional Reading

#### Create a new play project

1. Java seed template
> sbt new playframework/play-java-seed.g8

2. Scala seed template
> sbt new playframework/play-scala-seed.g8

 You need an internet connection to download the necessary files and folders.
 it will ask a project name and your organization. 
 Do not afraid to just give any name for the organization,
 the important thing is the project name.
 
 To open and do modifications to project in text editor VS Code, Intellij Idea, etc. you need to install `Scala` plugin to your text editor. 

#### Run play project

1. Download and Install sbt. [Download page](https://www.scala-sbt.org/download.html)
2. Open `cmd` on project location type `sbt update` to update necessory settings, plugins, and libraries.
3. type `sbt` to start the sbt server
4. type `compile` to compile the project
5. type `run` to run the project
6. Open browser and type `http://localhost:9000`

♦♦♦ or you can type `sbt run` to do all the step from one command.

