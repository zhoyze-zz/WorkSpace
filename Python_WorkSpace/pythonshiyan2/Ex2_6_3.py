import turtle

turtle.setup(800, 600)  #设置画布大小为800*600
colors=[(1,0,0),(0,1,0),(0,0,1),(1,1,0),(1,0,1),(0,1,1)]  #定义6种颜色
turtle.hideturtle() #隐藏画笔形状
turtle.speed(100) #设置速度为2

for i in range(6):
    turtle.penup()  #抬起画笔
    turtle.goto(0,0) #将画笔移动到0，0
    angle0=i*60  #定义angle0=i*60
    turtle.seth(angle0) #设置画笔角度为angle0
    turtle.fd(100) #沿着画笔绘制方向移动100
    pos0=turtle.pos() #获取画笔当前坐标
    turtle.pendown() #放下画笔
    turtle.color(colors[i], colors[i])  #设置不同填充颜色
    turtle. begin_fill() #开始填充颜色
    while True:
        turtle.forward(200)  # 向当前画笔方向移动100像素长度
        turtle.right(164)  # 顺时针移动144°
        if abs(turtle.pos() - pos0) < 1:  # 如果当前坐标与pos0坐标一致则break
            break
    turtle.end_fill()  # 填充结束
turtle.done()