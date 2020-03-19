# -*- coding: utf-8 -*-
"""
Created on Tue Sep 18 20:23:16 2018

@author: fengl
"""
import scipy.io.wavfile as wavf
import matplotlib.pyplot as plt
import matplotlib as mpl

import numpy as np

#读wav文件，并对声音数据转换为-1~1之间的实数，返回帧率和声音振幅数据（单声道）
def readWav(wavefn):
    (r,s) = wavf.read(wavefn)  #通过wavf.read（）函数读取.wav文件
    if s.dtype == np.int16:    #如果s的数据类型为 np.int16
        s = s/32768.0
    elif s.dtype == np.int32:  #如果s的数据类型为 np.int32:
        s = s/2147483648.0
    elif s.dtype == np.uint8:  #如果s的数据类型为 np.uint8
        s = s/256.0 
        
    if (len(s.shape)>1):
        s = s[:,0]
    else:
        s = s
    
    return (r,s)

#声音信息分帧函数
def enframe(signal, nw):
 
    signal_length=len(signal) #信号总长度
    if signal_length<=nw: #若信号长度小于一个帧的长度，则帧数定义为1
        nf=1
    else: #否则，计算帧的总长度
        nf=int(np.ceil((1.0*signal_length)/nw))

    pad_length=int(nf*nw) #所有帧加起来总的铺平后的长度
    zeros=np.zeros((pad_length-signal_length)) #不够的长度使用0填补
    pad_signal=np.concatenate((signal,zeros)) #填补后的信号记为pad_signal
    frames=pad_signal.reshape(nf,nw) #得到帧信号
    return frames   #返回帧信号矩阵

#求帧的一般过零率
def RMS(x):
  return sum(x**2)/len(x);

def fRMS(x):   #x为声音数据 **%
    # 循环计算各帧过零率 ****
    dtm = np.zeros((x.shape[0]))
    for i in range(0,x.shape[0]-1): 
        dtm[i]=RMS(x[i,:]);         #求帧的一般过零率

    return (dtm-dtm.min())/(dtm.max()-dtm.min())

#求帧的一般过零率
def ZCR(x):
  signs = np.sign(x)
  signs[signs == 0] = -1

  return len(np.where(np.diff(signs))[0])/len(x)

#计算声音数据的各帧一般过零率
def fZCR(x):   #x为声音数据 
    # 循环计算各帧过零率 ****
    dtm = np.zeros((x.shape[0]))
    for i in range(0,x.shape[0]-1): 
        dtm[i]=ZCR(x[i,:]);       

    return (dtm-dtm.min())/(dtm.max()-dtm.min())


(rate,wav) = readWav("explosion_1.wav")    #读取.wav文件
fwav = enframe(wav,256)   #声音信息分帧函数
m_zcr = fZCR(fwav)   #计算声音数据的各帧一般过零率
m_rms = fRMS(fwav)   # 循环计算各帧过零率 ****

(rate,wav) = readWav("gun_1.wav")    #读取.wav文件
fwav = enframe(wav,256)   #声音信息分帧函数
m_zcr1 = fZCR(fwav)   #计算声音数据的各帧一般过零率
m_rms1 = fRMS(fwav)   # 循环计算各帧过零率 ****

(rate,wav) = readWav("noise_1.wav")    #读取.wav文件
fwav = enframe(wav,256)   #声音信息分帧函数
m_zcr2 = fZCR(fwav)   #计算声音数据的各帧一般过零率
m_rms2 = fRMS(fwav)   # 循环计算各帧过零率 ****

(rate,wav) = readWav("screaming_1.wav")    #读取.wav文件
fwav = enframe(wav,256)   #声音信息分帧函数
m_zcr3 = fZCR(fwav)   #计算声音数据的各帧一般过零率
m_rms3 = fRMS(fwav)   # 循环计算各帧过零率 ****

fig = plt.figure()

mpl.rcParams["font.sans-serif"] = ["SimHei"]
mpl.rcParams["axes.unicode_minus"] = False


x = np.arange(4)
y1 = [m_rms.max(),m_rms1.max(),m_rms2.max(),m_rms3.max()]         #用来存放各个声音最大值的数组
y2 = [m_rms.min(),m_rms1.min(),m_rms2.min(),m_rms3.min()]         #用来存放各个声音最小值的数组
y3 = [m_rms.mean(),m_rms1.mean(),m_rms2.mean(),m_rms3.mean()]     #用来存放各个声音的数组
y4 = [m_rms.std(),m_rms1.std(),m_rms2.std(),m_rms3.std()]         #用来存放各个声音标准差的数组

y5 = [m_zcr.max(),m_zcr1.max(),m_zcr2.max(),m_zcr3.max()]
y6 = [m_zcr.min(),m_zcr1.min(),m_zcr2.min(),m_zcr3.min()]
y7 = [m_zcr.mean(),m_zcr1.mean(),m_zcr2.mean(),m_zcr3.mean()]
y8 = [m_zcr.std(),m_zcr1.std(),m_zcr2.std(),m_zcr3.std()]
bar_width = 0.2
tick_label = ["explosion", "gun", "noise", "screaming"]

plt.subplot(211)
plt.bar(x, y1, bar_width, align="center", color="c", label="explosion", alpha=0.5)
plt.bar(x+bar_width, y2, bar_width, color="b", align="center", label="gun", alpha=0.5)
plt.bar(x+bar_width+bar_width, y3, bar_width, color="g", align="center", label="noise", alpha=0.5)
plt.bar(x+bar_width+bar_width+bar_width, y3, bar_width, color="y", align="center", label="screaming", alpha=0.5)

plt.ylabel("RMS")
plt.xticks(x+bar_width, tick_label)
plt.legend()

plt.subplot(212)
plt.bar(x, y5, bar_width, align="center", color="c", label="explosion", alpha=0.5)
plt.bar(x+bar_width, y6, bar_width, color="b", align="center", label="gun", alpha=0.5)
plt.bar(x+bar_width+bar_width, y7, bar_width, color="g", align="center", label="noise", alpha=0.5)
plt.bar(x+bar_width+bar_width+bar_width, y8, bar_width, color="y", align="center", label="screaming", alpha=0.5)

plt.ylabel("ZCR")
plt.xticks(x+bar_width, tick_label)
plt.legend()
plt.show()