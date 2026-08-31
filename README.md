# SOSApp

SOS Help is a Java-based community assistance app that connects people who need help with nearby users willing to assist.

## Features
- Send an SOS request with a selected help type (medical, transport, food, safety, other)
- Match nearby users using a distance-based algorithm
- Show available helpers in a JavaFX interactive UI

## Tech Used
- Java (OOP with classes, objects, interfaces-friendly design)
- `ArrayList` and `HashMap` for user storage and type indexing
- Distance algorithm (Haversine) for nearby user search
- JavaFX for the desktop interface

## Build and Run
```bash
mvn clean test
mvn javafx:run
```
