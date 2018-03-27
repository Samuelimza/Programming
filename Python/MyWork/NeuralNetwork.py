# Created by Osama Ashhad Azmi in March '18

"""
To Do:
# Use np functions to append bias, delete delta
# (DONE) Fix the accuracy function for one hot compatibility
# Implement the cost function
# Use np.argmax at MARKER
# If possible clean the train function
"""

import numpy as np
import random


class NeuralNetwork:

    def __init__(self, layerN):
        self.m = None
        self.layerN = layerN
        self.w = [2 * np.random.random((layerN[i] + 1, layerN[i + 1])) - 1 for i in range(len(layerN) - 1)]
        self.layer = [None for _ in range(len(layerN))]

    # function to implement forward propagation
    def feedForward(self, X):
        self.layer[0] = self.addBias(X)
        for i in range(1, len(self.layerN) - 1):
            self.layer[i] = self.addBias(self.activate(self.layer[i - 1].dot(self.w[i - 1])))
        self.layer[len(self.layerN) - 1] = self.activate(self.layer[len(self.layerN) - 2].dot(self.w[len(self.layerN) - 2]))

    # function to implement back propagation
    def backProp(self, Y):
        delta = [None for _ in range(len(self.layerN))]
        delta[len(self.layerN) - 1] = Y - self.layer[len(self.layerN) - 1]
        for i in range(len(self.layerN) - 2, 0, -1):
            delta[i] = self.removeRedundantDelta((delta[i + 1].dot(self.w[i].T)) * self.activate(self.layer[i], True))
        for i in range(len(self.layerN) - 1):
            self.w[i] += self.layer[i].T.dot(delta[i + 1]) * (1 / len(Y))

    # function to train the NN on passed data
    def train(self, X, Y, epochs, batchSize=None):
        self.m = len(X)
        if batchSize is None:
            flag = False
        else:
            indices = [i for i in range(self.m)]
            random.shuffle(indices)
            temp = [X[i] for i in indices]
            X = temp
            temp = [Y[i] for i in indices]
            Y = temp
            flag = True

        for j in range(epochs):
            # print('Epoch:', j)
            if flag:
                i = 0
                while i + batchSize < self.m:
                    self.feedForward(X[i:i + batchSize])
                    self.backProp(Y[i:i + batchSize])
                    i += batchSize
                self.feedForward(X[i:self.m])
                self.backProp(Y[i:self.m])
            else:
                self.feedForward(X)
                self.backProp(Y)

    # function to calculate accuracy on passed data
    def getAccuracy(self, X, Y):
        self.feedForward(X)
        output = self.layer[len(self.layerN) - 1]
        count = 0
        for i in range(len(output)):
            maxi = 0
            maxIndex = None
            # MARKER-------------------------------
            for j in range(len(output[i])):
                if output[i][j] > maxi:
                    maxi = output[i][j]
                    maxIndex = j
                output[i][j] = 0
            if Y[i][maxIndex] == 1:
                count += 1
        return count / self.m

    # activation function (sigmoid) that can also calculate g'(z) when flag is set
    @staticmethod
    def activate(layer, flag=False):
        if flag:
            return layer * (1 - layer)
        return 1 / (1 + np.exp(-layer))

    # function to remove delta corresponding to the bias units during back propagation
    @staticmethod
    def removeRedundantDelta(delta):
        newDelta = np.zeros((len(delta), len(delta[0]) - 1))
        for i in range(len(delta)):
            newDelta[i] = delta[i][:-1].copy()
        return newDelta

    # function to add bias units during forward propagation
    @staticmethod
    def addBias(layer):
        newLayer = np.zeros((len(layer), len(layer[0]) + 1))
        for i in range(len(layer)):
            newLayer[i] = np.append(layer[i], [1])
        return newLayer
