pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                script {
                    // Spring Boot projesini derle
                    sh './mvnw clean package'
                }
            }
        }
        stage('Stop and Remove Old Container') {
            steps {
                script {
                    // Eğer varsa önceki Docker konteynerini durdur ve sil
                    sh 'docker stop hotelium-container || true'
                    sh 'docker rm -f hotelium-container || true'
                }
            }
        }
       stage('Remove Old Image') {
            steps {
                script {
                    // Eğer varsa önceki Docker image'ını sil
                    sh 'docker rmi -f hotelium-service || true'
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
                    sh 'docker run -p 8081:8081 -d --name hotelium-container hotelium-service'
                }
            }
        }
    }
}
