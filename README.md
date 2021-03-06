# Nurtrition Recommender Team 70
The java app for CIT591 of Team 70

# Configuration
1. First you will have to clone the project to a folder, if you see a "pom.xml" in the folder please delete it. (do **not** delete the pom_file folder!) and then create a new Maven project.
<img src='https://github.com/qiz216/sparkjavaproject/blob/master/images/1.maven.png'>
2. Remember to click "create a simple project", and change the workspace to the folder you just cloned from github. Click next. You can create whatever name you like for group-id and aircraft-id.
<img src='https://github.com/qiz216/sparkjavaproject/blob/master/images/2.configure_maven.png'>

3. This is the structure you will see after the project is created.
<img src='https://github.com/qiz216/sparkjavaproject/blob/master/images/3.pomfile.png'>

4. Open the **pom.xml** 
<img src='https://github.com/qiz216/sparkjavaproject/blob/master/images/4.replacepom.png'>

5. Open the **pom_file/pom.xml** and copy all the parts after "<dependencies>" and and paste it after the "</version>" in the **pom.xml** file.
<img src='https://github.com/qiz216/sparkjavaproject/blob/master/images/5.copy.png'>

6. right click on the **pom.xml** file and go to **Maven -> Update Project** and update it.
<img src='https://github.com/qiz216/sparkjavaproject/blob/master/images/6.updatemaven.png'>

7. If you do not have the Junit library in classpath, right click on the project and go to **properties**, in the **Java build path -> Libraries** click **Add Library** and add Junit.
<img src='https://github.com/qiz216/sparkjavaproject/blob/master/images/7.enablejunit.png'>
<img src='https://github.com/qiz216/sparkjavaproject/blob/master/images/8.junit.png'>

8. Go to **UserInterface -> Main.java** and run it. After it is running, go to http://localhost:4567/
<img src='https://github.com/qiz216/sparkjavaproject/blob/master/images/9.runMain.png'>

# How to use the App

# Technology Used
- **Front end**
  - Java spark Framework
  	- We used different handlers for this purpose.
- **Deployment**
  - The web application is deployed to a server using heroku
  - It is mapped to a domain using cname.
- **Website**
  - <a href="http://www.qizhaolouis.com">Nutrition App</a>
  - The web application is deployed to a server using Heroku
  - It is mapped to a domain using cname.

# Team Member
- Deyi Zhang: `deyi@seas.upenn.edu`
- Xixi Zhou: `xixizhou@seas.upenn.edu`
- Qi Zhao: `qiz216@seas.upenn.edu`
