#
# Copyright (c) 2010-2011 Oracle and/or its affiliates. All rights reserved.
#
# Redistribution and use in source and binary forms, with or without
# modification, are permitted provided that the following conditions
# are met:
#
#   - Redistributions of source code must retain the above copyright
#     notice, this list of conditions and the following disclaimer.
#
#   - Redistributions in binary form must reproduce the above copyright
#     notice, this list of conditions and the following disclaimer in the
#     documentation and/or other materials provided with the distribution.
#
#   - Neither the name of Oracle nor the names of its
#     contributors may be used to endorse or promote products derived
#     from this software without specific prior written permission.
#
# THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
# IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
# THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
# PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
# CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
# EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
# PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
# PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
# LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
# NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
# SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
#
BASE_HOME=~/java/X/ShoppingPractice

case "$1" in
build)
	mkdir -p $BASE_HOME/WebRoot/WEB-INF/classes/lyons/control
	mkdir -p $BASE_HOME/WebRoot/WEB-INF/classes/lyons/dao
	mkdir -p $BASE_HOME/WebRoot/WEB-INF/classes/lyons/db
	mkdir -p $BASE_HOME/WebRoot/WEB-INF/classes/lyons/entity
	mkdir -p $BASE_HOME/WebRoot/WEB-INF/classes/lyons/goods
	TARGET_DIR=$BASE_HOME/WebRoot/WEB-INF/classes
	cd $BASE_HOME/src
	echo $"compiling classes ..."
	javac -d $TARGET_DIR lyons/db/*.java
	javac -d $TARGET_DIR lyons/entity/*.java
	javac -d $TARGET_DIR -classpath lyons/db:lyons/entity:$CLASSPATH lyons/control/*.java
	javac -d $TARGET_DIR -classpath lyons/db:lyons/entity:$CLASSPATH lyons/dao/*.java
	javac -d $TARGET_DIR -classpath lyons/db:lyons/entity:$CLASSPATH lyons/goods/*.java

	cd $BASE_HOME/WebRoot
	
	echo $"Create war package ..."
	jar cvf $BASE_HOME/ShoppingPractice.war css image index.jsp jsp META-INF navbar.jsp sql WEB-INF

	cd $BASE_HOME
	cp ShoppingPractice.war /home/pi/java/WebServ/apache-tomcat-8.0.24/webapps
	;;
clean)
	rm -rf $BASE_HOME/WebRoot/WEB-INF/classes/*
	rm ShoppingPractice.war
  	;;
*)
  	echo $"Usage: $0 {build|clean}"
  	exit 1
  	;;
esac


