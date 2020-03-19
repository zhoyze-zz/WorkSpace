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

now = time.gmtime();       #当前系统时间
cal = calendar.month(now.tm_year, now.tm_mon)    #设置日历为当前时间的日历
cals = cal.split("\n")

turtle.pencolor("blue")
    
for i in range(len(cals)):
    turtle.penup()
    turtle.seth(0)
    turtle.goto(-200,-40*i+200)
    turtle.pendown()
    turtle.write(cals[i], align="left", font=("Courier", 14, "bold"))

turtle.done()   
