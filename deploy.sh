#!/bin/sh
password=$1
if [ $# -gt 0 ]; then
echo "项目开始发布" 
mvn clean deploy -Prelease -Dmaven.test.skip=true -Dgpg.passphrase=${password}
else
echo "OSS密码不能为空"
fi
