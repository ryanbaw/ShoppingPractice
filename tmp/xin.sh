#!/bin/bash

cur_dir=$(pwd)
echo $cur_dir

function do_compile_iec104(){
    echo $cur_dir
    iec104=$cur_dir/iec104
    iec104_src=$cur_dir/iec104/src
    iec104_bin=$cur_dir/iec104/bin
    echo $iec104_src
    echo $iec104_bin
    iec104_class=$cur_dir/iec104/class

    # 将iec104的src目录下的所有java文件的名称存入到iec104/src/sources.list文件中
    rm -rf $iec104_src/sources.list
    find $iec104_src -name "*.java" > $iec104_src/sources.list
    cat  $iec104_src/sources.list

    # $iec104_class是存放编译的class文件的目录
    rm -rf $iec104_class
    mkdir $iec104_class

    # 这里开始编译java文件，注意这里的-encoding utf-8，博主刚开始并没有加入这个，然后就报了一堆错误，纠结了很久才发现，这里给各位提个醒了。
    javac -d $iec104_class -encoding utf-8 -classpath $iec104_bin/classes12.jar:$iec104_bin/junit-4.10.jar:$iec104_bin/log4j-1.2.17.jar:$iec104_bin/mysql-connector-java-5.0.5-bin.jar:$iec104_bin/RXTXcomm.jar -g -sourcepath $iec104_src @$iec104_src/sources.list

    # 由于用到了log4j，所以要将log4j的配置文件一并放入，如果没有用到，可以忽略这句
    cp $iec104_src/log4j.properties $iec104_class

    # 如果原来在iec104目录下有jar报就删除掉，因为要生成新的
    rm $iec104/iec104.jar
    # 这里要cd到存放class的目录，否则如果采用绝对路径编译，编译出来的jar包里面就是绝对路径了，这样就会有问题
    # jar -cvfm $iec104/iec104.jar $iec104/MANIFEST.MF $iec104_class/*这样是错误的  
    cd $iec104_class
    jar -cvfm $iec104/iec104.jar $iec104/MANIFEST.MF *
    # 赋予可执行权限
    sudo chmod a+x $iec104/iec104.jar
}

do_compile_iec104
exit 0
