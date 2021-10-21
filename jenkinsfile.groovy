node {
    env.IMAGE_NAME = "selenium"
    env.TAG_NAME = "local"
    env.REMOTE_GIT_URL = "https://github.com/iLisaid/seleniumMaven.git"

    stage("Cloning Git") {
        git changelog: false, branch: "master", url: REMOTE_GIT_URL
    }

    stage("Building image") {
        sh '''
                docker build -t $IMAGE_NAME:$TAG_NAME ./seleniumMaven
            '''
    }

    stage("Run Gatling") {
        sh "docker run --rm $IMAGE_NAME:$TAG_NAME"
    }

    stage("Cleaning up") {
        sh "docker rmi $IMAGE_NAME:$TAG_NAME"
    }
}
