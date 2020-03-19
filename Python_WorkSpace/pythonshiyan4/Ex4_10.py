from math import*
import matplotlib.pyplot as plt
from math import log



x_label=[]
y_value=[]
for k in [10,100,1000,10000,100000,1000000,10000000]:
    sum_sin = 0
    n = k
    dn = (2 * pi) / n
    for i in range(n):
        sum_sin += abs(sin(i * dn)) * dn
    print(sum_sin)
    x_label=x_label+[log(k,10)]
    y_value=y_value+[sum_sin]
plt.plot(x_label,y_value,'r')
plt.ylabel('sum_sin')
plt.xlabel('log(number,10)')
plt.show()
