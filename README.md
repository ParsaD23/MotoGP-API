# MotoGP-API

**MotoGP-API** is a library that reads the results of the MotoGP, 500cc, Moto2 and Moto3 classes from a website. 
The current version has been developed and tested to read the data from [Motorsport Stats](https://results.motorsportstats.com/series/motogp/).

**Race results example (2019 MotoGP Qatar Grand Prix):**

| Pos | No  | Rider            | Nat           | Team                         | Laps | Time (ms) |
|:---:|:---:|:---------------- |:------------- |:---------------------------- |:----:|:---------:|
| 1   | 4   | Andrea Dovizioso | Italy         | Mission Winnow Ducati        | 22   | 2556902   |
| 2   | 93  | Marc Márquez     | Spain         | Repsol Honda Team            | 22   | 2556925   |
| 3   | 35  | Cal Crutchlow    | Great Britain | LCR Honda Castrol            | 22   | 2557222   |
| 4   | 42  | Álex Rins        | Spain         | Team Suzuki ECSTAR           | 22   | 2557359   |
| 5   | 46  | Valentino Rossi  | Italy         | Monster Energy Yamaha MotoGP | 22   | 2557502   |

**Championship standings example (2019 season):**

| Pos | Rider            | Points |
|:---:|:---------------- |:------:|
| 1   | Marc Márquez     | 420.0  |
| 2   | Andrea Dovizioso | 269.0  |
| 3   | Maverick Viñales | 211.0  |
| 4   | Álex Rins        | 205.0  |
| 5   | Fabio Quartararo | 192.0  |

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

In order to read the data define and initialize a new variable of `MotoGPData`:

```java
MotoGPData data = new MotoGPData();
```

`MotoGPData` methods:

```java
List<RiderOnlineData> getResultsByRaceCode(Category category, int year, RaceCode raceCode, Session session);
List<RiderOnlineData> getResultsByRaceNumber(Category category, int year, int raceNumber, Session session);
List<RiderStandingsData> getChampionshipStandings(Category category, int year);
```

Before using the `getResultsByRaceCode()` method, read the `RaceCode` enum class to select the correct raceCode or check the [Motorsport Stats](https://results.motorsportstats.com/series/motogp/) website.

***Example:***

```java
List<RiderOnlineData> race = data.getResultsByRaceNumber(Category.MotoGP, 2019, 1, Session.RACE);
List<RiderOnlineData> qp2 = data.getResultsByRaceCode(Category.MotoGP, 2019, RaceCode.QAT, Session.QP2)
List<RiderStandingsData> standings = data.getChampionshipStandings(Category.MotoGP, 2019);
```

### Classes

#### RiderOnlineData

This class is used in the `MotoGPData` methods.
An object of `RiderOnlineData` contains the following information:

| Parameter     | Type     | Description                                                                         | Example                        |
|:-------------:|:--------:|:----------------------------------------------------------------------------------- |:------------------------------:|
| `number`      | `int`    | Race number of a rider; `-1` in case the data is not available                      | `46`                           |
| `name`        | `String` | Rider's name                                                                        | `Valentino Rossi`              |
| `nationality` | `String` | Rider's nationality                                                                 | `Italy`                        |
| `team`        | `String` | Rider's team name                                                                   | `Monster Energy Yamaha MotoGP` |
| `position`    | `int`    | Rider's position; `0` in case the rider did not classify or did not finish the race | `5`                            |
| `time`        | `int`    | Lap time or the total race time in milliseconds                                     | `2557502`                      |
| `laps`        | `int`    | Number of completed laps                                                            | `22`                           |

#### RiderStandingsData

This class is used in the `MotoGPData` methods.
An object of `RiderStandingsData` contains the following information:

| Paramter   | Type     | Description          | Example        |
|:----------:|:--------:| -------------------- |:--------------:|
| `name`     | `String` | Rider's name         | `Marc Márquez` |
| `position` | `int`    | Rider's position     | `1`            |
| `points`   | `double` | Rider's total points | `420.0`        |

## Release History

* **v1.0**
  
  * First release

* **v2.0** (Not compatible with the previous version)
  
  * `RiderOnlineData` contains more information
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
  
* **v2.2**
  
  * New methods
  * More information in `RiderOnlineData`

## Future Updates

*Future updates will be written here...*

## License

This project is under the MIT license. See [LICENSE](https://github.com/ParsaD23/MotoGP-API/blob/master/LICENSE) for more information.

## Fair Use

For research and study purposes only.
