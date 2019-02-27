# JKodi Wrapper

JKodi Wrapper is a wrapper for the [Kodi JSON API](https://kodi.wiki/view/JSON-RPC_API/v8) written in [Kotlin](https://kotlinlang.org/). It is fully compatible with Java and Kotlin.

## Building and Requirements

To build a *.jar file JKodi Wrapper requires:

* [Gradle](https://gradle.org/) - is used to resolve dependencies and build a jar file.
* [JDK 8+](http://www.oracle.com/technetwork/java/javase/downloads/index.html) - JKodi Wrapper is developed with Oracle JDK 8 but should also work with OpenJDK. <br /><br />

To build JKodi Wrapper use the following command:

```shell
gradle clean build
```

The \*.jar file can be found in the **build/libs** directory afterwards. Even though, easier is it to directly install it into your maven repository using
```shell
gradle install
```

In order to use JKodi Wrapper, Kodi remote control must be enabled. To do so perform the following steps:

1.) Go to Settings
![Settings](https://raw.github.com/cf86/MP3ToolKit/master/Screenshots/ID3AddRemove.png)

2.) Open the Services Menu
![Service](https://raw.github.com/cf86/MP3ToolKit/master/Screenshots/ID3AddRemove.png)

3.) Select the Control Tab
![Control](https://raw.github.com/cf86/MP3ToolKit/master/Screenshots/ID3AddRemove.png)

4.) Enable Remote control
![Remote Access](https://raw.github.com/cf86/MP3ToolKit/master/Screenshots/ID3AddRemove.png)


## How to use

### Instantiate

During instantiation the Constructor just needs to IP, Port and a boolean to specifiy the use of TLS.

```java
// Java
Kodi kodi = new Kodi("127.0.0.1", 8080, false);
// Kotlin
val kodi = Kodi("127.0.0.1", 8080, false)
```

### Make a call
After an instance is instantiated, a component can directly be accessed and offers all methods defined in the specfic module of the [Kodi JSON API](https://kodi.wiki/view/JSON-RPC_API/v8).
For example to get all active players, the player module needs to be used.
The KodiID is just an ID which will just be returned, so you can use whatever ID you want:
 
```java
// Java
List<Player> players = kodi.getPlayer().getActivePlayers(new KodiID(1)).getResult();
// Kotlin
val players = kodi.player.getActivePlayers(KodiID(1)).result
```

to start/pause a given player, you could e.g. use:
```java
// Java
PlayerSpeed state = kodi.getPlayer().playPause(new KodiID(1), new PlayerID(1)).getResult();
// Kotlin
val speed = kodi.player.playPause(KodiID(1), PlayerID(1)).result
```

All other modules work in a similar manner.

## Copyright

Copyright (c) 2019 Christian Feier. See licence.txt for details.
