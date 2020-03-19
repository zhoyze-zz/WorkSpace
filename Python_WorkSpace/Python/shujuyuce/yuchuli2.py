import pandas as pd

for j in range (1000):
    n = str(j + 1000)
    csvFile = open("data/"+n + ".csv", "a")
    for i in range (29):
        str(i)
        m = str(i)+".csv"
        data1 = pd.read_csv(m)             #读取csv文件
        data11 = data1[data1['2'].isin([n])]
        # 创建csv文件
        # 先写入columns_name
        data_f = pd.DataFrame(data11)
        data_f.to_csv("data/"+n+".csv",header=0,index=0,mode='a')
        # 写入多行用writerows                                #写入多行

        print(data11)

    csvFile.close()