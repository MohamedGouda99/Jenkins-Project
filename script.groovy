def buildJar(){
    echo "building the application"
    sh 'mvn package'
}

def deploy(){
    echo "deploy the application"
}

def buildImage(){
    echo "building Docker Image"
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]){
        sh 'docker build -t gouda99/my-repo:jma-2.0 .'
        sh "echo $PASS | docker login -u $USER -p $PASS --password-stdin"
        sh 'docker push gouda99/my-repo:jma-2.0'

    }

    

}

