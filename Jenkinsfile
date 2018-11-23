pipeline {
    environment {
        IMAGE = 'easyhealth:latest'
        ECRURL = 'http://999999999999.dkr.ecr.eu-central-1.amazonaws.com'
        ECRCRED = 'ecr:eu-central-1:tap_ecr'
    }
    agent any
    stages {
	    stage('Checkout') { 
	    	steps{
                checkout scm  
	      	}
	   	}
        stage('Analise Sonar'){
            steps{
                withSonarQubeEnv('sonar') {
                    sh "mvn clean package sonar:sonar"
                }
            }
        }
        stage("Quality Gate") { 
            steps{
                timeout(time: 30, unit: 'MINUTES') { 
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        stage('Testes') { 
            steps {
                sh "mvn clean clover:setup test clover:aggregate clover:clover"
                step([
				    $class: 'CloverPublisher',
				    cloverReportDir: 'target/site',
				    cloverReportFileName: 'clover.xml',
				    healthyTarget: [methodCoverage: 70, conditionalCoverage: 70, statementCoverage: 70], // optional, default is: method=70, conditional=80, statement=80
				    unhealthyTarget: [methodCoverage: 50, conditionalCoverage: 50, statementCoverage: 50], // optional, default is none
				    failingTarget: [methodCoverage: 0, conditionalCoverage: 0, statementCoverage: 0]     // optional, default is none
				  ])
            }
        }
        stage('Build and Implantação') { 
            steps{
                script{
                    docker.withRegistry("http://.dkr.ecr.us-east-2.amazonaws.com", "ecr:us-east-2:tap_ecr") {
                        def customImage = docker.build("easyhealth-rest-api:latest")
                        customImage.push()
                    }
                }
            }
        }
        stage('Resultados') {
            steps {
                junit '**/target/surefire-reports/TEST-*.xml'
                archive 'target/*.jar'
            }
       }
    }
    post{
        always{
           sh "docker rmi easyhealth-rest-api:latest | true"
        }
    }
}
