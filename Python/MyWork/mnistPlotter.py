import numpy as np
import matplotlib.pyplot as plt
from tensorflow.examples.tutorials.mnist import input_data

mnist = input_data.read_data_sets("MNIST_data/", one_hot = True)

xtrain = np.vstack([img.reshape(-1,) for img in mnist.train.images])
ytrain = mnist.train.labels

xtest = np.vstack([img.reshape(-1,) for img in mnist.test.images])
ytest = mnist.test.labels

for i in range(211, 220):
    image = xtest[i]
    print(ytest[i])
    # print(xtest[i])
    for k in range(784):
        if(image[k] > 0):
            plt.plot(k % 28, 28 - int(k / 28), 'bo')
        else:
            plt.plot(k % 28, 28 - int(k / 28), 'ro')
    plt.show()
