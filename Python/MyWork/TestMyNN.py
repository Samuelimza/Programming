from NeuralNetwork import NeuralNetwork
import numpy as np
import matplotlib.pyplot as plt


def printBoundary(nn, xStart, xEnd, yStart, yEnd, xi, yi):
    arr = []
    x1 = xStart
    while x1 <= xEnd:
        x2 = yStart
        while x2 <= yEnd:
            arr.append([x1, x2])
            x2 += xi
        x1 += yi
    nn.feedForward(np.array(arr))
    for i in range(len(nn.layer[len(nn.layerN) - 1])):
        if nn.layer[3][i][0] > 0.5:
            plt.plot(nn.layer[0][i][0], nn.layer[0][i][1], 'rs')
        else:
            plt.plot(nn.layer[0][i][0], nn.layer[0][i][1], 'bs')
    plt.show()


data = np.genfromtxt('DoubleMoon1.txt', delimiter=' ')

# input data
x = np.array([[data[i][j] for j in range(2)] for i in range(len(data))])  # np.array(data[:,:2])

# output data
y = np.array([[data[i][2]] for i in range(len(data))])


n = NeuralNetwork([2, 6, 10, 6, 1])
n.train(x, y, epochs=200, batchSize=100)

# print output layer after training
n.feedForward(x)
print(n.layer[len(n.layerN) - 1])
print(n.getAccuracy(x, y))
printBoundary(n, -6, 6, -6, 6, 0.5, 0.5)
