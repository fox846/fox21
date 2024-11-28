import pandas as pd

house_data = pd.read_csv('F:\\ty sem I\\DSML\\PR\\House Data.csv')

print(house_data.columns)

house_data['price'] = house_data['price'].replace({r'[^\d.]': ''}, regex=True).astype(float)

categorical_column = 'district'  
quantitative_column = 'price'    

summary_stats = house_data.groupby(categorical_column)[quantitative_column].describe()

mean = house_data.groupby(categorical_column)[quantitative_column].mean()
median = house_data.groupby(categorical_column)[quantitative_column].median()
min_value = house_data.groupby(categorical_column)[quantitative_column].min()
max_value = house_data.groupby(categorical_column)[quantitative_column].max()
std_dev = house_data.groupby(categorical_column)[quantitative_column].std()

print("Summary Statistics (Mean, Median, Min, Max, Std Dev):\n")
print("Mean:\n", mean)
print("\nMedian:\n", median)
print("\nMinimum Value:\n", min_value)
print("\nMaximum Value:\n", max_value)
print("\nStandard Deviation:\n", std_dev)

