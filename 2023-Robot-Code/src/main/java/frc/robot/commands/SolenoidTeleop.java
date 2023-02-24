// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.solenoid;

public class SolenoidTeleop extends CommandBase {
 boolean solenoidState;
 solenoid solenoidSystem;
 boolean commandDone;
  /** Creates a new SolenoidTeleop. */
  public SolenoidTeleop(solenoid solenoidSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.

    solenoidSystem = solenoidSubsystem;
    solenoidState = solenoidSystem.getSolenoidState();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    commandDone = false;
    if(solenoidState == false) {
      solenoidSystem.setSolenoidStateTrue();
      commandDone = true;
    } else if (solenoidState == true) {
      solenoidSystem.setSolenoidStateFalse();
      commandDone = true;
    } else {
      System.out.println("There was a problem with the solenoidState");
      commandDone = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(commandDone == true) {
      return true;
    }
    return false;
  }
}
