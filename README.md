# helloworld

### Springboot application with zero downtime jenkins continuous deployment with terraform on AWS


## PreRequisites
    Jenkins, Ansible, Packer, Terraform, AWS infrastructure with VPC and subnets, AWS RDS mysql database
    The above prerequsite tools are created with manual/terraform code
    Custom docker image with jdk, maven, terraform is created for jenkins agent

## How to run
    Create pipeline job in jenkins with this repository as SCM
    Configure required crdentials for AWS account and Github repo
    Execute the build job





