# -*- coding: utf-8 -*-
"""
Created on Tue Nov 13 12:09:33 2018

@author: fengl
"""

def petal(t, r=60.0, angle=60.0): #画叶子
    for i in range(2):
        t.circle(r,angle) #角度为angle，半径为r画弧
        t.left(180-angle)

def flower(t, n=6, r=60.0, angle=60.0): #画花
    t.begin_fill()  #开始填充
    for i in range(n):
        petal(t, r, angle)  #调用画叶子的函数
        t.left(360.0/n)
    t.end_fill()

def move(t, length=100): #移动100
    t.pu()
    t.fd(length)
    t.pd()
