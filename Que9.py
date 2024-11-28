
import numpy as np

points = np.array([[0.1, 0.6], [0.15, 0.71], [0.08, 0.9], [0.16, 0.85],
                   [0.2, 0.3], [0.25, 0.5], [0.24, 0.1], [0.3, 0.2]])

# Initial centroids
m1 = points[0]  
m2 = points[7]  

def euclidean_distance(point, centroid):
    return np.sqrt(np.sum((point - centroid) ** 2))

def k_means_iteration(points, m1, m2):
    cluster1 = []
    cluster2 = []
   
    cluster_labels = []  
    for point in points:
        dist_to_m1 = euclidean_distance(point, m1)
        dist_to_m2 = euclidean_distance(point, m2)
        if dist_to_m1 < dist_to_m2:
            cluster1.append(point)
            cluster_labels.append("C1")
        else:
            cluster2.append(point)
            cluster_labels.append("C2")
    m1_updated = np.mean(cluster1, axis=0) if cluster1 else m1
   
    m2_updated = np.mean(cluster2, axis=0) if cluster2 else m2
    return cluster_labels, m1_updated, m2_updated, cluster1, cluster2


for iteration in range(1, 3):
    print(f"Iteration {iteration}:") 
    cluster_labels, m1, m2, cluster1, cluster2 = k_means_iteration(points, m1, m2)
    for i, label in enumerate(cluster_labels):
        print(f"Point P{i+1}: {label}") 
   
    p6 = points[5]  
    p6_cluster = "C1" if euclidean_distance(p6, m1) < euclidean_distance(p6, m2) else "C2"

 
    population_m2 = len(cluster2)
    
    print(f"1. P6 belongs to: {p6_cluster}")
    print(f"2. Population around m2: {population_m2}")
    print(f"3. Updated m1: {m1}")
    print(f"3. Updated m2: {m2}")
    print("-" * 50)

