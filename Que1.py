import pandas as pd

selected_columns = titanic_data[['Name', 'Age', 'Survived']].head()

indexed_data = titanic_data.set_index('PassengerId').head()

sorted_data = titanic_data.sort_values(by='Fare', ascending=False).head()

description = titanic_data.describe()

data_types = titanic_data.dtypes

print("Selected Columns:")
print(selected_columns)
print("\nIndexed Data:")
print(indexed_data)
print("\nSorted Data by 'Fare':")
print(sorted_data)
print("\nDescription of Dataset:")
print(description)
print("\nData Types of Each Column:")
print(data_types)
