import sys
import numpy as np
import matplotlib.pyplot as plt
from tensorflow.examples.tutorials.mnist import input_data

def nonLin(x, deriv = False):
    x = np.clip(x, -50, 50)
    if(deriv == True):
        return x * (1 - x)
    return 1 / (1 + np.exp(-x))

mnist = input_data.read_data_sets("MNIST_data/", one_hot = True)

xtrain = np.vstack([img.reshape(-1,) for img in mnist.train.images])
ytrain = mnist.train.labels

xtest = np.vstack([img.reshape(-1,) for img in mnist.test.images])
ytest = mnist.test.labels

#wieght matrices
w0 = np.genfromtxt("w0.txt", delimiter = ',')
w1 = np.genfromtxt("w1.txt", delimiter = ',')
w2 = np.genfromtxt("w2.txt", delimiter = ',')

l0 = xtest
print("Calculating l1 ")
l1 = nonLin(l0.dot(w0))
print("Calculating l2 ")
l2 = nonLin(l1.dot(w1))
print("Calculating l3 ")
l3 = nonLin(l2.dot(w2))

sumnum = 0
for i in range(0, 10000):
    maxi = 0
    index1 = None
    for k in range(10):
        if(l3[i][k] > maxi):
            maxi = l3[i][k]
            index1 = k
    index2 = None
    for k in range(10):
        if(ytest[i][k] == 1):
            index2 = k
    if(index1 == index2):
        sumnum += 1
print(sumnum / 100)
