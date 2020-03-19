TempStr=input("请输入带有符号的距离值：")
if TempStr[-1] in ['M','m']:
    K=(eval(TempStr[0:-1]))*1.852
    print("转换后的距离是{:.2f}K".format(K))
elif TempStr[-1] in ['K','k']:
    M=(eval(TempStr[0:-1]))/1.852
    print("转换后的距离是{:.2f}M".format(M))
else:
    print("输入格式错误")