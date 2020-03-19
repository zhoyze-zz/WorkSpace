filename ="data3/" + str( + 1000) + ".csv"
file1 = open(filename , 'r', encoding='utf-8') # 要去掉空行的文件
file2 = open(filename, 'w', encoding='utf-8') # 生成没有空行的文件