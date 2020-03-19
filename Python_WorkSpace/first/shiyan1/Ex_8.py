f11,f12=input("输入第一个分数(例如3,4)：").split(",")
f21,f22=input("输入第二个分数(例如3,4)：").split(",")

f11 = int(f11)
f12 = int(f12)
f21 = int(f21)
f22 = int(f22)

diff = f11*f22 - f12*f21

if diff > 0:
    print ("{0}/{1} 比 {2}/{3}大".format(f11,f12,f21,f22))
else:
    print ("{0}/{1} 比 {2}/{3}小".format(f11,f12,f21,f22))