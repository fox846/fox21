import pandas as pd

file_path = "F:\\ty sem I\\DSML\\PR\\Covid Vaccine Statewise.csv"  
covid_data = pd.read_csv(file_path)

print("Dataset Description:")
print(covid_data.describe(include='all')) 
print("\nDataset Information:")
covid_data.info()
print("\nFirst Few Rows of Dataset:")
print(covid_data.head())

statewise_first_dose = covid_data.groupby('State')['First Dose Administered'].sum().reset_index()


print("\nState-wise Vaccination for First Dose:")
print(statewise_first_dose)

statewise_second_dose = covid_data.groupby('State')['Second Dose Administered'].sum().reset_index()
print("\nState-wise Vaccination for Second Dose:")
print(statewise_second_dose)

statewise_first_dose.to_csv("statewise_first_dose.csv", index=False)
statewise_second_dose.to_csv("statewise_second_dose.csv", index=False)
