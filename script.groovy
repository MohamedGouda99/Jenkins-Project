#!/usr/bin/env groovy
// def buildJar(){
//     echo "building the application"
//     sh 'mvn package'
// }

def deploy(){
    
    def dockerCmd = 'docker run -d -p 8080:8080 gouda99/my-repo:jma-500.0'
    sshagent(['ec2-server-key']) {
        sh "ssh -o StrictHostKeyChecking=no ubuntu@54.87.29.255 ${dockerCmd}"
    }
}

// def buildImage(){
//     echo "building Docker Image"
//     withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]){
//         sh 'docker build -t gouda99/my-repo:jma-4.0 .'
//         sh "docker login -u $USER -p $PASS"
//         sh 'docker push gouda99/my-repo:jma-4.0'
//     }

return this

