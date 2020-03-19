from turtle import*
color('blue','yellow')
begin_fill()
hideturtle()
speed(1)
while True:
    forward(200)
    right(144)
    if abs(pos())<1:
        break
end_fill()
done()
