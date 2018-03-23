#import sys
import numpy as np

testfile = open("test.txt", "w")
testfile.close()
testfile = open("test.txt", "a")
# wb write # r+ read and write # ab+ read and append
a = [[1, 2, 3], [4, 55, 6], [7, 8, 9]]
for i in range(len(a)):
    stri = str(a[i][0]) + ',' + str(a[i][1]) + ',' + str(a[i][2]) + '\n'
    testfile.write(stri)
testfile.close()

data = np.genfromtxt("test.txt", delimiter = ',')
print(data)
