# -*- coding: utf-8 -*-
"""
Created on Fri Nov 23 21:14:54 2018

@author: fengl
"""
#Ex7_8.py   爬出并且提取需要的标签文本
import urllib.request
from bs4 import BeautifulSoup
import re
import jieba
import wordcloud
from scipy.misc import imread
import matplotlib.pyplot as plt


mask = imread("chinamap.jpg")   #读取中国地图模板

def getHtml(url):                #用于爬出指定网页html
    html=urllib.request.urlopen(url).read()
    html= html.decode('utf-8')
    return html

url1="https://news.sina.com.cn/c/2019-11-30/doc-iihnzahi4430496.shtml"
url2="https://news.sina.com.cn/c/2019-11-30/doc-iihnzahi4423858.shtml"
url3="https://news.sina.com.cn/c/2019-12-01/doc-iihnzahi4482185.shtml"
url4="https://news.sina.com.cn/gov/2019-12-01/doc-iihnzhfz2850702.shtml"
url5="https://news.sina.com.cn/c/2019-12-01/doc-iihnzahi4538150.shtml"
url6="https://news.sina.com.cn/c/2019-11-30/doc-iihnzahi4401639.shtml"
url7="https://news.sina.com.cn/w/2019-12-01/doc-iihnzhfz2827451.shtml"
url8="https://news.sina.com.cn/c/2019-11-30/doc-iihnzahi4427714.shtml"
url9="https://news.sina.com.cn/w/2019-12-01/doc-iihnzahi4437172.shtml"
url10="https://news.sina.com.cn/c/2019-11-30/doc-iihnzahi4461040.shtml"

def getlist(url):       #爬出并提取需要的标签文本，并且保存到文件data.txt中
    html = getHtml(url)
    bsObj = BeautifulSoup(html, "html.parser")   #爬出需要的标签资源
    downloadList=bsObj.select('p')
    text_re=re.compile(r'<p>(\s+?\S+?)</p>')
    text_list=[]         #用于保存提取出的标签文本的数组
    for txt in downloadList:
        html="{}".format(txt)
        text_list+=text_re.findall(html)
        file = open('data.txt', 'a')   #在文件里追加保存
        file.write(str(text_list));
        file.close()
    print(text_list)

getlist(url1)   #爬出并提取url1中需要的标签文本，并且保存到文件data.txt中
getlist(url2)   #爬出并提取url2中需要的标签文本，并且保存到文件data.txt中
getlist(url3)   #爬出并提取url3中需要的标签文本，并且保存到文件data.txt中
getlist(url4)   #爬出并提取url4中需要的标签文本，并且保存到文件data.txt中
getlist(url5)   #爬出并提取url5中需要的标签文本，并且保存到文件data.txt中
getlist(url6)   #爬出并提取url6中需要的标签文本，并且保存到文件data.txt中
getlist(url7)   #爬出并提取url7中需要的标签文本，并且保存到文件data.txt中
getlist(url8)   #爬出并提取url8中需要的标签文本，并且保存到文件data.txt中
getlist(url9)   #爬出并提取url9中需要的标签文本，并且保存到文件data.txt中
getlist(url10)  #爬出并提取url10中需要的标签文本，并且保存到文件data.txt中

#删除文件中不需要的字符u3000
with open('data.txt', 'r') as fpr:
    content = fpr.read()
content = content.replace('u3000', '') #将读取到的文本中的u3000替换为空
print(content)
with open('data.txt', 'w') as fpw:
    fpw.write(content)    #将文本重新写回txt文件中

f = open("data.txt", "r", encoding="GBK")   #读取文本
t = f.read()
f.close()
ls = jieba.lcut(t)          #通过jieba库的lcut函数将文本分割
txt = " ".join(ls)
w = wordcloud.WordCloud(\
    width = 2000, height = 1500,\
    background_color = "white",
    font_path = "msyh.ttc", mask = mask
    )
bmp = w.generate(txt)

plt.imshow(bmp)
plt.axis('off')
plt.show()