JobsTracker
===========

#### JobsTracker  Project


JobsTracker is a very simple Jobs Monitoring Application. 

#### Dependencies

Jobstracker depends on the following Open-Source frameworks and Libraries

1. Spring Framework and etc through Spring Boot
2. AngularJS
3. Bootstrap
4. Maven
5. NodeJS
6. PostgreSQL (can be modified to use other DBMS)
7. Thanks to JHipster for making these easier.

#### Installing Dependencies (applicable for Windows only)


1. Install [JAVA SDK 1.7+](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html)
  * Note: Install JDK and not the JRE
  * Java 8 sdk can also be used
  * If you type the command `java -version` in your terminal and did not result to a `Bad command or Filename` then Java was aready installed
2. Install [Maven](http://mirror.rise.ph/apache/maven/maven-3/3.3.3/binaries/apache-maven-3.3.3-bin.zip)   
  * Extract the Zip file and copy to your c: drive
  * Make sure maven.bat is can be found in your path.
  * Set the PATH Environmental Variables ![alt text](https://github.com/albertoclarit/JobsTracker/blob/master/docs/setpath.png "Set Path")
  * Add JAVA_HOME to your environmental variables
![alt text](https://github.com/albertoclarit/JobsTracker/blob/master/docs/setjavahome.png "Set Path")
  * in the command line type `mvn` it should NOT be `Bad Command or Filename`. If it shows
   `[INFO] Scanning for projects..` then maven was installed successfully
   
3. Install [NodeJs](https://nodejs.org/en/) and install the recommended installer
  * make sure typing `node --version` will not result to an error message
      
4. Installing NodeJS packages
  * Install Grunt by typing `npm install -g grunt-cli`
  * Install Bower by typing `npm install -g bower`

5. Download and Install [Postgresql](http://www.enterprisedb.com/products-services-training/pgdownload#windows)
  * Please specify for now the username is "postgres" and password is "password"
  * After installation, in PGAdmin create a database named "jobtracker"
  
6. Install Git for Windows at [link](https://git-scm.com/download/win)
  * Command `git` should not result to an error
  
7. Clone JobTracker project
  * Open terminal
  * Go to your root drive by typing  cd \
  * type `git clone https://github.com/albertoclarit/JobsTracker.git Jobtracker`

8. Install JobTracker Node Components
  * From root of c: type `cd Jobtracker`
  * type `bower install`
  * there are packages that are in conflict and needs to be answered in the choices. 
  Choose the option that is needed by JobTracker
  ![alt text](https://github.com/albertoclarit/JobsTracker/blob/master/docs/choosebower.png "Choose")
  
   
9. Build the project
  * Type `mvn -Pprod package`
  
10.) Run the project
  * from Jobtracker folder type `cd target`
  * You should find the JobTracker.war in that folder
  * run it by typing `java -jar JobTracker.war  --spring.profiles.active=prod`



#### During Development

1. In Intellij,Eclipse or Netbeans just open the Diretory from the IDE.
   * Point your runnable static method in Application class.
   * Run your Application (it depends on your IDE how) Please research on their corresponding website how
2. Running Angular JS in debug mode   
   * type `grunt serve` in the JobTracker folder.
    
    
    


