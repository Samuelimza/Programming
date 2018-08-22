import tensorflow as tf
a = tf.constant('Hello Tensorflow')
sess = tf.Session()
print(sess.run(a))
