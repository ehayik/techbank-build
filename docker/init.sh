#!/usr/bin/env bash

docker network create --attachable -d bridge techbankNet

docker-compose docker-compose.yml up -d
