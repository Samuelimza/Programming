import numpy as np
def nonLin(x, deriv = False):
    if(deriv == True):
        return x * (1 - x)
    return 1 / (1 + np.exp(-x))
#input data
X = np.array([[0,0,1], [0,1,1], [1,0,1], [1,1,1]])
#output data
y = np.array([[1], [0], [0], [1]])
np.random.seed(1)
#synapses
syn0 = 2*np.random.random((3, 4)) - 1
syn1 = 2*np.random.random((4, 1)) - 1
#training step
for j in range(60000):
    l0 = X
    l1 = nonLin(l0.dot(syn0))
    l2 = nonLin(l1.dot(syn1))
    l2Error = y - l2
    l2Delta = l2Error
    l1Error = l2Delta.dot(syn1.T)
    l1Delta = l1Error * nonLin(l1, deriv = True)
    if(j % 10000) == 0:
        print("Error:" + str(np.mean(np.abs(l2Error))))
    #update wieghts
    syn1 += l1.T.dot(l2Delta)
    syn0 += l0.T.dot(l1Delta)
print("Output after Training")
print(l2)
