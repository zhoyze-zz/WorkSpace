# -*- coding: utf-8 -*-
"""
Created on Fri Sep 28 20:05:09 2018

@author: fengl
"""
#从指定位置(100,100)开始绘制一个五角星并回到该点处
from turtle import *
hideturtle()
speed(2)

penup()   #抬笔
goto(100,100)  #定位
seth(0)  #设置角度
pendown()   #落笔

pos0=pos()   #保留起始位置
color('red', 'yellow')
begin_fill()
while True:
    forward(200)
    right(144)
    if abs(pos()-pos0) < 1:
        break
end_fill()
    
done()