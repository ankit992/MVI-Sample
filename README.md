# MVI-Sample

### Travis build Status
[![Build Status](https://api.travis-ci.org/ankit992/MVI-Sample.png)](https://travis-ci.org/ankit992/MVI-Sample)


### UI Tests
1. Switch to QA build variant from build variants option 
2. Add a new Build Configuration for Instrumentation tests which include all modules 
3. Run the tests using that configuration from Android studio 

Alternatively 

command `./gradlew connectedQaAndroidTest` can be used for running UI tests

### Unit Tests
1. Unit tests can be ran by using the Android Studio UI by doing a right click on the test and then using option `Run this test`

command `./gradlew testQaUnitTest` can be used in order to run the Unit tests
