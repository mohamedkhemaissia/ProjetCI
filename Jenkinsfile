pipeline {

    agent {
        label "master"
    }

    tools {
        // Note: this should match with the tool name configured in your jenkins instance (JENKINS_URL/configureTools/)
        maven "Maven 3.6.3"
       
    }

    environment {
        // This can be nexus3 or nexus2
        NEXUS_VERSION = "nexus3"
        // This can be http or https
        NEXUS_PROTOCOL = "http"
        // Where your Nexus is running
        NEXUS_URL = "localhost:8543"
        // Repository where we will upload the artifact
        NEXUS_REPOSITORY = "Timesheet-Maven-Repository"
        // Jenkins credential id to authenticate to Nexus OSS
        NEXUS_CREDENTIAL_ID = "nexus-credentials"
        
        MVN = maven.initialiseMvn()
        BUILD_INFO = maven.newBuildInfo()

    }
       
    stages {
        
        stage("clone code") {
            steps {
                script {
                    // Let's clone the source
                    git 'https://github.com/mohamedkhemaissia/ProjetCI';
                }
            }
        }

        stage("mvn build") {
            steps {
                script {
                    // If you are using Windows then you should use "bat" step
                    // Since unit testing is out of the scope we skip them
                    //bat "mvn clean package"
                    MVN.run goals: "clean package", pom: "pom.xml", buildInfo: BUILD_INFO
                    //withCredentials([usernamePassword(credentialsId: 'test-creds', variable: 'TOKEN_VAR')]) {
                    //MVN.run goals: "org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar -Dsonar.host.url=http://localhost:8765 -Dsonar.login=${TOKEN_VAR}", pom: "pom.xml"
                    //}
                }
            }
        }

        stage("publish to nexus") {
            steps {
                MVN.deployer.deployArtifacts BUILD_INFO
            }
        }

    }
}
