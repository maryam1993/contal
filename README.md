<h1 align="center"> ATM </h1> <br>



## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Assumptions](#assumptions)
- [Quick Start](#quick-start)
- [Run Local](#run-local)
- [Testing](#testing)


## Introduction

This is a simple spring boot project that a customer can dispense money from an ATM. 
There are different denominations available for the customer to select. There are also specific notes 
that based on these, denominations can differ.

## Features

The way this program works is that at first a list of options of denominations will be shown
to the user. If the selected amount is not in the list of options, an exception will be thrown.
If the machine has that amount of money, it will be dispensed. Otherwise, an exception will be thrown.
Based on the entered amount and the available cash amount, money will be dispensed. Although
user would not be able to choose which combination of notes to get since the system is in charge of that.
For example if the amount is 150, and we do not have 3x$50, the combination would be 1x$50 and 6x20$.

In this project, the database is a csv file which is holding the amount of each expense and will be modified after each dispense.

* The data will be changed in case of rerunning or stopping the program.

## Assumptions

As it was mentioned in the pdf file, for 200 amount, there is a specific condition. This amount will be dispensed if
the available notes would be 3x$50 and 8x$20.

* The number of available cash of each not is specified in the csv file.
* The name of the csv file is specified on the properties file.



## Quick Start
This is a spring boot project so with running the AtmApplication.java the project will be up and running on port 8080.
There is a controller that contains main services including dispenseMoney() which is for instance accessible through 
url: ip:port/atm/dispense/{amount}.


## Run Local
In order to run and test the application you can either run it in debug mode using your
IDE env or run the command below.

```bash
$ mvn spring-boot:run
```

## Testing
The unit test are available in test package. AtmSuite.java has combined all the tests and  can be executed all
together.
Another way to run them is through below command.
```bash
$ mvn clean package
```









