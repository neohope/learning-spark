#!/usr/bin/env python
# -*- coding:utf-8 -*-

import sys

from pyspark import SparkContext

"""
平均值计算
"""
sc = SparkContext('local', 'BasicAvg')


def basicAvg(nums):
    rdd = sc.parallelize(nums)
    sumCount = rdd.map(lambda x: (x, 1)).fold((0, 0), (lambda x, y: (x[0] + y[0], x[1] + y[1])))
    return sumCount[0] / float(sumCount[1])


if __name__ == "__main__":
    nums = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
    avg = basicAvg(nums)
    print(avg)
