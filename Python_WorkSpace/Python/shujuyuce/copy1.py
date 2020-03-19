import pandas as pd
import csv

for j in range(1000):
    for i1 in range(24):
        filename = "data5/" + str(j + 1000) + ".csv"
        filename2 ="data1/" + str(j + 1000) + ".csv"
        csvFile = open( filename, "a+")
        with open(filename2,"rt+", encoding="utf-8") as csvfile:
            writer = csv.writer(csvfile, lineterminator='\n')
            reader = csv.reader(csvfile)
            for i,rows in enumerate(reader):
                if i == i1:
                    row1 = rows
        with open(filename2, "rt+", encoding="utf-8") as csvfile:
            writer = csv.writer(csvfile, lineterminator='\n')
            reader = csv.reader(csvfile)
            for i,rows in enumerate(reader):
                if i == i1+73:
                    row2 = rows
        with open(filename2, "rt+", encoding="utf-8") as csvfile:
            writer = csv.writer(csvfile, lineterminator='\n')
            reader = csv.reader(csvfile)
            for i,rows in enumerate(reader):
                if i == i1+49:
                    row3 = rows
        writer = csv.writer(csvFile,lineterminator='\n')
        writer.writerow([row1,row2,row3])