#!/usr/bin/env python
# -*- coding:utf-8 -*-

from pyspark import SparkContext
import csv
from io import StringIO

"""
加载CSV文件
"""

sc = SparkContext("local", "LoadCsv")


def loadRecord(line):
    """
    解析一行数据
    """
    input = StringIO(line)
    reader = csv.DictReader(input, fieldnames=["name", "favouriteAnimal"])
    return next(reader)


def loadRecords(fileNameContents):
    """
    解析一个文件
    """
    input = StringIO(fileNameContents[1])
    reader = csv.DictReader(input, fieldnames=["name", "favouriteAnimal"])
    return reader


def writeRecords(records):
    """
    写入CSV文件
    """
    output = StringIO()
    writer = csv.DictWriter(output, fieldnames=["name", "favouriteAnimal"])
    for record in records:
        writer.writerow(record)
    return [output.getvalue()]


if __name__ == "__main__":
    inputFile="../../../datain/favourite_animals.csv"
    outputFile="../../../dataout/loadcsv.py"
    outputFileFull = "../../../dataout/loadcsvfull.py"

    """
    加载一个文件
    对于每一行进行解析
    对每一行进行过滤
    将过滤结果写入硬盘
    """
    input = sc.textFile(inputFile)
    data = input.map(loadRecord)
    pandaLovers = data.filter(lambda x: x['favouriteAnimal'] == "panda")
    pandaLovers.mapPartitions(writeRecords).saveAsTextFile(outputFile)

    """
    加载一个文件并进行解析
    进行过滤
    将结果写入硬盘
    """
    fullFileData = sc.wholeTextFiles(inputFile).flatMap(loadRecords)
    fullFilePandaLovers = fullFileData.filter(
        lambda x: x['favouriteAnimal'] == "panda")
    fullFilePandaLovers.mapPartitions(
        writeRecords).saveAsTextFile(outputFileFull)
    sc.stop()
