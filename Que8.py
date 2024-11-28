import pandas as pd
from sklearn.tree import DecisionTreeClassifier
from sklearn.preprocessing import LabelEncoder

data= pd.read_csv('F:\\ty sem I\\DSML\\PR\\Lipstick.csv')

encoders = {}
encoded_data = data.copy()
for column in ['Age', 'Income', 'Gender', 'Ms', 'Buys']:
    encoder = LabelEncoder()
    encoded_data[column] = encoder.fit_transform(data[column])
    encoders[column] = encoder

X = encoded_data[['Age', 'Income', 'Gender', 'Ms']]
y = encoded_data['Buys']

clf = DecisionTreeClassifier(criterion='entropy', random_state=42)
clf.fit(X, y)

test_data = {'Age': '21-35', 'Income': 'Low', 'Gender': 'Male', 'Ms': 'Married'}
test_encoded = {key: encoders[key].transform([value])[0] for key, value in test_data.items()}

test_df = pd.DataFrame([test_encoded])

prediction = clf.predict(test_df)
predicted_label = encoders['Buys'].inverse_transform(prediction)[0]

print("Test Data:", test_data)
print("Predicted Decision:", predicted_label)
