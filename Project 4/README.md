# Project Four

Submission Date: 7 December 2017

The game can be run with the Driver class, located in project4/src/planetwars/publicapi

The strategy that I implemented is an attacking strategy. It goes as follows-

The ivisPlanets list contains planets that is either owned by or adjacent to a planet owned by
the player. At the start of every turn(takeTurn()), the ivisPlanets list is updated.

For each planet in ivisPlanets, if the planet is neutral, people are automatically shuttled to 
the neutral planet. If it is an enemy planet and the player has enough people to safely take over 
the enemey planet, then the player will send the required amount to take over the planet.
Otherwise, the player calls for reinforcements from an adjacent player owned planet. This operation
of sending and reinforcing takes place within the sendODST() method. 


## Files Created by Connor Hanlon
  * MyStrategy.java
  -located in project4/src/planetwars/strategies/MyStrategy.java
  * project4.iml
  -located in project5
  
## Key Takeaways
  * Use of a HashMap, which has benefit of speed of access and 
  dealing with unordered data
  * Use of numerous provided API to create a feasible, working
  strategy
  * Use of generics
