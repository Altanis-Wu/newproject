pipeline {
    agent {
        docker {
            image 'maven:3.8.1-adoptopenjdk-11' 
            args '-v /root/.m2:/root/.m2' 
        }
    }
    stages {
        stage('Build') { 
            steps {
                sh 'mvn -B -DskipTests clean package' 
            }
        }
        stage('Docker Build MySQL'){
            steps{
                sh 'docker-compose down'
                sh 'docker build -f Dockerfile-mysql -t newproject/mysql .'
                sh 'docker build -f Dockerfile-app -t newproject/app .'
                sh 'docker-compose up -d'
            }
        }
    }
}
