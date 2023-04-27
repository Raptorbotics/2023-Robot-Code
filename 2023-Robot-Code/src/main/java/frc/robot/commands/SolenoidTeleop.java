// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.solenoid;

public class SolenoidTeleop extends CommandBase {
  /** Creates a new SolenoidTeleop. */
  solenoid solenoidSystem;
  public SolenoidTeleop(solenoid solenoidSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.solenoidObject);

    solenoidSystem = solenoidSubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(solenoidSystem.getClawState() == false) {
      solenoidSystem.openClaw();
      return;
    } else if (solenoidSystem.getClawState() == true) {
      solenoidSystem.closeClaw();
      return;
    } else {
      System.out.println("There was a problem with the claw");
      return;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
