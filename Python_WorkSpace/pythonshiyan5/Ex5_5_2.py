# -*- coding: utf-8 -*-
"""
Created on Tue Nov 13 12:16:06 2018

@author: fengl
"""

import turtle
import random
import Ex5_Flower as myf

def draw_brach(brach_length,ratio=0.8,ps=10):
    turtle.speed(0)
    if brach_length > 20:
        if brach_length < 40:
            turtle.color('green')
        else:
            turtle.color('brown')
        #绘制本层次树枝
        if (ps>1):
            turtle.pensize(int(ps))
        else:
            turtle.pensize(1)
        fx = int(random.uniform(0.5, 1.5)*brach_length)
        turtle.forward(fx)
        angle = int(random.uniform(0.5, 1.5)*25)
        # 绘制右侧的树枝
        turtle.right(angle)
        draw_brach(int(brach_length*ratio),ratio,ps*ratio)
        
        # 绘制左侧的树枝
        turtle.left(2*angle)
        draw_brach(int(brach_length*ratio),ratio,ps*ratio)


        if brach_length < 40:

            turtle.color("red", "red")  # 设置颜色，填充颜色都为红
            myf.flower(turtle, 6, 10.0, 60.0)
            turtle.color('green')
        else:
            turtle.color('brown')

        # 返回之前的树枝上
        turtle.right(angle)
        turtle.backward(fx)
        
def main():
    turtle.left(90)
    turtle.tracer(False)       #设置不看绘制过程，直接出结果
    turtle.penup()
    turtle.backward(150)
    turtle.pendown()
    draw_brach(100)
    turtle.done()

if __name__ == '__main__':
    main()
