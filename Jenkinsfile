pipeline {
    agent any

    tools {
        // JDK 11'u kullan
        jdk 'JDK 11'
    }

    stages {
            stage('Which Java?') {
                steps {
                    sh 'java --version'
                }
            }
        stage('Build') {
            steps {
                script {
                    // Spring Boot projesini derle
                    sh './mvnw clean package'
                }
            }
        }
        stage('Dockerize') {
            steps {
                script {
                    // Docker image'ını oluştur
                    sh 'docker build -t hotelium-service .'
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    // Docker image'ını deploy et
                    sh 'docker run -p 8081:8081 -d hotelium-service'
                }
            }
        }
    }
}
