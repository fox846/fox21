import pandas as pd

numeric_data = telecom_data.select_dtypes(include=['number'])

min_values = numeric_data.min()
print("Minimum values:\n", min_values, "\n")

max_values = numeric_data.max()
print("Maximum values:\n", max_values, "\n")

mean_values = numeric_data.mean()
print("Mean values:\n", mean_values, "\n")

range_values = max_values - min_values
print("Range of values:\n", range_values, "\n")

std_dev = numeric_data.std()
print("Standard Deviation:\n", std_dev, "\n")

variance = numeric_data.var()
print("Variance:\n", variance, "\n")

percentiles = numeric_data.describe(percentiles=[0.25, 0.5, 0.75]).loc[['25%', '50%', '75%']]
print("Percentiles:\n", percentiles, "\n")
