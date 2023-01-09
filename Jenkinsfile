//CODE_CHANGES = getGitChanges()
pipeline{
    agent any


    parameters{
        string(name: 'VERSION', defaultValue: '', description: 'version to deploy on prod')
        choice(name: 'VERSION', choices: ['1.1.0','1.2.0','1.3.0'], description: '')
        booleanParam(name: 'executeTest', defaultValue: true, description: '')
    }

    // tools{
    //     maven 'Maven'
    // }

    // ENVIRONMENT{
    //     NEW_VERSION = '1.3.0'
    //     SERVER_CREDENTIALS = CREDENTIALS('SERVER-CREDENTIALS')
    // }
    stages{
        stage("build"){
            when{
                expression{
                    params.executeTest
                }
            }
            // when{
            //     expression{
            //         BRANCH_NAME == 'dev' && CODE_CHANGES == true
            //     }
            // }
            steps{
                script{
                    echo "building the application.........."
                    echo "buildin new version1.3"
                }
            }
        }
        stage("test"){
            // when{
            //     expression{
            //         BRANCH_NAME == 'dev' || BRANCH_NAME == 'main'
            //     }
            // }
            steps{
                script{
                    echo "testing the application............"
                }
            }
        }
        stage("deploy"){
            when{
                expression{
                    echo "deploying version ${params.VERSION}"
                }
            }
            steps{
                script{
                    echo "deploying the application........"
                    // echo "deploy with credentials ${SERVER_CREDENTIALS}"
                    // sh "${SERVER_CREDENTIALS}"


                    // withCredentials([
                    //     usernamePassword(credentials: 'SERVER_CREDENTIALS', usernameVariable: USER, passwordVariable: PWD)
                    // ]){
                    //     sh "some script ${USER} ${PWD}"
                    // }

                }
            }
        }
    }
   
}