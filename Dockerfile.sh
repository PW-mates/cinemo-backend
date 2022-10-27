#!/bin/bash

docker build . -t cinemo-backend
docker stop cinemo-backend; docker rm cinemo-backend;
docker run -d -it --name cinemo-backend -p 127.0.0.1:9001:8080/tcp --restart always --env-file ../.env-cinemo-backend cinemo-backend