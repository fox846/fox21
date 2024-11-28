import numpy as np

points = np.array([[2, 10], [2, 5], [8, 4], [5, 8], [7, 5], [6, 4], [1, 2], [4, 9]])

m1 = points[0]  
m2 = points[3]  
m3 = points[6]  

def euclidean_distance(point, centroid):
    return np.sqrt(np.sum((point - centroid) ** 2))

def k_means_iteration(points, m1, m2, m3):
    cluster1 = []
    cluster2 = []
    cluster3 = []
    cluster_labels = [] 
    for point in points:
        dist_to_m1 = euclidean_distance(point, m1)
        dist_to_m2 = euclidean_distance(point, m2)
        dist_to_m3 = euclidean_distance(point, m3)
        if dist_to_m1 < dist_to_m2 and dist_to_m1 < dist_to_m3:
            cluster1.append(point)
            cluster_labels.append("C1")
        elif dist_to_m2 < dist_to_m1 and dist_to_m2 < dist_to_m3:
            cluster2.append(point)
            cluster_labels.append("C2")
        else:
            cluster3.append(point)
            cluster_labels.append("C3")
    m1_updated = np.mean(cluster1, axis=0) if cluster1 else m1
    m2_updated = np.mean(cluster2, axis=0) if cluster2 else m2
    m3_updated = np.mean(cluster3, axis=0) if cluster3 else m3
    return cluster_labels, m1_updated, m2_updated, m3_updated, cluster1, cluster2, cluster3

for iteration in range(1, 3):
    print(f"Iteration {iteration}:")
    cluster_labels, m1, m2, m3, cluster1, cluster2, cluster3 = k_means_iteration(points, m1, m2, m3)
    for i, label in enumerate(cluster_labels):
        print(f"Point P{i+1}: {label}")
    
    
    p6 = points[5]  
    p6_cluster = "C1" if euclidean_distance(p6, m1) < euclidean_distance(p6, m2) and euclidean_distance(p6, m1) < euclidean_distance(p6, m3) else \
                 "C2" if euclidean_distance(p6, m2) < euclidean_distance(p6, m1) and euclidean_distance(p6, m2) < euclidean_distance(p6, m3) else "C3"
    
    population_m3 = len(cluster3)
    
    print(f"1. P6 belongs to: {p6_cluster}")
    print(f"2. Population around m3: {population_m3}")
    print(f"3. Updated m1: {m1}")
    print(f"3. Updated m2: {m2}")
    print(f"3. Updated m3: {m3}")
    print("-" * 40)
