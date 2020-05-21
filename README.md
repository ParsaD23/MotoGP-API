# MotoGP-API

MotoGP-API is a library that reads grid and race results of the MotoGP class from a website. The current version has been developed and tested to read the data of the 2019 season from [Motorsport Stats](https://results.motorsportstats.com/series/motogp/).

## Getting Started

In order to use this library, import the [motogpapi-x.x.jar](https://github.com/ParsaD23/MotoGP-API/releases) release into your project.

### Prerequisites

The following libraries are required:

* JSON: [download](https://repo1.maven.org/maven2/org/json/json/20190722/json-20190722.jar)
* Jsoup: [download](https://jsoup.org/packages/jsoup-1.13.1.jar)

## Usage

In order to read the data define and initialize a new variable of *MotoGPData*:

```java
MotoGPData data = new MotoGPData();
```

*It is recommended to use this class in order not to change your code in case of updates.*

### Methods

Each method returns a list of *RiderOnlineData*:

```java
List<RiderOnlineData> getGrid(int year, RaceCode raceCode);
List<RiderOnlineData> getRaceResults(int year, RaceCode raceCode);
```

*Example:*

```java
List<RiderOnlineData> grid = data.getGrid(2019, RaceCode.QAT);
List<RiderOnlineData> raceResults = data.getRaceResults(2019, RaceCode.QAT);
```

### RiderOnlineData

An object of *RiderOnlineData* contains the following information:

* **Number**: integer value that represents the race number of a rider
* **Position**: integer value that represents the position of a rider; this value is "0" in case the rider did not classify or did not finish the race
* **Constructor**: enum value that can be *Yamaha*, *Honda*, *Ducati*, *Aprilia*, *Suzuki*, *KTM* or *NotDefined*

## Release History

* 1.0
    * First release

## Future Updates

Future updates include:
* Moto2 and Moto3 results
* Practice and qualifying  results
* More information in the RiderOnlineData class
* Coverage of multiple seasons (currently available seasons: 2019)

## License

This project is under the MIT license. See [LICENSE](https://github.com/ParsaD23/MotoGP-API/blob/master/LICENSE) for more information.

## Fair Use

For research and study purposes only.

