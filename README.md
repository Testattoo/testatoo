# Testatoo

[![CircleCI](https://circleci.com/gh/Testatoo/testatoo.svg?style=svg)](https://circleci.com/gh/Testatoo/testatoo)
[![Gitter chat](https://badges.gitter.im/gitterHQ/gitter.png)](https://gitter.im/Ovea/testatoo)
[![codecov](https://codecov.io/gh/Testatoo/testatoo/branch/master/graph/badge.svg)](https://codecov.io/gh/Testatoo/testatoo)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

[.left.text-left]
image::logo.svg[]

## Documentation and Samples

The documentation is available http://www.testatoo.org/documentation.html[here]

Samples are available https://github.com/Ovea/testatoo-sample[here]

## Quickstart

Get Started in 5 minutes http://www.testatoo.org/get-started.html[here]

## Downloading

Latest Testatoo version is available on {download-url}[Maven Central]

    <dependency>
      <groupId>org.testatoo</groupId>
      <artifactId>testatoo</artifactId>
      <version>2.0.b27</version>
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

Follow the instructions http://maven.apache.org/download.cgi#Installation["Maven Installation Instructions"]

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
