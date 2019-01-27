#!/usr/bin/env python
# -*- coding:utf-8 -*-

import sys

from pyspark import SparkContext

"""
求和
"""
sc = SparkContext("local", "Sum")


def basicSum(nums):
    rdd = sc.parallelize(nums)
    return rdd.fold(0, (lambda x, y: x + y))


if __name__ == "__main__":
    nums = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
    sum = basicSum(nums)
    print(sum)
