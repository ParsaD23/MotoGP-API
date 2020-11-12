# MotoGP-API

Current version: `3.0`
Latest update: November 12, 2020

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

* Constructors' championship standings

* Teams' championship standings

## Getting Started

In order to use this library, import the latest [motogpapi-x.x.jar](https://github.com/ParsaD23/MotoGP-API/releases) release into your project.

### Prerequisites

The following libraries are required:

* JSON: [download](https://repo1.maven.org/maven2/org/json/json/20190722/json-20190722.jar)
* Jsoup: [download](https://jsoup.org/packages/jsoup-1.13.1.jar)

Import them into your project.

## Usage

Look at the [wiki page](https://github.com/ParsaD23/MotoGP-API/wiki) for a detailed description.

## License

This project is under the MIT license. See [LICENSE](https://github.com/ParsaD23/MotoGP-API/blob/master/LICENSE) for more information.

## Fair Use

For research and study purposes only.
