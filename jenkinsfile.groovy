node {
    env.IMAGE_NAME = "selenium"
    env.TAG_NAME = "local"
    env.REMOTE_GIT_URL = "https://$GIT_USER:$GIT_PASSWD@git.artezio.net/ndokutovich/art-hr.git"

    stage("Cloning Git") {
        git changelog: false, branch: "dev", credentialsId: "art-hr-service", url: REMOTE_GIT_URL
    }

    stage("Building image") {
        dir("gatling-build") {
            sh '''
                cp -R ../src/main/docker/gatling/. .
                cp -R ../src/gatling/ .
                docker build -t $IMAGE_NAME:$TAG_NAME .
            '''
            deleteDir()
        }
    }

    stage("Run Gatling") {
        sh "docker run --rm -v $WORKSPACE/gatling-results:/opt/gatling/results -e JAVA_OPTS='-Dusers=$USERS -Dduration=$DURATION -DserverUrl=$SERVER_URL -DkeycloakServerUrl=$KEYCLOAK_SERVER_URL -DkeycloakRealm=$KEYCLOAK_REALM -DkeycloakUser=$KEYCLOAK_USER -DkeycloakPassword=$KEYCLOAK_PASSWORD' -e SIMULATIONS=$SIMULATIONS $IMAGE_NAME:$TAG_NAME"
    }

    stage("Cleaning up") {
        sh "docker rmi $IMAGE_NAME:$TAG_NAME"
    }
}
