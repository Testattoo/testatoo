# Testatoo

[![CircleCI](https://circleci.com/gh/Testattoo/testattoo.svg?style=svg)](https://circleci.com/gh/Testattoo/testattoo)
[![Gitter chat](https://badges.gitter.im/gitterHQ/gitter.png)](https://gitter.im/Ovea/testattoo)
[![codecov](https://codecov.io/gh/Testattoo/testattoo/branch/master/graph/badge.svg)](https://codecov.io/gh/Testattoo/testattoo)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

![Testattoo Logo](logo.svg "Testattoo")

## Documentation and Samples

The documentation is available [here](http://www.testattoo.org/documentation.html)

Samples are available [here](https://github.com/Ovea/testattoo-sample)

## Quickstart

Get Started in 5 minutes [here](http://www.testattoo.org/get-started.html)

## Downloading

Latest Testattoo version is available on [Maven Central](https://repo.maven.apache.org/maven2/org/testattoo/testattoo/)

    <dependency>
      <groupId>org.testattoo</groupId>
      <artifactId>testattoo-core</artifactId>
      <version>[LATEST]</version>
      <scope>test</scope>
    </dependency>

## Building

### Prerequisites

#### Java 1.8+

You need version **1.8** and more of the Java JDK installed

    $ java -version
    java version "1.8.0_151"
    ...
    
#### Maven

Follow the instructions [Maven Installation Instructions](http://maven.apache.org/download.cgi#Installation)

    $ mvn --version  
    Apache Maven 3.5.0
    ...

#### Installation Steps

    $ git clone https://github.com/Ovea/testatoo
    $ cd testatoo

### Install browsers
    - Install Firefox
    - Install Chrome
    - Install Edge / Safari

If you don't want to install Browser and driver you can use docker images

    - docker run -d -p 4444:4444 selenium/standalone-firefox:3.0.1-aluminum
    or
    - docker run -d -p 4444:4444 selenium/standalone-chrome:3.0.1-aluminum

In your Terminal

    $ mvn clean install  // Test on Chrome by default
    // With docker container usage
    $ mvn clean install -Dremote -Pfirefox
