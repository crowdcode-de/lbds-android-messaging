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

# Using Messaging Lib to communicate with LBDS

## Configuration steps needed

TODO: verify client side configuration

## Program steps needed

Inside an Activity or Service

### Instantiate your http call

`HttpMethod method = new HttpMethod(HttpMethod.MethodType.GET, "http://www.google.com");`

 A very simple abstraction of the HttpMethods can be found in the lbds-http module. 
 See https://github.com/crowdcode-de/lbds-http for details. 

### Create a send intent

`LbdsSendIntent sendIntent = new LbdsSendIntent(method);`

### Fire the intent
`startService(endIntent);`


# Receiving Data from LBDS 

After a request has been processed, the LBDS-Subsystem will emit an Intent for Sormas.
This intent is named LbdsResponseIntent and it is also the location where the phsyical receiver configuration -
 like package and app name - is configured.

## Preconfiguration

### android-manifest.xml

A receiver for the intent must be implemented.

`        <service
             android:name=".LbdsRecevierComponent"
             android:enabled="true"
             android:exported="true">
             <intent-filter>
                 <action android:name="android.intent.action.SEND" />
                 <category android:name="android.intent.category.DEFAULT" />
             </intent-filter>
         <service> `  
         
The current package for SORMAS has been taken from github and was set to _de.symeda.sormas.app_ - this may be subject to change
The current Receiver Component was set to _de.symeda.sormas.app.LbdsRecevierComponent_ - this also may be subject of a change


### Receiver implementation

A receiver implementation must be located in the a.m. package and it must implement an IntentService

`public class LbdsRecevierComponent extends IntentService ...`

# Key Exchange Functionality

In order to exchange public Keys for encrypting transport data between SORMAS and LBDS 
there is a key exchange functionality (kex). 

* Every KEX Intent will contain ONE key when emitted towards a partner
* Every KEX Reply intent will containt BOTH keys when sent by the requested partner

Example:

* SORMAS emits a LbdsPropagateKexToLbdsIntent will soleley contain the SORMAS public key
* This intent is consumed by LBDS, the current public key will be replaced
* LBDS emits a LbdsPropagateKexToSormasIntent, which will contain both keys; the new SORMAS key plus the current LBDS key

_A response intent always has both keys!_

## Useful to know

* You may use the Constants package to differ the Intent Type

```
final String intentType = intent.getStringExtra(Constants.INTENT_TYPE);
   if (intentType != null && !intentType.trim().isEmpty()) {
     IntentType type = IntentType.valueOf(intentType);
       switch (type) {
         case HTTP_SEND_INTENT:
           final String httpContainer = intent.getStringExtra(Constants.HTTP_CONTAINER);
             HttpContainer container = HttpContainer.deserializePackedHttpContainer(httpContainer);
             final HighLevelService highLevelService = ApplicationContext.getBean(HighLevelService.class);
             final String responder = "192.168.178.23:8080";
             final TransmissionSession session = highLevelService.submitHttpMethod(container.getMethod(), responder);
             // ...
             break;
         case KEX_TO_LBDS_INTENT:
           final String publicKey = intent.getStringExtra(Constants.SORMAS_KEY);
           final PublicKey key = KeySerializationUtil.deserializePublicKey(publicKey);
           //....
           break;
                  case HTTP_RESPONSE_INTENT:
                      break;
                  case KEX_TO_SORMAS_INTENT:
                      break;
              }
          }
        ....
```

## To be done
