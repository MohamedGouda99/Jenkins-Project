// //CODE_CHANGES = getGitChanges()
// pipeline{
//     agent any


//     parameters{
//         string(name: 'VERSION', defaultValue: '', description: 'version to deploy on prod')
//         choice(name: 'VERSION', choices: ['1.1.0','1.2.0','1.3.0'], description: '')
//         booleanParam(name: 'executeTest', defaultValue: true, description: '')
//     }

//     // tools{
//     //     maven 'Maven'
//     // }

//     // ENVIRONMENT{
//     //     NEW_VERSION = '1.3.0'
//     //     SERVER_CREDENTIALS = CREDENTIALS('SERVER-CREDENTIALS')
//     // }
//     stages{
//         stage("build"){
//             when{
//                 expression{
//                     params.executeTest
//                 }
//             }
//             // when{
//             //     expression{
//             //         BRANCH_NAME == 'dev' && CODE_CHANGES == true
//             //     }
//             // }
//             steps{
//                 script{
//                     echo "building the application.........."
//                     echo "buildin new version1.3"
//                 }
//             }
//         }
//         stage("test"){
//             // when{
//             //     expression{
//             //         BRANCH_NAME == 'dev' || BRANCH_NAME == 'main'
//             //     }
//             // }
//             steps{
//                 script{
//                     echo "testing the application............"
//                 }
//             }
//         }
//         stage("deploy"){
//             when{
//                 expression{
//                     echo "deploying version ${params.VERSION}"
//                 }
//             }
//             steps{
//                 script{
//                     echo "deploying the application........"
//                     // echo "deploy with credentials ${SERVER_CREDENTIALS}"
//                     // sh "${SERVER_CREDENTIALS}"


//                     // withCredentials([
//                     //     usernamePassword(credentials: 'SERVER_CREDENTIALS', usernameVariable: USER, passwordVariable: PWD)
//                     // ]){
//                     //     sh "some script ${USER} ${PWD}"
//                     // }

//                 }
//             }
//         }
//     }
   
// }
pipeline{
    agent any
    tools{
        maven 'Maven'
    }
    stages{
        stage("build jar"){
            steps{
                script{
                    echo "building the application"
                    sh 'mvn package'
                }
            }
        }
        
        stage("build image"){
            steps{
                script{
                    echo "building Docker Image"

                    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]){
                        sh 'docker build -t gouda99/my-repo:jma-2.0 .'
                        sh "echo $PASS | docker login -u $USER -p $PASS --password-stdin"
                        sh 'docker push gouda99/my-repo:jma-2.0'
                    }
                }
            }
        }
        
        stage("deploying"){
            steps{
                script{
                    echo "deploying the application.........."
                }
            }
        }

        
    }
}