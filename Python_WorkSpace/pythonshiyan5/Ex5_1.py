#Ex5_1.py
#给该代码的每一行加上注释，以进一步理解其含义
import turtle
def drawLine(draw):   #绘制单段数码管
    turtle.pendown() if draw else turtle.penup()
    turtle.fd(40)
    turtle.right(90)#向右转90°
def drawDigit(digit): #根据数字绘制七段数码管
    drawLine(True) if digit in [2,3,4,5,6,8,9] else drawLine(False)  #当这个数字在这个数组里就绘制，否则就不绘制
    drawLine(True) if digit in [0,1,3,4,5,6,7,8,9] else drawLine(False) #当这个数字在这个数组里就绘制，否则就不绘制
    drawLine(True) if digit in [0,2,3,5,6,8,9] else drawLine(False) #当这个数字在这个数组里就绘制，否则就不绘制
    drawLine(True) if digit in [0,2,6,8] else drawLine(False) #当这个数字在这个数组里就绘制，否则就不绘制
    turtle.left(90)
    drawLine(True) if digit in [0,4,5,6,8,9] else drawLine(False) #当这个数字在这个数组里就绘制，否则就不绘制
    drawLine(True) if digit in [0,2,3,5,6,7,8,9] else drawLine(False) #当这个数字在这个数组里就绘制，否则就不绘制
    drawLine(True) if digit in [0,1,2,3,4,7,8,9] else drawLine(False) #当这个数字在这个数组里就绘制，否则就不绘制
    turtle.left(180)
    turtle.penup()#将笔抬起
    turtle.fd(20) #向前20
def drawDate(date):  #获得要输出的数字
    for i in date:
        drawDigit(eval(i))  #通过eval()函数将数字变为整数
def main():
    turtle.setup(800, 350, 200, 200)
    turtle.penup()
    turtle.speed(1000)
    turtle.fd(-300)
    turtle.pensize(5)
    drawDate('20181012')
    turtle.hideturtle()#隐藏画笔
    turtle.done()
main()
