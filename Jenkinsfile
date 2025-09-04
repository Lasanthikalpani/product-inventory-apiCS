pipeline {
    agent any

    tools {
        maven 'apache-maven-3.9.6'   // Use the name Jenkins knows
        jdk 'jdk-17'                 // Use the name Jenkins knows
    }
    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-credentials') // Jenkins credentials ID for Docker Hub
        IMAGE_NAME = "lasanthi821/product-inventory-api" // ← Change this!
        DOCKER_TAG = "latest"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', 
                url: 'https://github.com/Lasanthikalpani/product-inventory-apiCS.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'  // CHANGED: sh → bat
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml' // Publish test results
                }
            }
        }

        stage('Package') {
            steps {
                bat 'mvn package -DskipTests'  // CHANGED: sh → bat
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // For Windows, you might need to use bat for docker commands too
                    // Try this if the docker.build fails:
                    bat "docker build -t ${IMAGE_NAME}:${DOCKER_TAG} ."
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerhub-credentials') {
                        // For Windows, you might need to use bat for docker commands too
                        bat "docker push ${IMAGE_NAME}:${DOCKER_TAG}"
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