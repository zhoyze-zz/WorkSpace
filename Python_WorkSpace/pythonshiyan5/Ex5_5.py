# -*- coding: utf-8 -*-
"""
Created on Tue Nov 13 12:16:06 2018

@author: fengl
"""

import turtle

def draw_brach(brach_length,ratio=0.8,ps=10):#递归
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
            
        turtle.forward(brach_length)
        
        # 绘制右侧的树枝
        turtle.right(25)
        draw_brach(int(brach_length*ratio),ratio,ps*ratio)
        
        # 绘制左侧的树枝
        turtle.left(50)
        draw_brach(int(brach_length*ratio),ratio,ps*ratio)

        if brach_length < 40:
            turtle.color('green')
        else:
            turtle.color('brown')

        # 返回之前的树枝上
        turtle.right(25)
        turtle.backward(brach_length)
        
def main():
    turtle.speed(0)
    turtle.left(90)
    turtle.penup()
    turtle.backward(150)
    turtle.pendown()
    
    draw_brach(100)

    turtle.done()

if __name__ == '__main__':
    main()
