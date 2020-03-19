# -*- coding: utf-8 -*-
"""
Created on Tue Oct 16 16:42:26 2018

@author: fengl
"""
#文本进度条程序
import time
scale = 50
print("执行开始".center(scale//2,'-'))
start = time.perf_counter()
for i in range(scale+1):
    a = '*' * i #a为*号的数量
    b = '.' * (scale - i)
    c = (i/scale)*100
    dur = time.perf_counter() - start
    print("\r{:^3.0f}%[{}->{}]{:.2f}s".format(c,a,b,dur),end='')  #在本行重新打印*************
    time.sleep(0.1)
print("\n"+"执行结束".center(scale//2,'#'))
