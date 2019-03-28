#####################################
###                               ###
### MakeFile for Laravel Skeleton ###
###                               ###
#####################################


help: #prints list of commands
	@cat ./makefile | grep : | grep -v "grep"


#####################################
###                               ###
###       Start/stop project      ###
###                               ###
#####################################

start: #start docker container
	@sudo docker-compose up -d


stop: #stop docker container
	@sudo docker-compose down

