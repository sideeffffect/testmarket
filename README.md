# Market analysis test

[![Build Status](https://travis-ci.org/sideeffffect/testmarket.svg)](https://travis-ci.org/sideeffffect/testmarket)

This is a simple Java program that parses input CSV file.
From the data it computes reports for each country and quarter of year.
These reports are then exported to an HTML document.


## Classes overview

There are 4 most important classes.

`cz.sideeffect.testmarket.Report` represents a report for one country in one quarter.

`cz.sideeffect.testmarket.input.DataReader` wraps around `CSVReader` and parses the input CSV file.

`cz.sideeffect.testmarket.export.ExportHtml` can export a list of reports into a pretty HTML document.

`cz.sideeffect.testmarket.Main` is used for executing the program from commandline.



## Usage

This project is managed by [gradle](https://gradle.org/).


### Running

You can run the program via gradle.
The CSV input is read from `stdin`.
Reports are exported as HTML and written to `stdout`.

```
gradle -q run < src/test/resources/data.csv > reports.html
```


### Tests

The project is automatically tested at [Travis-CI](https://travis-ci.org/sideeffffect/testmarket).
You can run tests yourself via gradle:

```
gradle test
```


### Documentation

The documentation is located in `build/docs/javadoc`.
It is build via gradle:

```
gradle javadoc
```


## TODO

 * units for test data not integers
 * is 0 acceptable in input in units column
 * checked vs unchecked exceptions
 * style/format/colors in table?
 * format of exported CSV/Excel
