// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// package
package frc.robot.subsystems;

import org.littletonrobotics.junction.Logger;

// imports
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

// This is a subsystem that controls 4 SRX motor controllers for a differential tank drive. It has methods to drive the left and right sides of the robot, as well as a method to stop all motors. The periodic and simulationPeriodic methods are currently empty, but can be used for any necessary updates or simulations in the future.
public class DifferentialSubsystem extends SubsystemBase {

  // Motor controller instances
  private final TalonSRX m_leftMotor1;
  private final TalonSRX m_leftMotor2;
  private final TalonSRX m_rightMotor1;
  private final TalonSRX m_rightMotor2;

  // Constructor initializes the motor controllers with their respective IDs from the Constants class
  public DifferentialSubsystem() {
    m_leftMotor1 = new TalonSRX(Constants.TankDriveConstants.kLeftMotor1ID);
    m_leftMotor2 = new TalonSRX(Constants.TankDriveConstants.kLeftMotor2ID);
    m_rightMotor1 = new TalonSRX(Constants.TankDriveConstants.kRightMotor1ID);
    m_rightMotor2 = new TalonSRX(Constants.TankDriveConstants.kRightMotor2ID);
  }

  // Method to drive the left side of the robot at a specified speed
  public void driveLeft(double speed){
    m_leftMotor1.set(TalonSRXControlMode.PercentOutput, speed);
    m_leftMotor2.set(TalonSRXControlMode.PercentOutput, speed);
  }

  // Method to drive the right side of the robot at a specified speed (inverted)
  public void driveRight(double speed){
    m_rightMotor1.set(TalonSRXControlMode.PercentOutput, -1 * speed);
    m_rightMotor2.set(TalonSRXControlMode.PercentOutput, -1 * speed);
  }

  // Method to e-stop all motors
  public void stopMotors(){
    driveLeft(0);
    driveRight(0);
  }

  // periodic functions
  @Override
  public void periodic() {
    Logger.recordOutput("Tank Drive/Left Motor 1", m_leftMotor1.getMotorOutputPercent());
    Logger.recordOutput("Tank Drive/Left Motor 2", m_leftMotor2.getMotorOutputPercent());
    Logger.recordOutput("Tank Drive/Right Motor 1", m_rightMotor1.getMotorOutputPercent());
    Logger.recordOutput("Tank Drive/Right Motor 2", m_rightMotor2.getMotorOutputPercent());
  }

  @Override
  public void simulationPeriodic() {
  }
}
