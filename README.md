# helloworld

### Springboot application with zero downtime jenkins continuous deployment with terraform on AWS


## PreRequisites
    Jenkins, Ansible, Packer, Terraform, AWS infrastructure with VPC and subnets, AWS RDS mysql database
    The above prerequsite tools are created with manual/terraform code
    Custom docker image with jdk, maven, terraform is created for jenkins agent

## How to run
    Configure src/main/resources/application.properties with appropriate DB details
    Create pipeline job in jenkins with this repository as SCM
    In Jenkinsfile replace "test" with your job name in the below script
    ```
        docker {
          image 'arulkumar1967/build-arul-container:latest'
          args '-u root:sudo -v $HOME/workspace/test:/test'
        }
    ```
    Configure required crdentials for AWS account and Github repo
    Execute the build job

## Testing application

### Create table in RDS mysql instance with mysql client with below ddl command
```
CREATE TABLE users ( id smallint unsigned not null auto_increment, user_name varchar(20), date_of_birth varchar(20), constraint pk_example primary key (id) );

```

### Sample Test Scripts

Replace ip address appropriately

```
Command:
curl -X PUT \
  http://34.244.214.224:8090/hello/Arul \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -d '{
    "dateOfBirth": "2007-06-12"
}'
Expected result:
No Response message. Row should be created in users table for Arul

Command:
curl -X GET \
  http://34.244.214.224:8090/hello/Arul \
  -H 'Cache-Control: no-cache'
 Expected Result:
 Hello Arul Your birthday is in N Day(s)

Command:
curl -X PUT \
  http://34.244.214.224:8090/hello/Arulk \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -d '{
    "dateOfBirth": "2007-04-24"
}'
Expected result:
No Response message. Row should be created in users table for Arulk

Command:
curl -X GET \
  http://34.244.214.224:8090/hello/Arulk \
  -H 'Cache-Control: no-cache'
 Expected Result:
 Hello Arulk Happy Birthday!

Command:
curl -X PUT \
  http://34.244.214.224:8090/hello/Arulk12 \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -d '{
    "dateOfBirth": "2007-04-24"
}'
Expected result:
Arulk12 must contains only letters.

Command:
curl -X PUT \
  http://34.244.214.224:8090/hello/Arulku \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -d '{
    "dateOfBirth": "2019-04-30"
}'
Expected result:
Date 2019-04-30 must be a date before the today date


```









