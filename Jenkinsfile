pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'Java17'
    }

    stages {

        stage('Clone Code') {
            steps {
                git 'https://github.com/preetham1869/ResumeScreeningApp.git'
            }
        }

        stage('Build Project') {
            steps {
                bat 'mvn clean package -DskipTests'
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