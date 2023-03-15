// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class compressorCommand extends CommandBase {
  /** Creates a new compressorCommand. */

  double time;
	Timer timer = new Timer();
  public compressorCommand(double m_time) {
    // Use addRequirements() here to declare subsystem dependencies.
    time = m_time;
    addRequirements(Robot.m_Compressor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.m_Compressor.compressorOn();
    /*
    if (timer.hasElapsed(time) ) {
     Robot.m_Compressor.compressorOff();

    } else if (Timer.getFPGATimestamp() < time) {
      Robot.m_Compressor.compressorOn();
    }
    */
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //Robot.m_Compressor.compressorOff();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (timer.hasElapsed(time)) {

		//Robot.m_Compressor.compressorOff();
     return true;
  }

  return false;
}
}