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



    stage('Deploy') {
 mvn clean package deploy:deploy-file -DgroupId=tn.esprit.spring -DartifactId=Timesheet-spring-boot-core-data-jpa-mvc-REST-1 -Dversion=0.0.2 -DgeneratePom=true -Dpackaging=war -DrepositoryId=deploymentRepo -Durl=http://localhost:8543/repository/Timesheet-Maven-Repository/ -Dfile=target/Timesheet-spring-boot-core-data-jpa-mvc-REST-1-0.0.2.jar
    }
  }

  junit allowEmptyResults: true, testResults: '**/target/surefire-reports/TEST-*.xml, **/target/failsafe-reports/*.xml'

  mailIfStatusChanged(new Git(this).commitAuthorEmail)
}

boolean preconditionsForDeploymentFulfilled() {
  if (isBuildSuccessful() &&
      !isPullRequest() &&
      shouldBranchBeDeployed()) {
    return true
  } else {
    echo "Skipping deployment because of branch or build result: currentResult=${currentBuild.currentResult}, " +
      "result=${currentBuild.result}, branch=${env.BRANCH_NAME}."
    return false
  }
}

private boolean shouldBranchBeDeployed() {
  return env.BRANCH_NAME == 'master' || env.BRANCH_NAME == 'develop'
}

private boolean isBuildSuccessful() {
  currentBuild.currentResult == 'SUCCESS' &&
    // Build result == SUCCESS seems not to set be during pipeline execution.
    (currentBuild.result == null || currentBuild.result == 'SUCCESS')
}

void initMaven(Maven mvn) {

  if ("master".equals(env.BRANCH_NAME)) {

    echo "Building master branch"
    mvn.additionalArgs = "-DperformRelease"
    currentBuild.description = mvn.getVersion()
  }
}
