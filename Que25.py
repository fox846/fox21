import pandas as pd
from sklearn.preprocessing import MinMaxScaler, LabelEncoder

file_path = 'C:/Users/saksh/OneDrive/Desktop/DSML PRACT. EXAM/IRIS.csv'  
iris_data = pd.read_csv(file_path)

print("Missing Values Before Cleaning:")
print(iris_data.isnull().sum())

iris_data.fillna({
    'sepal_length': iris_data['sepal_length'].mean(),
    'sepal_width': iris_data['sepal_width'].mean(),
    'petal_length': iris_data['petal_length'].mean(),
    'petal_width': iris_data['petal_width'].mean(),
    'species': iris_data['species'].mode()[0]
}, inplace=True)

print("\nMissing Values After Cleaning:")
print(iris_data.isnull().sum())


scaler = MinMaxScaler()
numeric_columns = ['sepal_length', 'sepal_width', 'petal_length', 'petal_width']
iris_data[numeric_columns] = scaler.fit_transform(iris_data[numeric_columns])

print("\nData After Normalization:")
print(iris_data.head())

encoder = LabelEncoder()
iris_data['species_encoded'] = encoder.fit_transform(iris_data['species'])

print("\nSpecies Encoding:")
print(iris_data[['species', 'species_encoded']].drop_duplicates())

iris_data['sepal_area'] = iris_data['sepal_length'] * iris_data['sepal_width']
iris_data['petal_area'] = iris_data['petal_length'] * iris_data['petal_width']

print("\nData with Derived Features:")
print(iris_data.head())

thresholds = {col: (iris_data[col].quantile(0.25), iris_data[col].quantile(0.75)) for col in numeric_columns}
outliers = {}
for col, (q1, q3) in thresholds.items():
    iqr = q3 - q1
    lower = q1 - 1.5 * iqr
    upper = q3 + 1.5 * iqr
    outliers[col] = iris_data[(iris_data[col] < lower) | (iris_data[col] > upper)]

print("\nOutlier Summary:")
for col, df_outliers in outliers.items():
    print(f"Outliers in {col}:")
    print(df_outliers)