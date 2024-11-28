import pandas as pd
import matplotlib.pyplot as plt


numeric_data = house_data.select_dtypes(include=['number'])

std_dev = numeric_data.std()
print("Standard Deviation:\n", std_dev, "\n")

variance = numeric_data.var()
print("Variance:\n", variance, "\n")

percentiles = numeric_data.describe(percentiles=[0.25, 0.5, 0.75]).loc[['25%', '50%', '75%']]
print("Percentiles:\n", percentiles, "\n")

plt.figure(figsize=(15, 10))
for i, column in enumerate(numeric_data.columns, 1):
    plt.subplot((len(numeric_data.columns) + 2) // 3, 3, i)  
    plt.hist(numeric_data[column], bins=20, color='skyblue', edgecolor='black')
    plt.title(column)
    plt.xlabel('Value')
    plt.ylabel('Frequency')

plt.tight_layout()
plt.show()
