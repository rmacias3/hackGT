import random
import csv
file = open("data_created.csv", "wb")
spamwriter = csv.writer(file, delimiter=',')
spamwriter.writerow(["casuals", "jackets", "shoes", "formals", "zip_code", "store_id", "month", "year", "average_household_income","male_population","female_population","male_median_age","female_median_age","sales"])

stores = 8; months =12; categories = 5
for year in [2010,2011,2012,2013,2014]:
	for month in range(months):
		for i in range(stores):
			rand_num = int(random.gauss(5, 1))
			if (1<=month<=3 and i in [1,2,3]):
				rand_num +=5
			if (9<=month<=10 and i in [7,8]):
				rand_num +=5
			if (11<=month<=12 and i in [1,2,3,7]):
				rand_num +=15
			if(5<=month<=8 and i in [1,4,5,6]):
				rand_num+=8
			if (1<=i<=3):
				zip_code="30309";  avg_household_income = 39421;male_population=25530;female_population=24206;male_median_age=29.4;female_median_age=29.4
			if (4<=i<=6):
				zip_code="30318"; avg_household_income = 39421;male_population=25530;female_population=24206;male_median_age=29.4;female_median_age=29.4
			if (7<=i<=8):
				zip_code="30313"; avg_household_income = 39421;male_population=25530;female_population=24206;male_median_age=29.4;female_median_age=29.4

			if (i == 1):
				spamwriter.writerow([1,1,0,1,zip_code, i, month, year, avg_household_income, male_population, female_population, male_median_age, female_median_age, rand_num])
			if (i == 2):
				spamwriter.writerow([0,1,1,0,zip_code, i, month, year, avg_household_income, male_population, female_population, male_median_age, female_median_age, rand_num])
			if (i == 3):
				spamwriter.writerow([0,1,0,0,zip_code, i, month, year, avg_household_income, male_population, female_population, male_median_age, female_median_age, rand_num])
			if (i == 4):
				spamwriter.writerow([1,0,1,1,zip_code, i, month, year, avg_household_income, male_population, female_population, male_median_age, female_median_age, rand_num])
			if (i == 5):
				spamwriter.writerow([1,0,1,0,zip_code, i, month, year, avg_household_income, male_population, female_population, male_median_age, female_median_age, rand_num])
			if (i == 6):
				spamwriter.writerow([1,0,1,1,zip_code, i, month, year, avg_household_income, male_population, female_population, male_median_age, female_median_age, rand_num])
			if (i == 7):
				spamwriter.writerow([0,1,0,1,zip_code, i, month, year, avg_household_income, male_population, female_population, male_median_age, female_median_age, rand_num])
			if (i == 8):
				spamwriter.writerow([0,0,1,1,zip_code, i, month, year, avg_household_income, male_population, female_population, male_median_age, female_median_age, rand_num])
