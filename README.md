mta-hosting-optimizer
================================

Requirements
------------
* [Java Platform (JDK) 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Apache Maven 3.x](http://maven.apache.org/)

Quick start
-----------
1. `mvn clean install`
2. `java -jar target/mta-hosting-optimizer-0.0.1-SNAPSHOT.jar`
3. Point your browser to http://localhost:8080/hosts/inefficient/
4. Refresh the page a number of times to confirm that the application is working consistently.



Accessing the Online Production Deployment
------------------------------------------
1. Load Balancer URL - http://138.197.8.199/hosts/inefficient/
2. Service 2 - http://159.65.251.93:8082/hosts/inefficient/
3. Service 1 - http://159.203.110.123:8082/hosts/inefficient/
4. Refresh these pages a number of times to confirm that the application is working consistently.

N.B: If any of these URLs are not reachable, kindly contact me.




Setting up the Online Production Deployment from scratch
--------------------------------------------------------
1. Setup and account and create a project on https://gitlab.com.
2. Create a Gitlab CI pipeline by creating a .gitlab-ci.yml file. 
3. Store your Digitalocean token in an environment variable named DIGITALOCEANTOKEN on your current working workstation (syntax for this varies depending on your OS).
4. Create a Rancher Digitalocean VM.

	docker-machine create --driver digitalocean --digitalocean-access-token $DIGITALOCEANTOKEN --digitalocean-image ubuntu-16-04-x64 --digitalocean-size 4gb --digitalocean-tags gitlab-rancher-example rancher-ui-host
	
5. Within the application's root directory, change directory to \docker-compose_rancher-ui-host and start the Rancher UI that you just created.
	
	cd docker-compose_rancher-ui-host
	cat docker-compose.yml
	docker-compose up -d
	
6. Create 3 Rancher hosts for the default environment to run our containers. One container will be run on each host, two for two instances of our application/service and one for a load balancer.
   Repeat the host creation step below thrice.
   
	Rancher UI > Default environment > Hosts > Add Host > Digitalocean. Add the Access token of Digitalocean ($DIGITALOCEANTOKEN) and create a machine.

7. 	If you are working on Windows, download and execute the windows executable of the Rancher CLI. This should open up a shell or console.
    RUN THE COMMANDS IN THE REMAINING STEPS IN THIS SHELL AND NOT ON YOUR DEFAULT OS SHELL.
	
8. 	Create access control, environment & API token

	Create a Rancher CLI access token through the UI of rancher (API > Keys) and store their details in the following environment variables on your current working workstation.
	
	RANCHER_URL=http://<<RANCHER_IP>>:8080
	RANCHER_ACCESS_KEY=<accessKey_of_account_api_key>
	RANCHER_SECRET_KEY=<secretKey_of_account_api_key>
	
9. From within the root directory of your application, deploy application stack manually.
   
	cat docker-compose.yml
	cat rancher-compose.yml

	rancher up
	
10. Deploy to Rancher from Gitlab
    
	Add environment variables to the Online Gitlab project:
	
	RANCHER_URL=http://RANCHER_HOST_IP:8080/
	RANCHER_ACCESS_KEY=...
	RANCHER_SECRET_KEY=...
	
	Add the following step to your .gitlab-ci.yml:

	stages:
	  - ...
	  - deploy

	digitalocean-deploy:
	  image: cdrx/rancher-gitlab-deploy
	  stage: deploy
	  script:
	  - upgrade --environment Default --stack mta-hosting-optimizer --service web --new-image registry.gitlab.com/chikelueoji/mta-hosting-optimizer
	  - upgrade --environment Default --stack mta-hosting-optimizer --service web2 --new-image registry.gitlab.com/chikelueoji/mta-hosting-optimizer



