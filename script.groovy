#!/usr/bin/env groovy
def buildJar(){
     echo "building the application"
     sh 'mvn package'
}

def deploy(){
    echo "deploy the application for $BRANCH_NAME"
}


def buildImage(){
    echo "building Docker Image"
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]){
        sh "docker build -t gouda99/my-repo:${IMAGE_NAME} ."
        sh "docker login -u $USER -p $PASS"
        sh "docker push gouda99/my-repo:${IMAGE_NAME}"
    }
}
// def incrementVersion(){
//     echo "incrementing app version..."
//     sh "mvn build-helper:parse-version versions:set \
//         -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} versions:commit"

//         def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
//         def version = matcher[0][1]
//         env.IMAGE_NAME = "Jma-$version-$BUILD_NUMBER"
// }
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

return this

