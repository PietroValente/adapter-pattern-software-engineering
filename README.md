# Adapter pattern | ITD

<p align="center">
    <img src="https://www.unidformazione.com/wp-content/uploads/2018/04/unipd-universita-di-padova.png" width="250" alt="University of Padua"/>
</p>

## Overview

For this project it is assumed to be in Java Micro Edition environment, precisely CLDC1.1 
(https://docs.oracle.com/javame/config/cldc/ref-impl/cldc1.1/jsr139/index.html). 

The goal is to use in this environment a class library (myLib) born in the J2SE 1.4.2 environment
(http://geas.dei.unipd.it/jdk1.4.2/docs/api/
https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/index.html).

In particular, the library contains classes that make use of the List interface, and therefore of those to it connected (Collection, Iterator, ListIterator), and also Map, Set, Collection interface of the Java2 Collections Framework version 1.4.2.

In the development of the project it was considered **essential** that the code uses only the features present in CLDC 1.1 to create the adapter.

In the Pattern Report.pdf file you can find a detailed description of how the Adapter pattern was applied for the realization of what has been described.

In the MethodsTable.pdf file you can instead find a list of the methods that have been implemented for each class, those marked in yellow have not been requested and therefore not implemented.

## ITD

The _Implementation and Test deliverable_ (ITD) provides for the implementation of a prototype of the project with related tests and documentation relating to the tests performed.

The JUnit framework was used for the tests.

## Compile and usability

First compile the interfaces of the package adapter, which are:

* Collection
* Iterator
* List
* ListIterator
* Map
* Set

After all other files, it is important that all files are in the same folder (Source). Now you can use the adapters in your file.
