## Build Status

[![Build Status](https://drone.io/github.com/Ovea/testatoo/status.png)](https://drone.io/github.com/Ovea/testatoo/latest)

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

Follow the instructions [here](http://gvmtool.net/ "Groovy Installation Instructions")

    $ curl -s get.gvmtool.net | bash
    $ source "$HOME/.gvm/bin/gvm-init.sh"
    $ gvm install groovy
    $ groovy -version
    Groovy Version: 2.2.1 JVM: 1.7.0_45 Vendor: Oracle Corporation OS: Mac OS X



### Installation Steps

    $ cd /wherever
    $ git clone https://github.com/Ovea/testatoo
    $ cd testatoo

In your original Terminal

    $ cd mvn install

To just build and skip the tests

    $ mvn package -Dmaven.test.skip=true

If you want just start the jetty web server to execute the tests manually you can run in
testatoo-tests and testatoo-documentation/samples the command :

    $ mvn jetty:run -Pdev
    
    TODO
    assert that button have text 'Add a BBM configuration'
    expect button have text 'Add a BBM configuration'
    button should have text 'Add a BBM configuration'
    
    
