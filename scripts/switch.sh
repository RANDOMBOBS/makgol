# switch.sh

#!/bin/bash

# Crawl current connected port of WAS
CURRENT_PORT=$(cat /home/ubuntu/service_url.inc  | grep -Po '[0-9]+' | tail -1)
TARGET_PORT=0

echo "> Nginx currently proxies to ${CURRENT_PORT}."

# Toggle port number
if [ ${CURRENT_PORT} -eq 8080 ]; then
    TARGET_PORT=8081
elif [ ${CURRENT_PORT} -eq 8081 ]; then
    TARGET_PORT=8080
else
    echo "> No WAS is connected to nginx"
    exit 1
fi

# Change proxying port into target port
echo "set \$service_url http://127.0.0.1:${TARGET_PORT};" | tee /home/ubuntu/service_url.inc

echo "> Now Nginx proxies to ${TARGET_PORT}."

# WAS가 완전히 시작될 때까지 대기
sleep 10

# Reload nginx
sudo service nginx reload

if [ $? -ne 0 ]; then
    echo "> Failed to reload Nginx."
    exit 1
fi

echo "> Nginx reloaded."
