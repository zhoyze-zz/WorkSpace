import turtle

turtle.setup(800, 600)  # 设置画布大小为800*600
turtle.hideturtle()  # 隐藏turtle
turtle.speed(1000)  # 设置画笔移动速度为2

for i in range(5):  # i从0到4循环
    turtle.penup()  # 将笔抬起
    turtle.goto(-300 + i * 120, 0)  # 将画笔移动到-300+i*120,0的位置
    turtle.seth(0)  # 设置当前朝向为0度
    turtle.pendown()  # 将画笔放下

    turtle.color('red', 'yellow')  # 设置颜色，同时设置pencolor=color1, fillcolor=color2
    pos0 = turtle.pos()  # 获取当前坐标
    turtle.begin_fill()  # 准备开始填充图形
    while True:
        turtle.forward(200)  # 向当前画笔方向移动100像素长度
        turtle.right(164)  # 顺时针移动144°
        if abs(turtle.pos() - pos0) < 1:  # 如果当前坐标与pos0坐标一致则break
            break
    turtle.end_fill()  # 填充结束
turtle.done()