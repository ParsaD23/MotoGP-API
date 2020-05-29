# MotoGP-API

MotoGP-API is a library that reads the results of the MotoGP, 500cc, Moto2 and Moto3 classes from a website. 
The current version has been developed and tested to read the data from [Motorsport Stats](https://results.motorsportstats.com/series/motogp/).

### Available data

* Classes
    * Moto3: 2012 - today
    * Moto2: 2010 - today
    * MotoGP/500cc: 1949 - today

* Sessions
    * Free practices
    * Qualifying practices
    * Grid
    * Race
    
* Riders' championship standings

## Getting Started

In order to use this library, import the latest [motogpapi-x.x.jar](https://github.com/ParsaD23/MotoGP-API/releases) release into your project.

### Prerequisites

The following libraries are required:

* JSON: [download](https://repo1.maven.org/maven2/org/json/json/20190722/json-20190722.jar)
* Jsoup: [download](https://jsoup.org/packages/jsoup-1.13.1.jar)

Import them into your project.

## Usage

*Important: remember that some sessions have not always existed in the MotoGP calendar, or the data may not be available on the website. 
In that case an empty List will be returned.*

In order to read the data define and initialize a new variable of *MotoGPData*:

```java
MotoGPData data = new MotoGPData();
```

*It is recommended to use this class in order not to change your code in case of updates.*

### RiderOnlineData

This class is used in the [Session results methods](https://github.com/ParsaD23/MotoGP-API#session-result-methods).
An object of *RiderOnlineData* contains the following information:

* **Number**: Integer value that represents the race number of a rider; this value is "-1" in case the data is not available
* **Name**: String value
* **Position**: Integer value that represents the position of a rider; this value is "0" in case the rider did not classify or did not finish the race
* **Team**: String value

### RiderStandingsData

This class is used in the [Championship standings methods](https://github.com/ParsaD23/MotoGP-API#championship-standings-methods).
An object of *RiderStandingsData* contains the following information:

* **Position**: Integer value
* **Name**: String value
* **Points**: Double value

### Session result methods

Each method returns a list of *RiderOnlineData*:

```java
List<RiderOnlineData> getFreePractice1(Category category, int year, RaceCode code);
List<RiderOnlineData> getFreePractice2(Category category, int year, RaceCode code);
List<RiderOnlineData> getFreePractice3(Category category, int year, RaceCode code);
List<RiderOnlineData> getFreePractice4(Category category, int year, RaceCode code);
List<RiderOnlineData> getQualifying1(Category category, int year, RaceCode code);
List<RiderOnlineData> getQualifying2(Category category, int year, RaceCode code);
List<RiderOnlineData> getQualifying(Category category, int year, RaceCode code);
List<RiderOnlineData> getGrid(Category category, int year, RaceCode raceCode);
List<RiderOnlineData> getRaceResults(Category category, int year, RaceCode raceCode);
```

Before using these methods, read the RaceCode enum class to select the correct raceCode.

***Example:***

```java
List<RiderOnlineData> qualifying = data.getQualifying(Category.MotoGP, 2000, RaceCode.ESP);
List<RiderOnlineData> grid = data.getGrid(Category.Moto2, 2019, RaceCode.QAT);
List<RiderOnlineData> raceResults = data.getRaceResults(Category.MotoGP, 2015, RaceCode.ITA);
```

Results of the MotoGP class at Mugello in 2019 printed:

```text
===================== RACE RESULTS =====================
1	Danilo Petrucci		9	Mission Winnow Ducati
2	Marc Márquez		93	Repsol Honda Team
3	Andrea Dovizioso	4	Mission Winnow Ducati
4	Álex Rins		42	Team Suzuki ECSTAR
5	Takaaki Nakagami	30	LCR Honda IDEMITSU
6	Maverick Viñales	12	Monster Energy Yamaha MotoGP
7	Michele Pirro		51	Mission Winnow Ducati
8	Cal Crutchlow		35	LCR Honda Castrol
9	Pol Espargaró		44	Red Bull KTM Factory Racing
10	Fabio Quartararo	20	Petronas Yamaha SRT
[...]
```

### Championship standings methods

```java
List<RiderStandingsData> getChampionshipStandings(Category category, int year);
```

***Example:***

```java
List<RiderStandingsData> standings = data.getChampionshipStandings(Category.MotoGP, 2019);
```

Standings of the 2019 MotoGP season printed:

```text
========== STANDINGS ==========
1	Marc Márquez		420.0
2	Andrea Dovizioso	269.0
3	Maverick Viñales	211.0
4	Álex Rins               205.0
5	Fabio Quartararo	192.0
6	Danilo Petrucci		176.0
7	Valentino Rossi		174.0
8	Jack Miller	        165.0
9	Cal Crutchlow		133.0
10	Franco Morbidelli	115.0
[...]
```

## Release History

* **v1.0**
    * First release


* **v2.0** (Not compatible with the previous version)
    * *RiderOnlineData* contains more information
    * Free Practices, Qualifying Practices and Warm-up added
    * Moto3 and Moto2 classes added
    * Coverage of all Moto3 seasons (2012 - today)
    * Coverage of all Moto2 seasons (2010 - today)
    * Coverage of multiple MotoGP season (2005 - today)
    * Improved project structure


* **v2.0.1**
    * Added 1949 - 2004 MotoGP/500cc seasons
    * Bug fix
    
    
* **v2.1**
    * Championship standings results added

## Future Updates

*Future updates will be written here...*

## License

This project is under the MIT license. See [LICENSE](https://github.com/ParsaD23/MotoGP-API/blob/master/LICENSE) for more information.

## Fair Use

For research and study purposes only.
