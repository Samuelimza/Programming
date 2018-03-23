import numpy as np

def activate(x, deriv = False):
    if(deriv == True):
        return x * (1 - x)
    return 1 / (1 + np.exp(-x))

iterations = 100
alpha = 1
m = 4
n = [2, 2, 2, 1]
layers = 4
X = [[0,0], [0,1], [1,0], [1,1]]
Y = [1, 0, 0, 1]

#theta = [theta0, theta1, theta2] # len(n), max(n), max(n)
theta = 2 * np.random.random((layers, max(n), max(n))) - 1

for j in range(iterations):
    # len(n), max(n)
    dell = np.zeros((layers, max(n), max(n)))
    for i in range(m):
        # len(n), max(n)
        a = np.zeros((layers, max(n)))
        
        # len(n), max(n)
        d = np.zeros((layers, max(n)))
        a[0][: 3] = X[i][: 3]
        for l in range(1, layers):
            for x in range(n[l]):
                for y in range(n[l - 1]):
                    a[l][x] += activate(a[l - 1][y] * theta[l - 1][x][y])
        print(a[3])
        d[3][0] = Y[i] - a[3][0]
        #d[: n[3]] = [Y[k] - a[3][k] for k in range(n[3])]
        
        for temp in range(2, layers + 1):
            l = layers - temp
            for x in range(n[l]):
                for y in range(n[l + 1]):
                    d[l][x] += d[l + 1][y] * theta[l][x][y]

        for temp in range(2, layers + 1):
            l = layers - temp
            for x in range(n[l]):
                for y in range(n[l + 1]):
                    dell[l][x][y] += d[l + 1][y] * a[l][x]
    print("fun")
    dell /= m
    print(theta)
    theta -= dell * alpha
    print(theta)

