Instruction
===========

This is a Maven project.
To build please run
```
mvn clean verify
```
from project root directory.

Commentary
==========

0. I did not externalized players list file nor configuration for sake of ease
 of verification. But it could be handy to pass it as an argument from command line.
0. I did not use any maven plugins for building fat jar, so the most convenient way of running this app is to import it to an IDE as a maven project and run the main method.
0. I used Spring's scheduled executor for roulette results generation. It uses single-thread thread pool.
0. I use a lock to block block main thread on placing bets when prizes are calculated.
0. I used the Observer pattern to decouple roulette events from way they are presented to the user (console output in this case).




