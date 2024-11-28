import pandas as pd

iris_data = pd.read_csv('F:\\ty sem I\\DSML\\PR\\IRIS.csv')


print("Dataset Preview:")
print(iris_data.head())

species_group = iris_data.groupby('species')

print("\nStatistical Details by Species:")
for species, group in species_group:
    print(f"\nSpecies: {species}")
    print(group.describe())

