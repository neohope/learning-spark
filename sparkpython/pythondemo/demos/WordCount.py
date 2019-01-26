#!/usr/bin/env python
# -*- coding:utf-8 -*-

from pyspark import SparkContext

"""
单词出现频率统计
"""

sc = SparkContext("local", "WordCount")


def countString(lines):
    rdd = sc.parallelize(lines)
    result = rdd.flatMap(lambda x: x.split(" ")).countByValue()
    for key, value in result.items():
        print("{0}:\t{1}".format(key, value))


def countFile(fileIn, fileOut):
    rdd = sc.textFile(fileIn);
    words = rdd.flatMap(lambda x: x.split(" "))
    counts = words.map(lambda word: (word, 1)).reduceByKey(lambda x, y: x + y)
    counts.saveAsTextFile(fileOut)


if __name__ == "__main__":
    lines = ["pandas", "i like pandas", "i am neohope"]
    countString(lines)

    fileIn="../../../datain/TDOI.txt"
    fileOut = "../../../dataout/wordcount.py"
    countFile(fileIn, fileOut)
