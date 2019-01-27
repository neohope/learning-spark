# About
This is a code example that complements the material in the Spark O'Reilly book. 


# How to build

1. install pythoy 3.6+

2. install packages

```shell
    pip install pyspark
```

3. use IDE like pycharm to open the project


# How to run
1. use IDE like pycharm to run the demos


# Bugfix
pyspark-2.4.0版本在windows平台下存在一个bug，错误的引用了resource包，官方版本暂时没有修复，手工修复方法可以参看这里：
https://github.com/apache/spark/pull/23055/commits

修复时，python安装目录下的pyspark包文件需要替换，spark安装目录下pyspark包文件也需要替换。
