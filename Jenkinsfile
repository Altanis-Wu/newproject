pipeline {
    agent none
    stages{
        stage('Build'){
            agent{
                docker{
                    image 'maven:3.8.1-adoptopenjdk-11'
                }
            }
            steps {
                sh 'mvn -DskipTests -Pprod clean package'
                stash(includes: 'target/*.jar', name: 'jar')
                archiveArtifacts 'target/*.jar'
            }
        }
        stage('Build the Image'){
            agent any
            steps{
                unstash 'jar'
                sh 'docker-compose down'
                sh 'docker build -f Dockerfile-mysql -t newproject/mysql .'
                sh 'docker build -f Dockerfile-app -t newproject/app .'
            }
        }
        stage('Deploy to Docker'){
            agent any
            steps{
                sh 'docker-compose up -d'
            }
        }
    }
}
