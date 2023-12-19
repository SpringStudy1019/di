#!/bin/bash

REPOSITORY=/home/ubuntu/trendgaza_fe/dist
cd $REPOSITORY

echo "> $REPOSITORY 배포"
sudo cp -r * /var/www/html/.

echo "> nginx 재시작"
sudo systemctl reload nginx