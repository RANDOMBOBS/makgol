# run_new_was.sh

#!/bin/bash

CURRENT_PORT=$(cat /home/ubuntu/service_url.inc | grep -Po '[0-9]+' | tail -1)
TARGET_PORT=0

echo "> Current port of running WAS is ${CURRENT_PORT}."

if [ ${CURRENT_PORT} -eq 8080 ]; then
  TARGET_PORT=8081
elif [ ${CURRENT_PORT} -eq 8081 ]; then
  TARGET_PORT=8080
else
  echo "> No WAS is connected to nginx"
fi

TARGET_PID=$(lsof -F -i TCP:${TARGET_PORT} | awk '/^p/ {print substr($1, 2)}')

if [ ! -z ${TARGET_PID} ]; then
  echo "> Kill WAS running at ${TARGET_PORT}."
  sudo kill ${TARGET_PID}
  if [ $? -ne 0 ]; then
      echo "> Failed to kill WAS running at ${TARGET_PORT}."
      exit 1
  fi
fi

nohup java -jar -Dserver.port=${TARGET_PORT} /home/ubuntu/service/makgol/build/libs/* > /home/ubuntu/nohup.out 2>&1 &

if [ $? -ne 0 ]; then
    echo "> Failed to start new WAS."
    exit 1
fi

echo "> Now new WAS runs at ${TARGET_PORT}."
exit 0
