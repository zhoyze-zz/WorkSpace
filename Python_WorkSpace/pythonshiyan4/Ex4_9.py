#Ex4_9.py
from random import random
from time import perf_counter
import matplotlib.pyplot as plt
from math import log

x_label=[]
y_value=[]
for k in [10,100,1000,10000,100000,1000000,10000000]:
    steps = k
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
    x_label=x_label+[log(k,10)]
    y_value=y_value+[pi]
plt.plot(x_label,y_value,'r')
plt.ylabel('pi value')
plt.xlabel('log(number,10)')
plt.show()

