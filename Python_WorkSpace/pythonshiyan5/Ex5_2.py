#Ex5_2.py
#给该代码的每一行加上注释，以进一步理解其含义
import turtle
   
def drawLine2(draw,length):   #绘制单段数码管
    if (draw):
        turtle.pendown()

        turtle.left(45)                #向左转45°
        turtle.fd(int(length/10.0*(2**0.5)))  #对2开平方根    向角度方向移动length/10.0*(2**0.5)
        turtle.right(45)               #向右转45°
        turtle.fd(int(length*8/10.0))  #  向角度方向移动length*8/10
        turtle.right(45)               #向右转45°
        turtle.fd(int(length/10.0*(2**0.5)))   #  向角度方向移动length/10.0*(2**0.5)
        turtle.right(90)               #向右转90°
        turtle.fd(int(length/10.0*(2**0.5)))   #  向角度方向移动length/10.0*(2**0.5)
        turtle.right(45)               #向右转45°
        turtle.fd(int(length*8/10.0))          #  向角度方向移动length*8/10
        turtle.right(45)               #向右转45°
        turtle.fd(int(length/10.0*(2**0.5)))   #  向角度方向移动length/10.0*(2**0.5)
        turtle.right(135)    #向又转135°

    turtle.penup() 
    turtle.fd(length)
    turtle.right(90)
       
def drawDigit2(digit,length): #根据数字绘制七段数码管
    drawLine2(True,length) if digit in [2,3,4,5,6,8,9] else drawLine2(False,length)
    #如果该数字在这个数据里，则绘制第一段数码管
    drawLine2(True,length) if digit in [0,1,3,4,5,6,7,8,9] else drawLine2(False,length)
    # 如果该数字在这个数据里，则绘制第2段数码管
    drawLine2(True,length) if digit in [0,2,3,5,6,8,9] else drawLine2(False,length)
    # 如果该数字在这个数据里，则绘制第3段数码管
    drawLine2(True,length) if digit in [0,2,6,8] else drawLine2(False,length)
    # 如果该数字在这个数据里，则绘制第4段数码管
    turtle.left(90)
    drawLine2(True,length) if digit in [0,4,5,6,8,9] else drawLine2(False,length)
    # 如果该数字在这个数据里，则绘制第5段数码管
    drawLine2(True,length) if digit in [0,2,3,5,6,7,8,9] else drawLine2(False,length)
    # 如果该数字在这个数据里，则绘制第6段数码管
    drawLine2(True,length) if digit in [0,1,2,3,4,7,8,9] else drawLine2(False,length)
    # 如果该数字在这个数据里，则绘制第7段数码管
    turtle.left(180)
    turtle.penup()#抬起画笔
    turtle.fd(20)  #向前20
    
def drawDate(date):  #获得要输出的数字
    for i in date:
        #drawDigit(eval(i))  #通过eval()函数将数字变为整数
        drawDigit2(eval(i),60) #通过eval()函数将数字变为整数 并且设置length为60
def main():
    turtle.speed(1000)
    turtle.setup(800, 350, 200, 200)
    turtle.tracer(False) #不显示绘制过程
    turtle.penup()
    turtle.fd(-300)
    turtle.pensize(1)  #画笔粗细

    drawDate('20181017')
    turtle.hideturtle()  #隐藏乌龟
    turtle.done()
main()    
