import numpy as np
import math


class Vector:
    nc = None
    components = []
    # Mag = np.sum(components ** 2) ** (1 / 2)

    def __init__(self, no):
        self.nc = no
        self.components = [None for _ in range(no)]

    def magnitude(self):
        return np.sum([self.components[i] ** 2 for i in range(self.nc)]) ** (1 / 2)

    def distance(self, other):
        sumOfComp = 0
        for i in range(self.nc):
            sumOfComp += (self.components[i] - other.components[i]) ** 2
        return sumOfComp ** (1 / 2)

    def copy(self):
        vec = Vector(self.nc)
        vec.components = [self.components[i] for i in range(self.nc)]
        return vec

    def add(self, other):
        for i in range(self.nc):
            self.components[i] += other.components[i]

    def sub(self, other):
        for i in range(self.nc):
            self.components[i] -= other.components[i]

    def mult(self, factor):
        for i in range(self.nc):
            self.components[i] *= factor

    def setMag(self, newMag):
        factor = newMag / self.magnitude()
        for i in range(self.nc):
            self.components[i] *= factor

    def rotate(self, angle):
        sine = math.sin(angle)
        cosine = math.cos(angle)
        new0 = self.components[0] * cosine - self.components[1] * sine
        new1 = self.components[0] * sine + self.components[1] * cosine
        self.components[0] = new0
        self.components[1] = new1
