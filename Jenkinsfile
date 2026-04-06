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
                bat 'docker rm -f resumescreeningapp-container || exit 0'
                bat 'docker run -d --name resumescreeningapp-container -p 8086:8080 resumescreeningapp'
            }
        }
    }
}