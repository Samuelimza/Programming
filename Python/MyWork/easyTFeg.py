import tensorflow as tf

W = tf.Variable([0.3], dtype = tf.float32)
b = tf.Variable([-0.3], dtype = tf.float32)

x = tf.placeholder(tf.float32)
y = tf.placeholder(tf.float32)

linearmodel = W * x + b
squared = tf.square(linearmodel - y)
loss = tf.reduce_sum(squared)
optimizer = tf.train.GradientDescentOptimizer(0.01)
train = optimizer.minimize(loss)

xtrain = [1, 2, 3, 4]
ytrain = [0, -1, -2, -3]

init = tf.global_variables_initializer()
sess = tf.Session()
sess.run(init)

for i in range(1000):
    sess.run(train, {x: xtrain, y: ytrain})

currw, currb, currl = sess.run([W, b, loss], {x: xtrain, y: ytrain})
print("W: %s b: %s loss: %s" %(currw, currb, currl))
