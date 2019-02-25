#!/bin/bash

docker run --name some-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=admin mysql:5.6 