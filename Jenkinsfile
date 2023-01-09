//CODE_CHANGES = getGitChanges()
pipeline{
    agent any
    environment{
        NEW_VERSION = '1.3.0'
    }
    stages{
        stage("build"){
            // when{
            //     expression{
            //         BRANCH_NAME == 'dev' && CODE_CHANGES == true
            //     }
            // }
            steps{
                script{
                    echo "building the application.........."
                    echo "buildin new version ${NEW_VERSION}"
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
            steps{
                script{
                    echo "deploying the application........"
                }
            }
        }
    }
   
}