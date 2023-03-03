pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh '''echo "build"
'''
      }
    }

    stage('QA Testing') {
      parallel {
        stage('QA Testing') {
          steps {
            sh 'echo "qa testing"'
          }
        }

        stage('API Test on QA') {
          steps {
            sh 'echo "API Test on QA"'
          }
        }

      }
    }

    stage('Deploy on stage') {
      steps {
        sh 'echo "deploy on stage"'
      }
    }

    stage('Stage testing') {
      steps {
        sh 'echo "stage testing"'
      }
    }

    stage('Deploy PROD') {
      parallel {
        stage('Deploy PROD') {
          steps {
            sh 'echo "deploy prod"'
          }
        }

        stage('Backend Deployment') {
          steps {
            sh 'echo "backend deployment"'
          }
        }

        stage('Env Config') {
          steps {
            sh 'echo "setup environment prod"'
          }
        }

      }
    }

    stage('Sanity Test on PROD') {
      steps {
        sh 'echo "sanity test on prod"'
      }
    }

  }
}