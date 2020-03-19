TempStr=input("请输入带有符号的距离值：")
if TempStr[0] in ['M','m']:
    K=(eval(TempStr[1:]))*1.852
    print("转换后的距离是K{:.2f}".format(K))
elif TempStr[0] in ['K','k']:
    M=(eval(TempStr[1:]))/1.852
    print("转换后的距离是M{:.2f}".format(M))
else:
    print("输入格式错误")