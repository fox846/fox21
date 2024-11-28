import pandas as pd
import matplotlib.pyplot as plt

iris_data = pd.read_csv('F:\\ty sem I\\DSML\\PR\\IRIS.csv')

print("Dataset Preview:")
print(iris_data.head())

print("\nFeatures and their Data Types:")
print(iris_data.dtypes)

features = iris_data.select_dtypes(include=['float64', 'int64']).columns 
for feature in features:
    plt.figure(figsize=(6, 4))
    plt.hist(iris_data[feature], bins=15, color='blue', alpha=0.7)
    plt.title(f'Histogram of {feature}')
    plt.xlabel(feature)
    plt.ylabel('Frequency')
    plt.grid(True)
    plt.show()
