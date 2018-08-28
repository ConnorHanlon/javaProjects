# Project Two
Submission Date: 8 October 2017

## Overview
Screen saver application that animates balls bouncing and colliding inside of a box

A ball class was created that extended the circle class provided by instuctor. New methods 
were implemented specific to the ball class.

A ball screen saver class supports the animation of bouncing balls, as an extension of the 
provided abstract base class AnimationFrame. 

Key Features implemented/overrided by BallScreenSaver class:
  * action() - updates states of any animated objects
  * draw() - draws the graphics for each frame after action method updates the state
  * processKeyEvent() - provides keyboard control of speed of balls and saving image of ball heatmap
  * BallScreenSaver() - constructor
  
Key features implemented by CollisionLogger class:
  * Storage of information using two dimensional integer array
  * collide() - logs collision at the position of two balls in 2D array
  * getNormalizedHeatMap() - returns current state of collision log rescaled to largest with value of 255


## Files Created by Connor Hanlon
* Ball.java
* BallScreenSaver.java
* CollisionLogger.java

## Key Takeaways
  * Extending classes and implementing interfaces
  * processing key events
  * Working with 2D arrays
  * Handling exceptions using try catch blocks

