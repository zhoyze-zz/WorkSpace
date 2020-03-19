#Ex5_3.py
#给该代码的每一行加上注释，以进一步理解其含义
import turtle
from time import sleep
import time


def drawLine2(draw,length):   #绘制单段数码管
    if (draw):
        turtle.pendown()
        turtle.begin_fill()  
        turtle.left(45)
        turtle.fd(int(length/10.0*(2**0.5)))
        turtle.right(45)
        turtle.fd(int(length*8/10.0))
        turtle.right(45)
        turtle.fd(int(length/10.0*(2**0.5)))
        turtle.right(90)
        turtle.fd(int(length/10.0*(2**0.5)))
        turtle.right(45)
        turtle.fd(int(length*8/10.0))
        turtle.right(45)
        turtle.fd(int(length/10.0*(2**0.5)))
        turtle.right(135)
        turtle.end_fill()
    turtle.penup() 
    turtle.fd(length)
    turtle.right(90)

def drawDigit2(digit,length): #根据数字绘制七段数码管
    turtle.color('red', 'yellow')  # 设置笔的颜色和填充颜色
    drawLine2(True,length) if digit in [2,3,4,5,6,8,9,] else drawLine2(False,length)
    drawLine2(True,length) if digit in [0,1,3,4,5,6,7,8,9,] else drawLine2(False,length)
    drawLine2(True,length) if digit in [0,2,3,5,6,8,9] else drawLine2(False,length)
    drawLine2(True,length) if digit in [0,2,6,8] else drawLine2(False,length)
    turtle.left(90)
    drawLine2(True,length) if digit in [0,4,5,6,8,9] else drawLine2(False,length)
    drawLine2(True,length) if digit in [0,2,3,5,6,7,8,9] else drawLine2(False,length)
    drawLine2(True,length) if digit in [0,1,2,3,4,7,8,9,] else drawLine2(False,length)
    turtle.left(180)
    turtle.penup()
    turtle.fd(20)

def drawDigit3( length):  # 根据数字绘制七段数码管
    turtle.color('red', 'purple')  # 设置笔的颜色和填充颜色
    drawLine2(True, length)
    drawLine2(False, length)
    drawLine2(False, length)
    drawLine2(False, length)
    turtle.left(90)
    drawLine2(False, length)
    drawLine2(False, length)
    drawLine2(False, length)
    turtle.left(180)
    turtle.penup()
    turtle.fd(20)

def drawDigit4( length):  # 根据数字绘制七段数码管
    turtle.color('red', 'purple')  # 设置笔的颜色和填充颜色
    drawLine2(False, length)
    drawLine2(True, length)
    drawLine2(False, length)
    drawLine2(False, length)
    turtle.left(90)
    drawLine2(False, length)
    drawLine2(False, length)
    drawLine2(True, length)
    turtle.left(180)
    turtle.penup()
    turtle.fd(20)



def drawDate(date):  #获得要输出的数字
    j=0
    for i in date:
        drawDigit2(eval(i),60)
        j=j+1
        if (j == 4 ):
            drawDigit3(60)
        if (j == 6):
            drawDigit3(60)
        if (j==8):
            turtle.penup()
            turtle.fd(40)
            turtle.pendown()
        if (j == 10):
            drawDigit4(60)
        if (j == 12):
            drawDigit4(60)




def draw():
    turtle.setup(1600, 350, 200, 200)
    turtle.tracer(False)
    turtle.penup()
    turtle.fd(-750)
    turtle.pensize(1)
    turtle.color('red', 'yellow')  # 设置笔的颜色和填充颜色

    while(1):
        drawDate(time.strftime('%Y%m%d%H%M%S',time.localtime(time.time())))
        sleep(1)
        turtle.clear()
        turtle.fd(-1480)
    turtle.hideturtle()
    turtle.done()

def main():
    draw()

main()    
