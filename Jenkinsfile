pipeline {
    agent any

    stages {

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