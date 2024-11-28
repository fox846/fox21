import pandas as pd
import matplotlib.pyplot as plt

iris_data = pd.read_csv('F:\\ty sem I\\DSML\\PR\\IRIS.csv')

numeric_features = iris_data.select_dtypes(include=['float64', 'int64']).columns

for feature in numeric_features:
    plt.figure(figsize=(6, 4))
    plt.boxplot(iris_data[feature], vert=False, patch_artist=True, boxprops=dict(facecolor='lightblue'))
    plt.title(f'Box Plot of {feature}')
    plt.xlabel(feature)
    plt.grid(True)
    plt.show()

print("Analysis of distributions and outliers:")
for feature in numeric_features:
    desc = iris_data[feature].describe()
    print(f"\nFeature: {feature}")
    print(desc)
    q1 = desc['25%']
    q3 = desc['75%']
    iqr = q3 - q1
    lower_bound = q1 - 1.5 * iqr
    upper_bound = q3 + 1.5 * iqr
    outliers = iris_data[(iris_data[feature] < lower_bound) | (iris_data[feature] > upper_bound)][feature]
    print(f"Outliers (if any): {outliers.values}")
