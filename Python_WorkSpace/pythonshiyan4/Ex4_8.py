#Ex4_8.py
from random import random
from time import perf_counter
steps = 1000000
dx=1.0/steps
y0=1
s=0
start = perf_counter()
for i in range(1, steps+1):
    x = i*dx
    y1 = (1-x*x)**0.5
    s = s+(y0+y1)*dx/2
    y0=y1
pi = 4 * s
print("圆周率值是: {}".format(pi))
print("运行时间是: {:.5f}s".format(perf_counter() - start))
