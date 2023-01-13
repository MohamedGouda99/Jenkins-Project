library identifier: 'jenkins-shared-library@master', retriever: modernSCM(
    [$class: 'GitSCMSource', remote: 'https://gitlab.com/MohamedGouda99/jenkins-shared-library.git', credentialsId: 'github-credentials']
)
// @Library('jenkins-shared-library')
def gv

pipeline{
    agent any
    tools{
        maven 'maven:3.8.7'
    }
    stages{
        stage("Incerement Version"){
            steps{
                script{
                    echo "Increment App Version..."
                    incrementVersion()
                }
            }
        }
        stage("init"){
            steps{
                script{
                    gv = load 'script.groovy'
                }
            }
        }
        stage("build jar"){
            steps{
                script{
                   buildJar()
                }
            }
        }
        
        stage("build image and push image"){
            steps{
                script{
                        buildImage 'gouda99/my-repo:jma-11.0'
                        dockerLogin()
                        dockerPush 'gouda99/my-repo:jma-11.0'
                    }
                }
        }
        
        
        stage("deploying"){
            steps{
                script{
                  gv.deploy()
                }
            }
        }
    }
}       

