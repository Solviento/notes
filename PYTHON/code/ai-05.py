import numpy as np
import matplotlib.pyplot as plt
import matplotlib.image as mpimg
from mpl_toolkits.mplot3d import axes3d, Axes3D
from sklearn.metrics import pairwise_distances_argmin
from sklearn.utils import shuffle
from sklearn.cluster import KMeans
from time import time
import cv2, os

# citation: http://scikit-learn.org/stable/modules/generated/sklearn.cluster.KMeans.html
cwd = os.getcwd()
# read in img
png = cwd + "\\trees.png"
img = cv2.imread(png)
img = np.array(img, dtype=np.float64)/255
# reshape image
width, height, dim = tuple(img.shape)
img_ = np.reshape(img, (width * height, dim))
# do clustering kmeans
kmeans = KMeans(n_clusters=7).fit(img_)
# do post process
labels = kmeans.predict(img_)
# recreate image
image = np.zeros((width, height, dim))
iter = 0
# interpolate values
for i in range(width):
    for j in range(height):
        image[i][j] = kmeans.cluster_centers_[labels[iter]]
        iter += 1

cv2.imshow("kmeans", image)
cv2.waitKey(0)
