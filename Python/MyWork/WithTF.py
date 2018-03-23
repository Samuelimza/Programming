import sys
import numpy as np
import matplotlib.pyplot as plt
from tensorflow.examples.tutorials.mnist import input_data
import tensorflow as tf

mnist = input_data.read_data_sets("MNIST_data/", one_hot = True)

xtrain = np.vstack([img.reshape(-1,) for img in mnist.train.images])
ytrain = mnist.train.labels

xtest = np.vstack([img.reshape(-1,) for img in mnist.test.images])
ytest = mnist.test.labels

lr = 0.001
iterations = 100

X = tf.placeholder(tf.float32, [None, 784])
Y = tf.placeholder(tf.float32, [None, 10])

w1 = tf.Variable(tf.random_normal([784, 16]))
w2 = tf.Variable(tf.random_normal([16, 16]))
w3 = tf.Variable(tf.random_normal([16, 10]))

b1 = tf.Variable(tf.random_normal([16]))
b2 = tf.Variable(tf.random_normal([16]))
b3 = tf.Variable(tf.random_normal([10]))

l1 = tf.nn.sigmoid(tf.add(tf.matmul(xtrain, w1), b1))
l2 = tf.nn.sigmoid(tf.add(tf.matmul(l1, w2), b2))
l3 = tf.nn.sigmoid(tf.add(tf.matmul(l2, w3), b3))

loss = tf.reduce_mean(tf.nn.softmax_cross_entropy_with_logits(
    logits = l3, labels = ytrain))
optimizer = tf.train.AdamOptimizer(learning_rate = lr)
trainer  = optimizer.minimize(loss)

init =  tf.global_variables_initializer()

sess = tf.Session()
sess.run(init)

for i in range(iterations):
    sess.run([trainer], feed_dict={X: xtrain, Y: ytrain})
    print('Iteration ', i)

pred = tf.nn.softmax(l3)
correct_prediction = tf.equal(tf.argmax(pred, 1), tf.argmax(ytrain, 1))
accuracy = tf.reduce_mean(tf.cast(correct_prediction, "float"))
print("Accuracy:", accuracy.eval({X: mnist.test.images, Y: mnist.test.labels}, session = sess))
