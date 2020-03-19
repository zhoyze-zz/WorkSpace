filename ="data3/" + str( + 1001) + ".csv"

file1 = open(filename, 'r').readlines()
fileout = open('labels1.csv', 'w')
for line in file1:
    fileout.write(line.replace('"]"', ''))
fileout.close()