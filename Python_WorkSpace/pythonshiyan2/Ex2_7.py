#PythonDraw.py
import turtle
turtle.setup(650, 350, 200, 200)   #设置画布大小和位置
turtle.penup()            #将笔抬起
turtle.fd(-250)            #向前移动-250
turtle.pendown()            #将笔放下
turtle.pensize(25)          #设置笔的大小
turtle.pencolor("purple")   #设置颜色
turtle.seth(-40)           # 设置角度为-40°
for i in range(4):
    if (i%2==0):
        turtle.pencolor("green")
    else:
        turtle.pencolor("red")
    turtle.circle(40, 80)      #以40为角度，80为半径画圆
    turtle.circle(-40, 80)
turtle.pencolor("blue")
turtle.circle(40, 80/2)
turtle.fd(40)            #向前40
turtle.circle(16, 180)
turtle.fd(40 * 2/3)
turtle.done()