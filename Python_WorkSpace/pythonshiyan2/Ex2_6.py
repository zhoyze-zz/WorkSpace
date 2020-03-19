# -*- coding: utf-8 -*-
"""
Created on Sun Sep 23 09:11:11 2018

@author: fengl
"""

import turtle as tl

tl.speed(10)
tl.color('red', 'yellow')   #设置颜色
tl.begin_fill()
while True:
    tl.forward(200)
    tl.right(164)       #顺时针移动164°
    if abs(tl.pos()) < 1:   #画完之后就跳出
        break
while True:
    tl.back(200)
    tl.left(164)
    if abs(tl.pos()) < 1:
        break
tl.end_fill()
tl.done()