import pandas as pd
import numpy as np

data = pd.read_csv('F:\\ty sem I\\DSML\\PR\\Lipstick.csv')

def calculate_entropy(column, verbose=False):
    probabilities = column.value_counts(normalize=True)
    if verbose:
        print(f"Probabilities for {column.name}:")
        print(probabilities)
    entropy = -np.sum(probabilities * np.log2(probabilities))
    if verbose:
        print(f"Entropy: {entropy}\n")
    return entropy

def conditional_entropy(data, attribute, target, verbose=False):
    unique_values = data[attribute].unique()
    weighted_entropy = 0
    for value in unique_values:
        subset = data[data[attribute] == value]
        subset_entropy = calculate_entropy(subset[target], verbose=verbose)
        weight = len(subset) / len(data)
        weighted_entropy += weight * subset_entropy
        if verbose:
            print(f"For {attribute} = {value}:")
            print(f"Subset size: {len(subset)}, Weight: {weight}")
            print(f"Subset Entropy: {subset_entropy}\n")
    if verbose:
        print(f"Conditional Entropy for {attribute}: {weighted_entropy}\n")
    return weighted_entropy

def information_gain(data, attribute, target_entropy, target, verbose=False):
    attr_entropy = conditional_entropy(data, attribute, target, verbose=verbose)
    info_gain = target_entropy - attr_entropy
    if verbose:
        print(f"Information Gain for {attribute}: {info_gain}\n")
    return info_gain

print("Calculating entropy for the target variable 'Buys':")
target_entropy = calculate_entropy(data['Buys'], verbose=True)

attributes = ['Age', 'Income', 'Gender', 'Ms']

print("Calculating information gain for each attribute:")
info_gains = {}
for attr in attributes:
    print(f"Processing Attribute: {attr}")
    info_gains[attr] = information_gain(data, attr, target_entropy, 'Buys', verbose=True)

root_node = max(info_gains, key=info_gains.get)

print("Information Gain for each attribute:", info_gains)
print("Root Node of the Decision Tree:", root_node)

import matplotlib.pyplot as plt 
from sklearn.tree import plot_tree 
plt.figure(figsize=(12, 8))
plot_tree(clf, feature_names=X.columns, class_names=encoders['Buys'].classes_, filled=True)
plt.title("Decision Tree Visualization")
plt.show()
