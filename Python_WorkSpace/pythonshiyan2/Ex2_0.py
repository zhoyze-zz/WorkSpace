# -*- coding: utf-8 -*-
"""
Created on Sun Sep 23 09:09:40 2018

@author: fengl
"""
#绘制两个五角星
from turtle import *   #turtle用来画图的库
color('red', 'yellow')
begin_fill()   #开始填充
hideturtle()
speed(2)    #设置画笔速度
while True:
    forward(200)
    right(144)
    if abs(pos()) < 1:
        break
while True:
    back(200)
    left(144)
    if abs(pos()) < 1:
        break
end_fill()
done()
turtle.hideturtle()