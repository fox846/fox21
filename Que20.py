import pandas as pd
import numpy as np

iris_data = pd.read_csv('F:\\ty sem I\\DSML\\PR\\IRIS.csv')

features = iris_data[['sepal_length', 'sepal_width', 'petal_length', 'petal_width']].values

K = 3
iterations = 10

np.random.seed(42)  
random_indices = np.random.choice(features.shape[0], K, replace=False)  
centroids = features[random_indices]

for i in range(iterations):
    distances = np.array([[np.linalg.norm(point - centroid) for centroid in centroids] for point in features])
    labels = np.argmin(distances, axis=1)
    new_centroids = np.array([features[labels == k].mean(axis=0) for k in range(K)])

    if np.allclose(centroids, new_centroids):
        print(f"Converged after {i+1} iterations.")
        break
    
    centroids = new_centroids

print("Final cluster means (centroids):")
for cluster_id, centroid in enumerate(centroids):
    print(f"Cluster {cluster_id + 1}: {centroid}")
