#Ex4_7.py
from random import random
from time import perf_counter
DARTS = 10000*10000
hits = 0.0
start = perf_counter()
for i in range(1, DARTS+1):  #for循环
    x, y = random(), random()          #x，y为随机数
    dist = pow(x ** 2 + y ** 2, 0.5)
    if dist <= 1.0:
        hits = hits + 1
pi = 4 * (hits/DARTS)
print("圆周率值是: {}".format(pi))
print("运行时间是: {:.5f}s".format(perf_counter() - start))
