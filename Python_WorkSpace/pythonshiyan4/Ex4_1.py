#Ex4_1.py
height,weight = eval(input("请输入身高(米)和体重(公斤)[逗号隔开]: ")) #获取从键盘输入的height和weight通过","区分
bmi = weight / pow(height, 2)      #bmi=weight除以height的平方
print("BMI 数值为：{:.2f}".format(bmi))  #打印"BMI 数值为："精度为.2f，数值为bmi
who, nat = "", ""     #给who和nat赋值“”“”
if bmi < 18.5:                  #通过if语句判断bmi，给who和nat赋值
    who, nat = "偏瘦", "偏瘦"
elif 18.5 <= bmi < 24:
    who, nat = "正常", "正常"
elif 24 <= bmi < 25:
    who, nat = "正常", "偏胖"
elif 25 <= bmi < 28:
    who, nat = "偏胖", "偏胖"
elif 28 <= bmi < 30:
    who, nat = "偏胖", "肥胖"
else:
    who, nat = "肥胖", "肥胖"
print("BMI 指标为:国际'{0}', 国内'{1}'".format(who, nat)) #打印"BMI 指标为:国际'who', 国内'nat'"
