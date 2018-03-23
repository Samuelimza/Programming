import random
import matplotlib.pyplot as plt
import numpy as np
from VectorClass import Vector

n = 2

generators = []
v = Vector(2)
v.components = [random.random() * 100 for i in range(2)]
generators.append(v)
for i in range(n):
    w = Vector(2)
    found = 0
    while found != 1:
        w.components = [random.random() * 100 for i in range(2)]
        found = 1
        for j in range(len(generators)):
            if not(w.distance(generators[j]) > 33):
                found = 0
    generators.append(w)

values = []
arr = []
d = 0.05
while (d < 25):
    values.append(d)
    d += 0.05

for i in values:
    score = 25 / i
    print(int(score))
    for j in range(int(score)):
        arr.append(i)

data1r = []
data2r = []

for i in range(100):
    vec = Vector(2)
    vec.components = [1, 0]
    vec.rotate(2 * np.random.random() * np.pi)
    ind = int(np.random.random() * len(arr))
    sample = arr[ind]
    vec.setMag(sample)
    vec.add(generators[0])
    data1r.append(vec.components[0])
    data2r.append(vec.components[1])

data1b = []
data2b = []

for i in range(100):
    vec = Vector(2)
    vec.components = [1, 0]
    vec.rotate(2 * np.random.random() * np.pi)
    ind = int(np.random.random() * len(arr))
    sample = arr[ind]
    vec.setMag(sample)
    vec.add(generators[1])
    data1b.append(vec.components[0])
    data2b.append(vec.components[1])


plt.plot(data1r, data2r, 'ro')
plt.plot(data1b, data2b, 'bo')
plt.show()
