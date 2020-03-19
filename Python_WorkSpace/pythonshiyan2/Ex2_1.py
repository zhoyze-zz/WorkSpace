# -*- coding: utf-8 -*-
"""
Created on Tue Sep 18 20:23:16 2018

@author: fengl
"""
#从原点开始绘制一个五角星，且回到原点
from turtle import *  #引入turtle库
color('green', 'yellow')  #设置笔的颜色和填充颜色
begin_fill()  #开始填充
hideturtle()  #隐藏海龟
speed(1)   #设置绘制速度
while True:
    forward(200)  #前进200
    right(144)    #右转144度
    if abs(pos()) < 1:   #判断当前点是否在原点附近
        break   #退出循环
end_fill()   #结束填充
done()


