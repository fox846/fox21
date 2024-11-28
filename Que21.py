
import numpy as np 
import pandas as pd

data=pd.read_csv("C:/Users/ishav/Downloads/Datasets(1)/Datasets/IRIS.csv")

data.head(4)

data.shape
features=data[['sepal_length','sepal_width','petal_length','petal_width']].values

K=4
iteration=10
np.random.seed(42)
random_indices=np.random.choice(features.shape[0],K,replace=False)
centroids=features[random_indices]
for i in range(iteration):
    distances=np.array([[np.linalg.norm(point-centroid)for centroid in centroids] for point in features])
    labels = np.argmin(distances, axis=1)
    new_centroids = np.array([features[labels == k].mean(axis=0) for k in range(K)])

    if np.allclose(centroids, new_centroids):
        print(f"Converged after {i+1} iterations.")
        break
    centroids=new_centroids
for cluster_id , centroid in enumerate(centroids):
    print(f"cluster{cluster_id+1}:{centroid}")
