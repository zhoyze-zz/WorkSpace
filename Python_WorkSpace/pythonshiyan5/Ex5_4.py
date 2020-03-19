# -*- coding: utf-8 -*-
"""
Created on Tue Nov 13 12:09:33 2018

@author: fengl
"""

import turtle
import Ex5_Flower as myf


bob = turtle.Pen()

bob.speed(0)

bob.color("red","red") #设置颜色，填充颜色都为红
myf.move(bob, -100)   #调用ex5_flower中的函数move
myf.flower(bob, 7, 60.0, 60.0)

bob.color("green","green")  #设置颜色，填充颜色都为绿
myf.move(bob, 150)
myf.flower(bob, 10, 40.0, 80.0)   #调用ex5_flower中的函数flower

bob.color("blue","blue")
myf.move(bob, 150)
myf.flower(bob, 20, 140.0, 20.0)  #调用ex5_flower中的函数flower

bob.color("brown","brown")
myf.move(bob, 150)
myf.flower(bob, 8, 80.0, 20.0)  #调用ex5_flower中的函数flower

turtle.done()