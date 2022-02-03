#!/bin/sh

docker run --name mongoBooks -p27017:27017 \
    -e MONGO_INITDB_ROOT_USERNAME=mongoadmin \
    -e MONGO_INITDB_ROOT_PASSWORD=Testing123 \
    -d -v $HOME/Projects/docker/mongoBooks/data:/data/db \
    -v $HOME/Projects/docker/mongoBooks/logs:/logs \
    -v $HOME/Projects/docker/mongoBooks/tmp:/tmp \
    jmreif/mongodb
#TEST docker run --name mongoBooks -p27017:27017 -v $HOME/Projects/docker/mongoBooks/data:/data/db -v $HOME/Projects/docker/mongoBooks/logs:/logs jmreif/mongodb
docker ps
