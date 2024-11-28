import pandas as pd

file_path = 'F:\\ty sem I\\DSML\\PR\\Covid Vaccine Statewise.csv'
data = pd.read_csv(file_path)

unique_values = data.nunique()
print("Unique values in each column:")
print(unique_values)

column_data_types = data.dtypes
print("\nData types of each column:")
print(column_data_types)

missing_values = data.isnull().sum()
print("\nMissing values in each column:")
print(missing_values)

numeric_columns = data.select_dtypes(include=['float64', 'int64']).columns

data[numeric_columns] = data[numeric_columns].fillna(data[numeric_columns].mean())

print("\nDataset after filling missing values:")
print(data.head())

print("\n5. Converting data types for optimization:")
for col in numeric_columns:
    data[col] = data[col].astype('float32')  
print(data.dtypes)
