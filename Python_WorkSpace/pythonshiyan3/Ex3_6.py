# -*- coding: utf-8 -*-
"""
Created on Tue Oct 16 16:47:14 2018

@author: fengl
"""

import turtle
import calendar
import time
 
turtle.tracer(False) 

turtle.setup(800, 600)
turtle.hideturtle()
turtle.speed(10)

now = time.gmtime();
cal = calendar.month(now.tm_year, now.tm_mon)
cals = cal.split("\n")

turtle.pencolor("blue")
    
for i in range(len(cals)):
    turtle.penup()
    turtle.seth(0)
    turtle.goto(-200,-40*i+200)
    turtle.pendown()
    turtle.write(cals[i], align="left", font=("Courier", 14, "bold"))

turtle.penup()
turtle.seth(0)
turtle.goto(-200,-100)
turtle.pendown()
while (True):
    now = time.gmtime();
    turtle.write(time.strftime("%Y-%m-%d %H:%M:%S", now), align="left", font=("Courier", 16, "bold")) #绘制当前时间
    time.sleep(1)    #睡眠一秒
    turtle.undo()    #撤销上一步操作
turtle.done()   
