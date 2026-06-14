# FRC Differential (Tank) Drive Base

## Overview

This project contains a command-based FRC differential drive (tank drive) implementation built using WPILib and CTRE Phoenix 5.

The drivetrain uses four Talon SRX motor controllers arranged in a traditional tank drive configuration, with two motors per side. Driver control is handled through a custom Razer Xbox controller wrapper designed to provide improved support for Razer Xbox controllers such as the Razer Raiju and Razer Wolverine.

This repository can be used as a starting point for teams building a simple differential-drive robot or for teams looking to integrate enhanced controller support into their existing robot code.

---

## Features

### Drivetrain

* Differential (tank) drive system
* Four Talon SRX motor controllers
* Independent left and right side control
* Command-based WPILib structure
* Simple and expandable subsystem design

### Custom Razer Controller Wrapper

The included RazerXboxWrapper extends the functionality of WPILib's standard Xbox controller classes.

Features include:

* Automatic joystick deadband filtering
* Automatic Y-axis inversion
* Trigger support as both axes and buttons
* D-Pad triggers
* Stick-click button support
* Controller rumble support
* Access to the underlying XboxController when needed

---

## Hardware Requirements

### Required Components

* 4x Talon SRX motor controllers
* 4 drivetrain motors compatible with Talon SRX controllers

  * CIM motors
  * Mini CIM motors
  * Other supported brushed motors
* Differential drive chassis
* Xbox-compatible controller

### Recommended Controllers

* Razer Wolverine
* Razer Wolverine V2
* Razer Raiju

Standard Xbox controllers will also function correctly.

---

## Software Requirements

### Libraries

* WPILib
* CTRE Phoenix 5 Vendor Dependency

Phoenix 5 must be installed through WPILib's Vendor Dependency Manager before deploying the project.

---

## CAN IDs

Update the motor controller CAN IDs in `Constants.java` to match your robot:

java:

public static final int kLeftMotor1ID = 1;
public static final int kLeftMotor2ID = 2;
public static final int kRightMotor1ID = 3;
public static final int kRightMotor2ID = 4;

## Controls

### Driver Controls

| Input         | Function              |
| ------------- | --------------------- |
| Left Stick Y  | Left drivetrain side  |
| Right Stick Y | Right drivetrain side |
| A Button      | Stop drivetrain       |

Tank drive operation allows precise independent control of each side of the robot.

---

## Project Structure


frc.robot
│
├── Constants.java
├── RobotContainer.java
│
├── Util
│   └── RazerXboxWrapper.java
│
└── subsystems
    └── DifferentialSubsystem.java


### DifferentialSubsystem

Responsible for:

* Motor controller initialization
* Left-side drivetrain control
* Right-side drivetrain control
* Stopping all drivetrain motors

### RobotContainer

Responsible for:

* Controller initialization
* Default drive command configuration
* Button bindings

### RazerXboxWrapper

Responsible for:

* Enhanced controller support
* Input processing
* Deadband handling
* Trigger and D-Pad bindings
* Rumble functionality

## Notes

* This repository only contains drivetrain code.
* Additional mechanisms such as elevators, intakes, shooters, and autonomous routines are not included.
* Most Razer paddle buttons (M1-M6) are not exposed separately through standard XInput APIs and are typically remapped to existing controller buttons.
* Motor inversion may need adjustment depending on gearbox and drivetrain orientation.
* Has Logging for all 4 motor outputs, joysticks, and e-stop button (A), using AdvantageKit

## Future Improvements

Potential upgrades include:

* WPILib DifferentialDrive integration
* Talon SRX follower mode
* Encoder support
* Closed-loop velocity control
* Odometry
* Autonomous path following
* Current limiting and motor safety features

## License

This project follows the WPILib BSD License included with WPILib projects.
