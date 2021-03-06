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
        bat "mvn sonar:sonar -Dsonar.projectKey=Integration_continue1 -Dsonar.host.url=http://localhost:9000 -Dsonar.login=f65a451b096f092829759581458d1e98e005e2a6"
    }

        stage('Deploy') {
        bat " mvn clean package deploy:deploy-file -DgroupId=tn.esprit.spring -DartifactId=Timesheet-spring-boot-core-data-jpa-mvc-REST-1 -Dversion=0.0.2 -DgeneratePom=true -Dpackaging=war -DrepositoryId=deploymentRepo -Durl=http://localhost:8543/repository/Timesheet-Maven-Repository/ -Dfile=target/Timesheet-spring-boot-core-data-jpa-mvc-REST-1-0.0.2.jar"
    }
  }

 
}
