import numpy as np
from keras.models import load_model

dataset = np.loadtxt('pima-indians-diabetes.csv', delimiter=',')
x = dataset[:, 0:8]
y = dataset[:, 8]

model = load_model("model.h5")
model.summary()

model.fit(x, y, epochs=150, batch_size=10)

loss, accuracy = model.evaluate(x, y)
print('Accuracy: %.2f' % (accuracy*100))

model.save("model.h5")
