<h1 align="center">MotoGP-API</h1>

<p align="center">
  <a href="https://github.com/ParsaD23/MotoGP-API/releases"><img alt="GitHub release (latest by date)" src="https://img.shields.io/github/v/release/ParsaD23/MotoGP-API?color=light%20green"></a> <img alt="GitHub all releases" src="https://img.shields.io/github/downloads/ParsaD23/MotoGP-API/total?color=light%20green"> 
</p>

**MotoGP-API** is a library that reads the results of the MotoGP, 500cc, Moto2 and Moto3 classes from a website. 
The current version has been developed and tested to read the data from [Motorsport Stats](https://results.motorsportstats.com/series/motogp/).

## Contents

* [Home](https://github.com/ParsaD23/MotoGP-API/wiki)
* [Getting Started](https://github.com/ParsaD23/MotoGP-API/wiki/1.-Getting-Started)
* [Domain Classes](https://github.com/ParsaD23/MotoGP-API/wiki/2.-Domain-classes)
* [Usage](https://github.com/ParsaD23/MotoGP-API/wiki/3.-Usage)
* [Release History](https://github.com/ParsaD23/MotoGP-API/wiki/4.-Release-History)

## Available data

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

**Race results example (2019 MotoGP Qatar Grand Prix):**

| Pos | No  | Rider            | Nat           | Team                         | Laps | Time (ms) |
|:---:|:---:|:---------------- |:------------- |:---------------------------- |:----:|:---------:|
| 1   | 4   | Andrea Dovizioso | Italy         | Mission Winnow Ducati        | 22   | 2556902   |
| 2   | 93  | Marc Márquez     | Spain         | Repsol Honda Team            | 22   | 2556925   |
| 3   | 35  | Cal Crutchlow    | Great Britain | LCR Honda Castrol            | 22   | 2557222   |
| 4   | 42  | Álex Rins        | Spain         | Team Suzuki ECSTAR           | 22   | 2557359   |
| 5   | 46  | Valentino Rossi  | Italy         | Monster Energy Yamaha MotoGP | 22   | 2557502   |

**Championship standings example (2019 season):**

| Pos | Rider            | Points | Results                         |
|:---:|:---------------- |:------:|:------------------------------- |
| 1   | Marc Márquez     | 420.0  | `[20.0, 25.0, 0.0, 25.0, ...]`  |
| 2   | Andrea Dovizioso | 269.0  | `[25.0, 16.0, 13.0, 13.0, ...]` |
| 3   | Maverick Viñales | 211.0  | `[9.0, 0.0, 5.0, 16.0, ...]`    |
| 4   | Álex Rins        | 205.0  | `[13.0, 11.0, 25.0, 20.0, ...]` |
| 5   | Fabio Quartararo | 192.0  | `[0.0, 8.0, 9.0, 0.0, ...]`     |



## Getting Started

### Installing without Maven

Import the following libraries in your project:

* the latest motogpapi-x.x.jar release (in the [release section](https://github.com/ParsaD23/MotoGP-API/releases))
* Gson: [download](https://jar-download.com/artifacts/com.google.code.gson/gson/2.8.6)
* Jsoup: [download](https://jsoup.org/packages/jsoup-1.13.1.jar)

### Installing with Maven

First, you need to download the latest motogpapi-x.x.jar release (in the [release section](https://github.com/ParsaD23/MotoGP-API/releases)) and then install it in your local maven repository. Run the following command:

```shell
mvn install:install-file
  -Dfile=<path-to-jar-file>
  -DgroupId="com.github.parsad23"
  -DartifactId="motogpapi"
  -Dversion="3.0.4"
  -Dpackaging="jar"
  -DgeneratePom=true
```

Then add the motogpapi library to the dependencies in the `pom.xml` file of your project:

```xml
<dependency>
    <groupId>com.github.parsad23</groupId>
    <artifactId>motogpapi</artifactId>
    <version>3.0.4</version>
</dependency>
```

## Usage

Look at the [wiki page](https://github.com/ParsaD23/MotoGP-API/wiki) for a detailed description.

## License

This project is under the MIT license. See [LICENSE](https://github.com/ParsaD23/MotoGP-API/blob/master/LICENSE) for more information.

## Fair Use

For research and study purposes only.
