# health_check.sh
set -x
#!/bin/bash
# Crawl current connected port of WAS
CURRENT_PORT=$(cat /home/ubuntu/service_url.inc | grep -Po '[0-9]+' | tail -1)
TARGET_PORT=0

# Toggle port Number
if [ ${CURRENT_PORT} -eq 8080 ]; then
    TARGET_PORT=8081
elif [ ${CURRENT_PORT} -eq 8081 ]; then
    TARGET_PORT=8080
else
    echo "> No WAS is connected to nginx"
    exit 1
fi

echo "> Start health check of WAS at 'http://127.0.0.1:${TARGET_PORT}' ..."

# 대기할 초 수
WAIT_SECONDS=30
for (( i=1; i<=$WAIT_SECONDS; i++ )); do
    echo "> #${i} trying..."
    RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://127.0.0.1:${TARGET_PORT}/health)

    if [ ${RESPONSE_CODE} -eq 200 ]; then
        echo "> New WAS successfully running"
        exit 0
    elif [ ${i} -eq ${WAIT_SECONDS} ]; then
        echo "> Health check failed after waiting for ${WAIT_SECONDS} seconds."
        exit 1
    fi

    sleep 1
done