pipeline {
    agent any

    tools {
       maven "Maven 3.6.3"
    }

    node {

  Maven mvn = new MavenWrapper(this)

  catchError {

      
        stage('Build') {
        bat "mvn clean install -DskipTests"
    }

    stage('Unit Test') {
        bat "mvn test"
    }

     stage('Integration Test') {
        bat " mvn verify -DskipUnitTests"
    }

    stage('Static Code Analysis') {
        bat " mvn sonar:sonar   -Dsonar.projectKey=Integration_continue  -Dsonar.host.url=http://localhost:9000   -Dsonar.login=5f39ad655a6f237bb527da65b432911a895c12a7 "
    }

        stage('Deploy') {
        bat " mvn clean package deploy:deploy-file -DgroupId=tn.esprit.spring -DartifactId=Timesheet-spring-boot-core-data-jpa-mvc-REST-1 -Dversion=0.0.2 -DgeneratePom=true -Dpackaging=war -DrepositoryId=deploymentRepo -Durl=http://localhost:8543/repository/Timesheet-Maven-Repository/ -Dfile=target/Timesheet-spring-boot-core-data-jpa-mvc-REST-1-0.0.2.jar"
    }

    }
}
}

