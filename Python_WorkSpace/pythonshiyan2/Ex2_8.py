#PythonDraw.py
import turtle
turtle.setup(650, 650, 200, 200)
colors=[(1,0,0),(0,1,0),(0,0,1),(1,1,0),(1,0,1),(0,1,1),(0,0,0),(1,1,1),"Red","Green","Yellow"] #设置一个颜色的数组

turtle.penup()
turtle.goto(-300,-300)
turtle.pendown()

turtle.pensize(25)
turtle.seth(45)
turtle.right(40)

for i in range(10):
    turtle.pencolor(colors[i])
    turtle.circle(30, 80)
    turtle.circle(-30, 80)
    
turtle.pencolor("blue")
turtle.circle(30, 80/2)
turtle.fd(40)
turtle.circle(16, 180)
turtle.fd(40 * 2/3)
turtle.done()