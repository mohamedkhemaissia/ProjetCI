@Library('github.com/cloudogu/ces-build-lib@24c4f03')
import com.cloudogu.ces.cesbuildlib.*

node {

  Maven mvn = new MavenWrapper(this)

  catchError {

    stage('Checkout') {
      checkout scm
    }


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
        bat " mvn sonar:sonar -Dsonar.projectKey=CIpipeline  -Dsonar.host.url=http://localhost:9000  -Dsonar.login=e7ac7309bdda102fbcc11cfe5c6fcff1b463d409"
    }

        stage('Deploy') {
        bat " mvn clean package deploy:deploy-file -DgroupId=tn.esprit.spring -DartifactId=Timesheet-spring-boot-core-data-jpa-mvc-REST-1 -Dversion=0.0.2 -DgeneratePom=true -Dpackaging=war -DrepositoryId=deploymentRepo -Durl=http://localhost:8543/repository/Timesheet-Maven-Repository/ -Dfile=target/Timesheet-spring-boot-core-data-jpa-mvc-REST-1-0.0.2.jar"
    }
  }

 
}
