@Library('github.com/cloudogu/ces-build-lib@24c4f03')
import com.cloudogu.ces.cesbuildlib.*

node {

  Maven mvn = new MavenWrapper(this)

  catchError {

    stage('Checkout') {
      checkout scm
    }

    initMaven(mvn)

    stage('Build') {
     bat " mvn clean install -DskipTests "
    }

    stage('Unit Test') {
      bat " mvn  test "
    }

    stage('Integration Test') {
     bat " mvn  verify -DskipUnitTests "
    }

    stage('Static Code Analysis') {
     bat " mvn sonar:sonar -Dsonar.projectKey=CIpipeline -Dsonar.host.url=http://localhost:9000 -Dsonar.login=52356a06932b155a00ba0b5c3ed232a6669f16a4 "
    }

    stage('Deploy') {
 bat " mvn clean package deploy:deploy-file -DgroupId=tn.esprit.spring -DartifactId=Timesheet-spring-boot-core-data-jpa-mvc-REST-1 -Dversion=0.0.2 -DgeneratePom=true -Dpackaging=war -DrepositoryId=deploymentRepo -Durl=http://localhost:8543/repository/Timesheet-Maven-Repository/ -Dfile=target/Timesheet-spring-boot-core-data-jpa-mvc-REST-1-0.0.2.jar"
    }
  }
}


void initMaven(Maven mvn) {

  if ("master".equals(env.BRANCH_NAME)) {

    echo "Building master branch"
    mvn.additionalArgs = "-DperformRelease"
    currentBuild.description = mvn.getVersion()
  }
}
