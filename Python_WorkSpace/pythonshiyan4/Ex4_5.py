# -*- coding: utf-8 -*-
"""
Created on Sat Oct 27 22:15:35 2018

@author: fengl
"""
import matplotlib.pyplot as plt
x1=[-5,-1]
y1=[x1[0]+2,x1[1]+2]
x2=[-1,2]
y2=[x2[0]-1,x2[1]-1]
x3=[2,5]
y3=[x3[0]-2,x3[1]-2]

plt.plot(x1,y1,'b')       #绘制图，x轴坐标为数组x1，y轴坐标为数组y1
plt.plot(x2,y2,'r-')
plt.plot(x3,y3,'g-')
plt.ylabel('Y value')
plt.xlabel('X value')
try:
    x = eval(input("请输入x值: "))
    y = 0
    if -5<=x<= -1:
        y=x+2
    elif 2> x>-1:
        y=x-1
    elif 5 >= x >= 2:
        y = x - 2
    else:
        y = "非法数据"
except:
    print("非法输入！")
else:
    if (y != "非法数据"):
        plt.plot([x],[y],'g*')   #在坐标x，y处绘制‘*’
    plt.title('x={},and y={}'.format(x,y),fontproperties ='SimHei',fontsize = 20)  #设置标题
plt.show()