#!/usr/bin/env python
# -*- coding:utf-8 -*-

from pyspark import SparkContext

"""
平均值计算
"""
sc = SparkContext("local", "BasicMap")


def basicSquare(rdd):
    return rdd.map(lambda x: x * x)


def basicMap(nums):
    rdd = sc.parallelize(nums)
    return sorted(basicSquare(rdd).collect())


if __name__ == "__main__":
    nums = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
    output = basicMap(nums)

    for num in output:
        print("{0}".format(num))
