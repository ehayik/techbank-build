#!/usr/bin/env bash

docker network create --attachable -d bridge techbankNet

docker-compose -f docker-compose.yml up -d
