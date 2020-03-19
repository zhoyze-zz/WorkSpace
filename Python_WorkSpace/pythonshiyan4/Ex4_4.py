# -*- coding: utf-8 -*-
"""
Created on Sat Oct 27 22:15:35 2018

@author: fengl
"""
import matplotlib.pyplot as plt
plt.plot([1,3,2,4,5,6,7,8])       #绘制图，数组为y轴的坐标，x轴默认为01234567
plt.plot([0,1,2,3],[1.5,2,3,4.5],'b')  #绘制图，x轴坐标和y轴坐标，颜色为b
plt.plot([0,1,2,3],[2.5,3,4,5.5],'r-')
plt.ylabel('some numbers')   #设置y轴标签
plt.xlabel('X label')   #设置x轴标签
plt.title('My Graph')
plt.show()