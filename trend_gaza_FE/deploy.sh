#!/bin/bash

REPOSITORY=/home/ubuntu/trendgaza_fe
cd $REPOSITORY

DIST_PATH=$REPOSITORY/dist

echo "> $DIST_PATH 에 쓰기 권한 추가"
chmod +w $DIST_PATH

echo "> $DIST_PATH 배포"
cp -r * /var/www/html/.

echo "> nginx 재시작"
sudo systemctl reload nginx
