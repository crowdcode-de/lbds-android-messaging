# LBDS ANDROID MESSAGING

Set of Android objects to have a Intent based asynchronous communication between SORMAS and LBDS 

## Requirements

* java 8 +
* maven 3.6 +
* Android platform level 24

## Build

The component is built with maven

### regular JAR file as required by backend services
`mvn clean install`

### AAR build to make use in android
'mvn clean install -DskipTests=true -fpom-aar.xml'

