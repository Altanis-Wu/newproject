pipeline {
    agent none
    stages('Build'){
        agent{
            docker{
                image 'maven:3.8.1-adoptopenjdk-11'
            }
        }
        post {
            always {
                junit 'target/surefire-reports/*.xml'
            }
        }
        steps {
            sh 'mvn -DskipTests -Pprod clean package'
            sh 'mvn -Pprod test'
            stash(includes: 'target/*.jar', name: 'jar')
            archiveArtifacts 'target/*.jar'
        }
    }
    stages('Deploy to Docker'){
        agent any
        steps{
            unstash 'jar'
            sh 'docker-compose down'
            sh 'docker build -f Dockerfile-mysql -t newproject/mysql .'
            sh 'docker build -f Dockerfile-app -t newproject/app .'
            sh 'docker-compose up -d'
        }
    }
}
