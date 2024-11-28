import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt

titanic_data = pd.read_csv('F:\\ty sem I\\DSML\\PR\\Titanic.csv')

plt.figure(figsize=(10, 6))

sns.histplot(data=titanic_data, x='Fare', bins=30, kde=True, color='blue')
plt.title("Distribution of Ticket Prices (Fare) on Titanic")
plt.xlabel("Fare")
plt.ylabel("Frequency")
plt.grid(True)
plt.show()
