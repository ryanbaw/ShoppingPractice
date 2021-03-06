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

mkdir src/docroot/WEB-INF/classes
mkdir src/docroot/WEB-INF/lib
cd src/classes
echo "compiling classes directory"
javac -d ../docroot/WEB-INF/classes demo/*.java
cd ../taglib
echo "compiling lib directroy"
javac -classpath ../docroot/WEB-INF/classes:$CLASSPATH demo/*.java
echo "creating tag library archive"
jar cvf ../docroot/WEB-INF/lib/taglib.jar META-INF demo/*.class
rm demo/*.class
cd ../docroot
echo "creating web archive"
jar cvf ../../javamail.war index.html *.jsp WEB-INF
rm -r WEB-INF/classes
rm -r WEB-INF/lib
cd ../..




mkdir -p WebRoot/WEB-INF/classes/lyons/control
mkdir -p WebRoot/WEB-INF/classes/lyons/dao
mkdir -p WebRoot/WEB-INF/classes/lyons/db
mkdir -p WebRoot/WEB-INF/classes/lyons/entity
mkdir -p WebRoot/WEB-INF/classes/lyons/goods
mkdir WebRoot/WEB-INF/lib
cd src/lyons
javac -d ../../WebRoot/WEB-INF/classes/lyons/control control/*.java
javac -d ../../WebRoot/WEB-INF/classes/lyons/dao dao/*.java
javac -d ../../WebRoot/WEB-INF/classes/lyons/db db/*.java
javac -d ../../WebRoot/WEB-INF/classes/lyons/entity entity/*.java
javac -d ../../WebRoot/WEB-INF/classes/lyons/goods goods/*.java
echo "compiling classes directory"



cd WebRoot/
echo "creating web archive"
jar cvf ../shopsite.war css/  image/  index.jsp  jsp/  META-INF/  navbar.jsp  sql/  WEB-INF/
cd ..
