library identifier: 'jenkins-shared-library@master', retriever: modernSCM(
    [$class: 'GitSCMSource', remote: 'https://gitlab.com/MohamedGouda99/jenkins-shared-library.git', credentialsId: 'github-credentials']
)
// @Library('jenkins-shared-library')
def gv

pipeline{
    agent any
    tools{
        maven 'maven-3.8.7'
    }

    stages{
        stage("increment Version"){
            steps{
                script{
                    gv.incrementVersion()
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
                        buildImage(env.IMAGE_NAME)
                        dockerLogin()
                        dockerPush(env.IMAGE_NAME)
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
        stage("commit new image version"){
            steps{
                script{
                    gv.commitVersion()
                }
            }
        }
    }
}       

