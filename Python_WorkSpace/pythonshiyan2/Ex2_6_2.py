import turtle

turtle.setup(800, 600)  # 设置画布大小为800*600

turtle.hideturtle()  # 隐藏turtle
turtle.speed(1000)  # 设置速度为2

for i in range(6):  # i从0到5循环
    turtle.penup()  # 抬起画笔
    turtle.goto(0, 0)  # 移动到0，0处
    turtle.seth(i * 60)  # 设置角度为i*60°
    turtle.fd(100)  # 沿画笔绘制方向移动100
    turtle.pendown()  # 将画笔放下

    pos0 = turtle.pos()  # 获取当前坐标
    turtle.color('red', 'yellow')  # 设置边框颜色为红色，填充颜色为黄色
    turtle.begin_fill()  # 准备开始填充颜色
    while True:
        turtle.forward(200)  # 向当前画笔方向移动100像素长度
        turtle.right(164)  # 顺时针移动144°
        if abs(turtle.pos() - pos0) < 1:  # 如果当前坐标与pos0坐标一致则break
            break
    turtle.end_fill()  # 填充结束
turtle.done()