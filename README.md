# Market analysis test

[![Build Status](https://travis-ci.org/sideeffffect/testmarket.svg)](https://travis-ci.org/sideeffffect/testmarket)

This is a simple Java program that parses input CSV file.
From the data it computes reports for each country and quarter of year.
These reports are then exported to an HTML document.

## Tests

You can run tests via gradle:

```
gradle test
```

## TODO

 * units for test data not integers
 * is 0 acceptable in input in units column
 * checked vs unchecked exceptions
 * style/format/colors in table?
 * format of exported CSV/Excel
 * special class to run
