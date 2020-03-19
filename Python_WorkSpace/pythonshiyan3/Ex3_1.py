# -*- coding: utf-8 -*-
"""
Created on Fri Sep 28 20:05:09 2018

@author: fengl
"""
#绘制图形化的进度条
import turtle
import time

turtle.tracer(False) #加快图形显示速度

turtle.setup(800, 600)
turtle.hideturtle() #隐藏指针
turtle.speed(10)  #设置速度为10

xlen=400  #进度条长度
ylen=50  #进度条高度
scale = 20  #精度
dx = xlen/scale  #步长
dy = ylen/2

#底框绘制
turtle.penup()
turtle.seth(0)
turtle.goto(-xlen/2,dy)
turtle.pendown()
turtle.pensize(1)  #粗细为1
turtle.color('red', 'yellow')
turtle.begin_fill()
turtle.fd(xlen)
turtle.right(90)
turtle.fd(ylen)
turtle.right(90)
turtle.fd(xlen)
turtle.right(90)
turtle.fd(ylen)    
turtle.end_fill()

#绘制进度框
turtle.color('blue', 'blue')
start = time.perf_counter()
for i in range(scale+1):
    a = dx * i  #进度条长度
    c = (i/scale)*100  #进度
    #dur = time.perf_counter() - start
    if i>0:
        turtle.undo()  #撤销上一个操作，防止数字重叠
    turtle.penup()
    turtle.seth(0)
    turtle.goto(-xlen/2,dy-1)
    turtle.pendown()

    turtle.begin_fill() #开始填充
    turtle.fd(a)
    turtle.right(90)
    turtle.fd(ylen-2)
    turtle.right(90)
    turtle.fd(a)
    turtle.right(90)
    turtle.fd(ylen-2)    
    turtle.end_fill()


    
    turtle.penup()
    turtle.seth(0)
    turtle.goto(0,-10)
    turtle.pendown()
    turtle.pencolor('red')
    turtle.write("{:^3.0f}%".format(c))
    
    time.sleep(0.2)                        
turtle.done()
