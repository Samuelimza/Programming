import numpy as np
import matplotlib.pyplot as plt

X=np.array([[1 for i in range(100)],
   [i for i in range(100)]], dtype="float32")
# X /= np.max(X)

Y=np.array([i for i in range(100)], dtype="float32")

m=len(Y)

alpha = 0.0005

theta=np.array([[0],
               [1]], dtype="float32")

def h(x):
    return np.dot(theta.T, x)

epochs=300
for i in range(epochs):
    deltaTheta=np.array([0, 0], dtype="float32")
    J = 0
    for j in range(m):
        J += (0.5/m)*(h(np.reshape(X[:,j], (2, 1)))[0,0]-Y[j])*(h(np.reshape(X[:,j], (2, 1)))[0,0]-Y[j])
        rawOut = h(np.reshape(X[:, j], (2, 1)))[0,0] - Y[j]
        deltaTheta[0] += (1.0/m)*rawOut * X[0, j]
        deltaTheta[1] += (1.0/m)*rawOut * X[1, j]
    print(deltaTheta)
    print(theta)
    print(alpha*deltaTheta[0], alpha*deltaTheta[1])
    theta[0,0]-= alpha*deltaTheta[0]
    theta[1,0]-= alpha*deltaTheta[1]
    print(theta)
    print(J)
    print("\n")

print(theta)
