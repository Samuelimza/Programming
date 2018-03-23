import csv
import math
import numpy as np
import tensorflow as tf

survived = []
classOfTick = []
name = []
gender = []
age = []
sibsp = []
parch = []
fare = []
embarked = []

with open('train.csv') as data:
    read = csv.reader(data, delimiter=',')
    flag = 1
    for row in read:
        if flag != 1:
            survived.append(row[1])
            classOfTick.append(row[2])
            name.append(row[3])
            gender.append(row[4])
            age.append(row[5])
            sibsp.append(row[6])
            parch.append(row[7])
            fare.append(row[9])
            embarked.append(row[11])
        flag = 0
n = len(survived)  # - 1

for i in range(n):
    survived[i] = int(survived[i])
    sibsp[i] = int(sibsp[i])
    parch[i] = int(parch[i])
    if gender[i] == 'male':
        gender[i] = 0
    elif gender[i] == 'female':
        gender[i] = 1
    classOfTick[i] = int(classOfTick[i]) * 10
    fare[i] = float(fare[i])
    if age[i] != '':
        age[i] = float(age[i])
    else:
        age[i] = -1
fAge = []
fClass = []
fFare = []

indices = []
indicesOfMissingAge = []
for i in range(n):
    fClass.append(math.log(classOfTick[i]))
    if fare[i] == 0:
        fFare.append(0.0)
    else:
        fFare.append(math.log(fare[i]))
    if age[i] != -1:
        fAge.append(math.log(age[i]))
        indices.append(i)
    else:
        fAge.append(-1)
        indicesOfMissingAge.append(i)

xTrain = [[fClass[i], fFare[i]] for i in indices]
yTrain = [[fAge[i]] for i in indices]
yReal = [[age[i]] for i in indices]

xTest = [[fClass[i], fFare[i]] for i in indicesOfMissingAge]

alpha = 0.000001
iterations = 2000
theta = tf.Variable(tf.zeros([2, 1]))
x = tf.placeholder(tf.float32, [None, 2])
y = tf.placeholder(tf.float32, [len(indices), 1])
model = tf.matmul(x, theta)

loss = tf.reduce_sum(tf.square(model - yTrain))
optimizer = tf.train.GradientDescentOptimizer(alpha)
train = optimizer.minimize(loss)

init = tf.global_variables_initializer()

predictionModel = tf.exp(model)

with tf.Session() as sess:
    sess.run(init)
    print("Training age prediction model")
    for i in range(iterations):
        sess.run(train, {x: xTrain, y: yTrain})

    myLoss = tf.reduce_mean(tf.abs(tf.subtract(predictionModel, yReal)))

    # newLoss = tf.reduce_mean(tf.abs(tf.subtract(model, yTrain)))
    print(sess.run(myLoss, feed_dict={x: xTrain}))
    print("Theta: ", sess.run([theta]))

    prediction = sess.run(predictionModel, feed_dict={x: xTest})
count = 0
for i in indicesOfMissingAge:
    age[i] = round(prediction[count][0], 0)
    count += 1
np.savetxt('fuckMe.csv', np.array([fAge, fFare]).reshape(len(fAge), 2), delimiter=',')

# entries = "aefds,gbsh,sibudj".split(",")
