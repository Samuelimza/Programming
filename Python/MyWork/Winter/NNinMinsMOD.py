import numpy as np
def nonLin(x, deriv = False):
    x = np.clip(x, -500, 500)
    if(deriv == True):
        return x * (1 - x)
    return 1 / (1 + np.exp(-x))

alpha = 0.1
#input data
X = np.array([[0,0,1], [0,1,1], [1,0,1], [1,1,1]])
#output data
y = np.array([[1,0], [0,1], [0,1], [1,0]])

np.random.seed(1)
#wieght matrices
w0 = 2*np.random.random((3, 16)) - 1
print(w0)
w1 = 2*np.random.random((16, 16)) - 1
w2 = 2*np.random.random((16, 2)) - 1
for j in range(10000):
    l0 = X
    l1 = nonLin(l0.dot(w0))
    l2 = nonLin(l1.dot(w1))
    l3 = nonLin(l2.dot(w2))

    l3Er = y - l3
    if(j % 1000) == 0:
        print("Error:" + str(np.mean(np.abs(l3Er))))
    l3Delta = l3Er
    l2Error = l3Delta.dot(w2.T)
    l2Delta = l2Error * nonLin(l2, deriv = True)
    l1Error = l2Delta.dot(w1.T)
    l1Delta = l1Error * nonLin(l1, deriv = True)
    #update wieghts
    w2 += (l2.T.dot(l3Delta)) * alpha
    w1 += (l1.T.dot(l2Delta)) * alpha
    w0 += (l0.T.dot(l1Delta)) * alpha
print("Output after Training")
print(l3)
