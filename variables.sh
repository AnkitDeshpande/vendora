#!/bin/bash

# Set the RabbitMQ credentials from VM options
export RABBITMQ_USERNAME=vendora #${RABBITMQ_USERNAME}
export RABBITMQ_PASSWORD=vendora@123 # ${RABBITMQ_PASSWORD}

# Start the Docker Compose service
docker compose down -v
docker compose up -d
