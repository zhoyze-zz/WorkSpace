#Ex6_7.py
import jieba
import wordcloud
from scipy.misc import imread
import matplotlib.pyplot as plt

mask = imread("chinamap.jpg")
excludes = { }
f = open("我与地坛.txt", "r", encoding="utf-8")      #打开文件我与地坛
t = f.read()           #读取该文件内容
f.close()
ls = jieba.lcut(t)      #通过jieba库将文本分割
txt = " ".join(ls)

f = open("三国演义.txt", "r", encoding="utf-8")     #打开文件三国演义
t = f.read()            #读取该文件内容
f.close()
ls = jieba.lcut(t)        #通过jieba库将文本分割
txt = txt+" ".join(ls)      #将三国演义中的内容添加到txt中

f = open("红楼梦.txt", "r", encoding="utf-8")        #打开文件红楼梦
t = f.read()           #读取该文件内容
f.close()
ls = jieba.lcut(t)       #通过jieba库将文本分割
txt = txt+" ".join(ls)         #将红楼梦中的内容添加到txt中
w = wordcloud.WordCloud(\
    width = 1000, height = 700,\
    background_color = "white",
    font_path = "msyh.ttc", mask = mask
    )             #设置词云格式
bmp = w.generate(txt)                   #绘制词云

plt.imshow(bmp)
plt.axis('off')
plt.show()



