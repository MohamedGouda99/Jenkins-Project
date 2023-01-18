#!/usr/bin/env groovy
// def buildJar(){
//     echo "building the application"
//     sh 'mvn package'
// }

def deploy(){
    echo "deploy the application for my... $BRANCH_NAME"
}

// def buildImage(){
//     echo "building Docker Image"
//     withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]){
//         sh 'docker build -t gouda99/my-repo:jma-4.0 .'
//         sh "docker login -u $USER -p $PASS"
//         sh 'docker push gouda99/my-repo:jma-4.0'
//     }

return this

