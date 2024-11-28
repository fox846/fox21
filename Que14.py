import pandas as pd

file_path = "F:\\ty sem I\\DSML\\PR\\Covid Vaccine Statewise.csv"  
covid_data = pd.read_csv(file_path)

print("Dataset Description:")
print(covid_data.describe(include='all')) 
print("\nDataset Information:")
covid_data.info()
print("\nFirst Few Rows of Dataset:")
print(covid_data.head())

if 'Male(Individuals Vaccinated)' in covid_data.columns:
    total_males_vaccinated = covid_data['Male(Individuals Vaccinated)'].sum()
    print(f"\nTotal Number of Males Vaccinated: {total_males_vaccinated}")
else:
    print("\nColumn 'Male(Individuals Vaccinated)' not found in the dataset.")


if 'Female(Individuals Vaccinated)' in covid_data.columns:
    total_females_vaccinated = covid_data['Female(Individuals Vaccinated)'].sum()
    print(f"\nTotal Number of Females Vaccinated: {total_females_vaccinated}")
else:
    print("\nColumn 'Female(Individuals Vaccinated)' not found in the dataset.")
