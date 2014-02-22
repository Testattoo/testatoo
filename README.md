## Build Status

[![Build Status](https://travis-ci.org/Ovea/testatoo.png?branch=master)](https://travis-ci.org/Ovea/testatoo)

## How to build

### Prerequisites

#### Java 1.7

You need version **1.7** of the Java JDK installed

    $ java -version
    java version "1.7.0_45"
    
#### Maven

Follow the instructions [here](http://maven.apache.org/download.cgi#Installation "Maven Installation Instructions")
  

    $ mvn --version  
    Apache Maven 3.0.3 (r1075438; 2011-02-28 12:31:09-0500)
    Maven home: /usr/share/maven
    Java version: 1.7.0_45, vendor: Oracle Corporation
    Java home: /Library/Java/JavaVirtualMachines/jdk1.7.0_45.jdk/Contents/Home/jre
    Default locale: en_US, platform encoding: UTF-8
    OS name: "mac os x", version: "10.7.5", arch: "x86_64", family: "mac"


#### Groovy

Follow the instructions [here](http://groovy.codehaus.org/Installing+Groovy "Groovy Installation Instructions")

    $ curl -s get.gvmtool.net | bash
    $ source "$HOME/.gvm/bin/gvm-init.sh"
    $ gvm install groovy
    $ groovy -version
    Groovy Version: 2.2.1 JVM: 1.7.0_45 Vendor: Oracle Corporation OS: Mac OS X



### Installation Steps

    $ cd /wherever
    $ git clone https://github.com/Ovea/testatoo
    $ cd testatoo
    
### Mac OSX
    
Open a separate Terminal window in /wherever/testatoo/testatoo-tests/webapp/

    $ python -m SimpleHTTPServer 8080
    
In your original Terminal

    $ cd mvn package

To just build and skip the tests

    $ mvn package -Dmaven.test.skip=true
    
    
