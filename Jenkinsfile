pipeline {
  agent {
       docker {
          image 'arulkumar1967/build-arul-container:latest'
          args '-u root:sudo -v $HOME/workspace/test:/test'
        }
    }
  environment {
        EMAIL_RECIPIENTS = 'arulkr1967@gmail.com'
  }
  stages {

    stage('Build') {
      steps {
          sh 'mvn package'
          sh 'rm -f packer/*.jar'
          sh 'cp -r target/*.jar packer'
        /* withAWS(endpointUrl:'https://s3.amazonaws.com', credentials:'ada90a34-30ef-47fb-8a7f-a97fe69ff93f'){
			s3Upload(file:'helloworld-1.0.0.jar', bucket:'arulrevoulttest', path:'revhelloworld/target/helloworld-1.0.0')
		} */
		}

    }
    stage('Create Packer AMI') {
        steps {
          withCredentials([
            usernamePassword(credentialsId: 'ada90a34-30ef-47fb-8a7f-a97fe69ff93f', passwordVariable: 'AWS_SECRET', usernameVariable: 'AWS_KEY')
          ]) {
            sh 'packer build -var aws_access_key=${AWS_KEY} -var aws_secret_key=${AWS_SECRET} packer/packer.json'
          }
       }
    }
    stage('AWS Deployment') {
      steps {
          /* withAWS(region:'eu-west-1',credentials:'ada90a34-30ef-47fb-8a7f-a97fe69ff93f')\
                {
                    s3Download(file: '${JENKINS_HOME}/workspace/helloworld/terraform', bucket: 'arulrevoulttest', path: 'terraform.tfstate', force:true)
                } */
          withCredentials([
            usernamePassword(credentialsId: 'ada90a34-30ef-47fb-8a7f-a97fe69ff93f', passwordVariable: 'AWS_SECRET', usernameVariable: 'AWS_KEY'),
            usernamePassword(credentialsId: '2facaea2-613b-4f34-9fb7-1dc2daf25c45', passwordVariable: 'REPO_PASS', usernameVariable: 'REPO_USER'),
          ]) {
            sh '''
               cd terraform
               terraform init -var access_key=${AWS_KEY} -var secret_key=${AWS_SECRET}
               terraform apply -auto-approve -var access_key=${AWS_KEY} -var secret_key=${AWS_SECRET}
            '''
        }
      }
    }
  }
  
  }
