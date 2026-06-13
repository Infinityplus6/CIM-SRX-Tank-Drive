// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DifferentialSubsystem extends SubsystemBase {

  private final TalonSRX m_leftMotor1;
  private final TalonSRX m_leftMotor2;
  private final TalonSRX m_rightMotor1;
  private final TalonSRX m_rightMotor2;

  public DifferentialSubsystem() {
    m_leftMotor1 = new TalonSRX(Constants.TankDriveConstants.kLeftMotor1ID);
    m_leftMotor2 = new TalonSRX(Constants.TankDriveConstants.kLeftMotor2ID);
    m_rightMotor1 = new TalonSRX(Constants.TankDriveConstants.kRightMotor1ID);
    m_rightMotor2 = new TalonSRX(Constants.TankDriveConstants.kRightMotor2ID);
  }

  public void driveLeft(double speed){
    m_leftMotor1.set(TalonSRXControlMode.PercentOutput, -1 * speed);
    m_leftMotor2.set(TalonSRXControlMode.PercentOutput, -1 * speed);
  }

  public void driveRight(double speed){
    m_rightMotor1.set(TalonSRXControlMode.PercentOutput, speed);
    m_rightMotor2.set(TalonSRXControlMode.PercentOutput, speed);
  }

  public void stopMotors(){
    driveLeft(0);
    driveRight(0);
  }

  @Override
  public void periodic() {

  }

  @Override
  public void simulationPeriodic() {
  }
}
