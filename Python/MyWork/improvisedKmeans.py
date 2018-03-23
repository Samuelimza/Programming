import random

from VectorClass import Vector
import matplotlib.pyplot as plt
import numpy as np

vectors = []
data = np.genfromtxt("data.txt", delimiter=',')
m = len(data)
n = 2
for i in range(m):
    for j in range(len(data[0])):
        vectors.append(Vector(len(data[0])))
        vectors[i].components = data[i]

indices = [-1 for i in range(m)]
last = []
for i in range(n):
    ind = int(random.random() * n)
    last.append(vectors[ind])
current = [last[i].copy() for i in range(n)]
delta = []


def asign(i):
    distances = [last[j].distance(vectors[i]) for j in range(n)]
    mind = 9999999
    for j in range(n):
        if distances[j] < mind:
            mind = distances[j]
            indices[i] = j


def move(i):
    counter = 0
    avg = current[i].copy()
    avg.mult(0)
    for j in range(m):
        if indices[j] == i:
            avg.add(vectors[j])
            counter += 1
    if counter != 0:
        avg.mult(1 / counter)
    current[i] = avg.copy()


def show():
    x1 = []
    x2 = []
    y1 = []
    y2 = []
    for i in range(m):
        if indices[i] == 0:
            x1.append(vectors[i].components[0])
            y1.append(vectors[i].components[1])
        elif indices[i] == 1:
            x2.append(vectors[i].components[0])
            y2.append(vectors[i].components[1])

    plt.plot(x1, y1, 'bo', x2, y2, 'ro')
    plt.plot(current[0].components[0], current[0].components[1], 'bs', current[1].components[0],
             current[1].components[1], 'rs')
    plt.show()


show()

while np.mean(delta) != 0:
    for i in range(m):
        asign(i)

    for i in range(n):
        move(i)
    delta = [current[i].distance(last[i]) for i in range(n)]
    last = [current[i].copy() for i in range(n)]
    show()
print("DONE")
