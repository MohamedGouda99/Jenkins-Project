#!/usr/bin/env groovy
// def buildJar(){
//     echo "building the application"
//     sh 'mvn package'
// }

def deploy(){
    
    def shellCmd = "bash ./server-cmds.sh ${IMAGE_NAME}"
    def ec2_instance = "ubuntu@54.87.29.255"

    sshagent(['ec2-server-key']) {
        sh "scp server-cmds.sh ${ec2_instance}:/home/ubuntu"
        sh "scp docker-compose.yaml ${ec2_instance}:/home/ubuntu"
        sh "ssh -o StrictHostKeyChecking=no ubuntu@54.87.29.255 ${shellCmd}"
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

