import pandas as pd
import csv

num = 1006
csvFile = open("1008.csv", "w")
filename = "data1/" + str( num) + ".csv"
with open(filename, "rt", encoding="utf-8") as csvfile:
    reader = csv.reader(csvfile)
    for i, rows in enumerate(reader):
        if i == 3:
            row1 = rows
with open(filename, "rt", encoding="utf-8") as csvfile:
    reader = csv.reader(csvfile)
    for i, rows in enumerate(reader):
        if i == 3:
            row2 = rows
writer = csv.writer(csvFile)
writer.writerow([row1,row2,row1])
writer.writerow([row1,row2,row1])