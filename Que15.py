import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt

titanic_data = pd.read_csv('F:\\ty sem I\\DSML\\PR\\Titanic.csv')


plt.figure(figsize=(8, 5))
sns.countplot(data=titanic_data, x='Survived', hue='Survived', palette='viridis')
plt.title("Survival Count")
plt.xlabel("Survived (0 = No, 1 = Yes)")
plt.ylabel("Count")
plt.legend(title='Survived', loc='upper right')
plt.show()

plt.figure(figsize=(8, 5))
sns.countplot(data=titanic_data, x='Survived', hue='Sex', palette='magma')
plt.title("Survival by Gender")
plt.xlabel("Survived (0 = No, 1 = Yes)")
plt.ylabel("Count")
plt.legend(title='Sex', loc='upper right')
plt.show()

plt.figure(figsize=(8, 5))
sns.countplot(data=titanic_data, x='Pclass', hue='Survived', palette='coolwarm')
plt.title("Survival by Passenger Class")
plt.xlabel("Passenger Class (1 = First, 2 = Second, 3 = Third)")
plt.ylabel("Count")
plt.legend(title='Survived', loc='upper right')
plt.show()

plt.figure(figsize=(8, 5))
sns.histplot(data=titanic_data, x='Age', bins=30, kde=True, color='blue')
plt.title("Age Distribution of Passengers")
plt.xlabel("Age")
plt.ylabel("Frequency")
plt.show()


plt.figure(figsize=(8, 5))
sns.boxplot(data=titanic_data, x='Pclass', y='Fare', hue='Pclass', palette='pastel')
plt.title("Fare Distribution by Passenger Class")
plt.xlabel("Passenger Class")
plt.ylabel("Fare")
plt.legend(title='Passenger Class', loc='upper right')
plt.show()


plt.figure(figsize=(8, 5))
sns.heatmap(titanic_data.isnull(), cbar=False, cmap='viridis', yticklabels=False)
plt.title("Missing Values in the Titanic Dataset")
plt.show()
