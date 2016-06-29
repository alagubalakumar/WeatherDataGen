# WeatherDataGen
Project to generate test data

Commands to generate real time forecast data

java -jar WeatherGenData-1.0-jar-with-dependencies.jar -f <directory path> -m false

Example:
1.java -jar WeatherGenData-1.0-jar-with-dependencies.jar -f E:\\MyApps -m false
2.java -jar WeatherGenData-1.0-jar-with-dependencies.jar -f /home/weatherdata -m false

The above command will connect to openweathermap api and gather 16 days data of the following cities
"Melbourne","Brisbane","Perth","Adelaide","Canberra","Hobart","Darwin"


Second way to generate mock data is

java -jar WeatherGenData-1.0-jar-with-dependencies.jar -c 200 -f <directory path> -sd <start date> -ed <end date> -m true

Example:
java -jar WeatherGenData-1.0-jar-with-dependencies.jar -c 200 -f "E:\\MyApps" -sd "01-01-2016" -ed "29-06-2016" -m true
