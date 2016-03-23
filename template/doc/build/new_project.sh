#!/bin/bash
WD=`dirname $0`
WS=$WD/../../..
cd $WS

PROJ_NAME=$1

mkdir $PROJ_NAME
cp -R template/doc $PROJ_NAME
cp -R template/src $PROJ_NAME
cp template/pom.xml $PROJ_NAME

PACKAGE_FOLDER=$PROJ_NAME/src/main/java/com/ns
mv $PACKAGE_FOLDER/template $PACKAGE_FOLDER/$PROJ_NAME

SQL_FOLDER=$PROJ_NAME/doc/sql
mv $SQL_FOLDER/template.sql $SQL_FOLDER/$PROJ_NAME.sql

sed -i '' -e "s/template/$PROJ_NAME/g" $SQL_FOLDER/$PROJ_NAME.sql

RESOURCE_FOLDER=$PROJ_NAME/src/main/resources

sed -i '' -e "s/template/$PROJ_NAME/g" $RESOURCE_FOLDER/prop.properties

sed -i '' -e "s/template/$PROJ_NAME/g" $RESOURCE_FOLDER/log4j.properties

sed -i '' -e "s/TEMPLATE/$PROJ_NAME/g" $RESOURCE_FOLDER/log4j.properties

SRC_FOLDER=$PROJ_NAME/src/main/java

sed -i '' -e "s/template/$PROJ_NAME/g" $SRC_FOLDER/org/nightstudio/common/aop/AopNSDao.java
sed -i '' -e "s/template/$PROJ_NAME/g" $SRC_FOLDER/org/nightstudio/common/aop/AopNSJsonAction.java
sed -i '' -e "s/template/$PROJ_NAME/g" $SRC_FOLDER/org/nightstudio/common/aop/AopNSRedisDao.java

WEB_FOLDER=$PROJ_NAME/src/main/webapp

sed -i '' -e "s/{Template}/$PROJ_NAME/g" $WEB_FOLDER/WEB-INF/view/web/index/login.jsp

sed -i '' -e "s/template/$PROJ_NAME/g" $WEB_FOLDER/WEB-INF/action-servlet.xml

sed -i '' -e "s/template/$PROJ_NAME/g" $WEB_FOLDER/WEB-INF/web.xml

YEAR=`date +%Y`
sed -i '' -e "s/{Year}/$YEAR/g" $WEB_FOLDER/WEB-INF/view/web/index/login.jsp

sed -i '' -e "s/template/$PROJ_NAME/g" $WEB_FOLDER/WEB-INF/view/view.jsp

sed -i '' -e "s/Template/$PROJ_NAME/g" $WEB_FOLDER/index.html

sed -i '' -e "s/template/$PROJ_NAME/g" $PROJ_NAME/pom.xml

echo "Create $PROJ_NAME Success"

