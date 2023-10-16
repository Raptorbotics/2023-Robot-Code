// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.extender;

public class extenderTeleop extends CommandBase {
  private extender motor;
  private String option;
  /** Creates a new extenderTeleop. */
  public extenderTeleop(extender subsystem, String Option) {
    // Use addRequirements() here to declare subsystem dependencies.
    motor = subsystem;
    option = Option;
    addRequirements(motor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(option == "extend") {
      motor.extend();
    } else if(option == "retract") {
      motor.retract();
    } else {
      motor.stop();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    motor.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
