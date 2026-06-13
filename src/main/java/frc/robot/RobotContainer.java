// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Util.RazerXboxWrapper;
import frc.robot.subsystems.DifferentialSubsystem;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  
  private final DifferentialSubsystem m_differentialSubsystem = new DifferentialSubsystem();
  private final RazerXboxWrapper driverController = new RazerXboxWrapper(0);

  public RobotContainer() {
    configureBindings();
  }

  
  private void configureBindings() {
    m_differentialSubsystem.setDefaultCommand(new RunCommand(
      () ->{
        m_differentialSubsystem.driveLeft(driverController.getLeftY());
        m_differentialSubsystem.driveRight(driverController.getRightY());
      }, m_differentialSubsystem));

    driverController.A.onTrue(Commands.runOnce(() -> m_differentialSubsystem.stopMotors()));
  }

}
