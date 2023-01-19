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
def incrementVersion(){
    echo "incrementing app version..."
    sh "mvn build-helper:parse-version versions:set \
          -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} versions:commit"

    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
    def version = matcher[0][1]
    env.IMAGE_NAME = "$version-$BUILD_NUMBER"
}
def commitVersion(){
    echo "commit version update"
    withCredentials([usernamePassword(credentialsId: 'github-credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]){
        sh 'git config --global user.email "jenkins@example.com"'
        sh 'git config --global user.name "jenkins"'

        sh 'git status'
        sh 'git branch'
        sh 'git config --list'

        sh "git remote set-url origin https://${USER}:${PASS}@gitlab.com/MohamedGouda99/java-mvn-project.git"
        sh 'git add .'
        sh 'git commit -m "CI: Version Bump"'
        sh 'git push origin HEAD:main'

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

