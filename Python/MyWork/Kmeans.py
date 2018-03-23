import matplotlib.pyplot as plt
import numpy as np

def asign(i):
    distances = [((data[i][0] - last[j][0])**2 + (data[i][1] - last[j][1])**2)**0.5 for j in range(n)]
    mind = 9999999
    for j in range(n):
        if(distances[j] < mind):
            mind = distances[j]
            indices[i] = j

def move(i):
    xavg = 0
    yavg = 0
    counter = 0
    for j in range(m):
        if(indices[j] == i):
            xavg += data[j][0]
            yavg += data[j][1]
            counter += 1
    current[i][0] = xavg / counter
    current[i][1] = yavg / counter

def show():
    x1 = []
    x2 = []
    y1 = []
    y2 = []
    for i in range(m):
        if(indices[i] == 0):
            x1.append(data[i][0])
            y1.append(data[i][1])
        elif(indices[i] == 1):
            x2.append(data[i][0])
            y2.append(data[i][1])

    plt.plot(x1, y1, 'bo', x2, y2, 'ro')
    plt.plot(current[0][0], current[0][1], 'bs', current[1][0], current[1][1], 'rs')
    plt.show()

data = [[0, 0], [1, 1], [0, 1], [1, 0], [10, 0], [10, 1], [11, 0], [11, 1]] #m
m = len(data)
n = 2
indices = [-1 for i in range(m)]
last = [[np.random.random(), np.random.random()] for i in range(n)] #n
current = [[last[i][j] for j in range(2)] for i in range(n)]
delta = [[last[i][j] for j in range(2)] for i in range(n)]
show()
while(np.mean(delta) != 0):
    for i in range(m):
        asign(i)

    for i in range(n):
        move(i)
    delta = [[last[i][j] - current[i][j] for j in range(2)] for i in range(n)]
    last = [[current[i][j] for j in range(2)] for i in range(n)]
    show()
print("DONE")
