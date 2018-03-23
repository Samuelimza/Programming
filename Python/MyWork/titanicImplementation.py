import tensorflow as tf
import numpy as np
import random
from TitanicProb import survived
from TitanicProb import sibsp
from TitanicProb import parch
from TitanicProb import classOfTick
from TitanicProb import gender
from TitanicProb import age
from TitanicProb import fare


def shuffle(toBeShuffled, indices):
    temp = [toBeShuffled[i] for i in indices]
    return temp


def toBool(x):
    for i in range(len(x)):
        if x[i][0] > x[i][1]:
            x[i][0] = 1
            x[i][1] = 0
        else:
            x[i][0] = 0
            x[i][1] = 1
    return x


def accuracy(x, y):
    print(len(x), len(y))
    counter = 0
    for i in range(len(x)):
        if float(x[i][0]) == float(y[i][0]) and float(x[i][1]) == float(y[i][1]):
            counter += 1
    return counter / len(x)


m = len(survived)

indices = [i for i in range(m)]
random.shuffle(indices)
print(indices)

sibsp = shuffle(sibsp, indices)
parch = shuffle(parch, indices)
classOfTick = shuffle(classOfTick, indices)
gender = shuffle(gender, indices)
age = shuffle(age, indices)
fare = shuffle(fare, indices)

xTrain = [[float(sibsp[i]), float(parch[i]), float(classOfTick[i]),
           float(gender[i]), age[i], fare[i]] for i in range(int(m * 0.9))]
xTest = []
for i in range(len(xTrain) + 1, m):
    xTest.append([float(sibsp[i]), float(parch[i]), float(classOfTick[i]),
           float(gender[i]), age[i], fare[i]])
yTrain = []
yTest = []
for i in range(int(m * 0.9)):
    temp = []
    if survived[i] == 0:
        temp = [0, 1]
    else:
        temp = [1, 0]
    yTrain.append(temp)
for i in range(len(xTrain) + 1, m):
    temp = []
    if survived[i] == 0:
        temp = [0, 1]
    else:
        temp = [1, 0]
    yTest.append(temp)
lr = 0.00001
iterations = 5000

X = tf.placeholder(tf.float32, [None, 6])
Y = tf.placeholder(tf.float32, [None, 2])

w1 = tf.Variable(tf.random_normal([6, 10]) * 10)
w2 = tf.Variable(tf.random_normal([10, 10]) * 10)
w3 = tf.Variable(tf.random_normal([10, 2]) * 10)

b1 = tf.Variable(tf.random_normal([10]) * 10)
b2 = tf.Variable(tf.random_normal([10]) * 10)
b3 = tf.Variable(tf.random_normal([2]) * 10)

l1 = tf.nn.sigmoid(tf.add(tf.matmul(X, w1), b1))
l2 = tf.nn.sigmoid(tf.add(tf.matmul(l1, w2), b2))
l3 = tf.add(tf.matmul(l2, w3), b3)
tl3 = tf.nn.sigmoid(l3)
loss = tf.reduce_mean(tf.nn.sigmoid_cross_entropy_with_logits(
    logits=l3, labels=Y))
optimizer = tf.train.AdamOptimizer() # learning_rate=lr)
trainer = optimizer.minimize(loss)

init = tf.global_variables_initializer()

sess = tf.Session()
sess.run(init)

for i in range(iterations):
    sess.run(trainer, feed_dict={X: xTrain, Y: yTrain})
    print(sess.run(loss, feed_dict={X: xTrain, Y: yTrain}))
    print('Iteration ', i)

l3Val = sess.run(tl3, feed_dict={X: xTrain})
print(accuracy(toBool(l3Val), yTrain))

l3Val = sess.run(tl3, feed_dict={X: xTest})
print(accuracy(toBool(l3Val), yTest))

arr = [10, 20, 30, 40]
indexMe = [1, 3, 0, 2]
arr = shuffle(arr, indexMe)
print(arr)
