import pandas as pd
data = pd.read_csv("aa.csv")             #读取csv文件
#print(data)
data1 = data[data['riqi'].isin(['2017/12/1','2017/12/2','2017/12/3'])]
# = data[data['xq'].isin(['1286'])]
print(data1)