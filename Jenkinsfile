pipeline {
    agent any

    tools {
        maven 'M3'  // Requires Maven to be configured in Jenkins
        jdk 'jdk17' // Requires JDK 17 to be configured in Jenkins
    }

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-credentials') // Jenkins credentials ID for Docker Hub
        IMAGE_NAME = "your-dockerhub-username/product-inventory-api" // ← Change this!
        DOCKER_TAG = "latest"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', 
                url: 'https://github.com/your-username/product-inventory-api.git' // ← Change this!
                }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml' // Publish test results
                }
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package -DskipTests' // Skip tests since they already ran
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${IMAGE_NAME}:${DOCKER_TAG}")
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerhub-credentials') {
                        docker.image("${IMAGE_NAME}:${DOCKER_TAG}").push()
                    }
                }
            }
        }

        stage('Deploy (Optional)') {
            steps {
                script {
                    // This would SSH into a server and deploy the new image
                    // For now, we'll just print a message
                    echo 'Deployment would happen here to staging/production'
                }
            }
        }
    }

    post {
        always {
            cleanWs() // Clean up workspace
        }
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed! Check the logs.'
        }
    }
}