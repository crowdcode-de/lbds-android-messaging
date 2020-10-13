# LBDS ANDROID MESSAGING

Set of Android objects to have a Intent based asynchronous communication between SORMAS and LBDS 

## Requirements

* java 8 +
* maven 3.6 +
* Android platform level 24

## Build

The component is built with maven

### AAR build to make use in android
'mvn clean install -PyourProfile'

### Location of your SDK

There is a profile for each operating system in the pom.xml. You may add a profile for your needs.
Or you modify the path matching the exact location of your Android SDK

