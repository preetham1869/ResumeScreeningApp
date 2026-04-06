pipeline {
    agent any

    stages {

        stage('Clone Code') {
            steps {
                git 'https://github.com/preetham1869/ResumeScreeningApp.git'
            }
        }

        stage('Build Project') {
            steps {
                bat 'mvnw.cmd clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat 'docker build -t resumescreeningapp .'
            }
        }

        stage('Run Container') {
            steps {
                bat 'docker run -d -p 8086:8080 resumescreeningapp'
            }
        }
    }
}