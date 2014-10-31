## Build Status

[![Build Status](https://drone.io/github.com/Ovea/testatoo/status.png)](https://drone.io/github.com/Ovea/testatoo/latest)

## How to build

### Prerequisites

#### Java 1.7

You need version **1.7** and more **(1.8 ...)** of the Java JDK installed

    $ java -version
    java version "1.8.0_25"
    Java(TM) SE Runtime Environment (build 1.8.0_25-b17)
    Java HotSpot(TM) 64-Bit Server VM (build 25.25-b02, mixed mode)

    
#### Maven

Follow the instructions [here](http://maven.apache.org/download.cgi#Installation "Maven Installation Instructions")
  

    $ mvn --version  
    Apache Maven 3.0.5
    Maven home: /usr/share/maven
    Java version: 1.8.0_25, vendor: Oracle Corporation
    Java home: /usr/lib/jvm/java-8-oracle/jre
    Default locale: en_US, platform encoding: UTF-8
    OS name: "linux", version: "3.16.0-24-generic", arch: "amd64", family: "unix"
    

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

### IDE Integration

For a better integration on your IDE you can add at the root level of the project
this pom aggregator

    <project>
  
        <modelVersion>4.0.0</modelVersion>
    
        <groupId>com.ovea.testatoo</groupId>
        <artifactId>testatoo</artifactId>
        <version>testatoo-1</version>
        <packaging>pom</packaging>
        <name>Testatoo</name>
    
        <modules>
            <module>testatoo-pom</module>
            <module>testatoo-core</module>
            <module>testatoo-tests</module>
            <module>testatoo-documentation</module>
            <module>testatoo-site</module>
        </modules>
    
    </project>
