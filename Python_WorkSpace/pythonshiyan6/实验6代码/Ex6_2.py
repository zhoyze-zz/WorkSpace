#Ex6_2.py
import jieba
txt = open("红楼梦.txt", "r", encoding='utf-8').read()
words = jieba.lcut(txt)
counts = {}
for word in words:
    if len(word) == 1:
        continue
    else:
        counts[word] = counts.get(word,0) + 1
items = list(counts.items())
items.sort(key=lambda x:x[1], reverse=True) 

#统计总词频
a=0
for num in range(len(items)):
    word, count = items[num]
    a = a+count

b=0
#当达到80%时显示，并跳出
for num in range(len(items)):
    word, count = items[num]
    b = b + count
    if(b/a>0.8):
        print("前{0:}词".format(num))
        break

