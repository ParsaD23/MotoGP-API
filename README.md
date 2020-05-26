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

## Getting Started

In order to use this library, import the latest [motogpapi-x.x.jar](https://github.com/ParsaD23/MotoGP-API/releases) release into your project.

### Prerequisites

The following libraries are required:

* JSON: [download](https://repo1.maven.org/maven2/org/json/json/20190722/json-20190722.jar)
* Jsoup: [download](https://jsoup.org/packages/jsoup-1.13.1.jar)

Import them into your project.

## Usage

### Methods

*Important: remember that some sessions have not always existed in the MotoGP calendar, or the data may not be available on the website. 
In this case an empty List will be returned.*

In order to read the data define and initialize a new variable of *MotoGPData*:

```java
MotoGPData data = new MotoGPData();
```

*It is recommended to use this class in order not to change your code in case of updates.*

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

*Example:*

```java
List<RiderOnlineData> qualifying = data.getQualifying(Category.MotoGP, 2000, RaceCode.ESP);
List<RiderOnlineData> grid = data.getGrid(Category.Moto2, 2019, RaceCode.QAT);
List<RiderOnlineData> raceResults = data.getRaceResults(Category.MotoGP, 2015, RaceCode.ITA);
```

### RiderOnlineData

An object of *RiderOnlineData* contains the following information:

* **Number**: Integer value that represents the race number of a rider; this value is "-1" in case the data is not available
* **Name**: String value
* **Position**: Integer value that represents the position of a rider; this value is "0" in case the rider did not classify or did not finish the race
* **Team**: String value

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


* **v2.1**
    * Coverage of all MotoGP/500cc seasons (1949 - today)
    * Bug fix

## Future Updates

*Future updated wil be written here...*

## License

This project is under the MIT license. See [LICENSE](https://github.com/ParsaD23/MotoGP-API/blob/master/LICENSE) for more information.

## Fair Use

For research and study purposes only.
