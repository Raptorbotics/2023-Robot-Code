// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.intake;
import java.util.concurrent.TimeUnit;

public class intakeTeleop extends CommandBase {
  private intake intakeSystem;
  private String option;
  /** Creates a new intakeTeleop. */
  public intakeTeleop(intake subsystem, String Option) {
    intakeSystem = subsystem;
    option = Option;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intakeSystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    intakeSystem.start();
    if(option == "toggle") {
      intakeSystem.changeToggle();
    } else if(option == "stop") {
      intakeSystem.stop();
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