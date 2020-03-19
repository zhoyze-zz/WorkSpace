# -*- coding: utf-8 -*-
"""
Created on Tue Oct 16 16:47:14 2018

@author: fengl
"""

import turtle
import calendar
 
turtle.tracer(False)

turtle.setup(800, 600)
turtle.hideturtle()
turtle.speed(10)

cal = calendar.month(2018, 10)     #设置时间为2018，10月的日历
cals = cal.split("\n")

print(cals)

turtle.pencolor("blue")
    
for i in range(len(cals)):   #当i在cals的长度里时
    turtle.penup()
    turtle.seth(0)
    turtle.goto(-200,-50*i+100)
    turtle.pendown()
    turtle.write(cals[i], align="left", font=("Courier", 14, "bold"))   #写数字，

turtle.done()   
