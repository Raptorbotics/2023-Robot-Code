// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.compressor;

public class compressorCommand extends CommandBase {
  /** Creates a new compressorCommand. */

  double time;
	Timer timer = new Timer();
  private compressor compressor;

  public boolean getCompressorState() { // Get the compressor state in a boolean (true or false)
    return compressor.state();
  }

  public void compressorOn() { // Turn compressor on
    compressor.compressorOn();
  }

  public void compressorOff() { // Turn compressor off
    compressor.compressorOff();
  }

  public compressorCommand(compressor compressorSubsystem, double m_time) { // get all the inputs needed in order to run the file
    // Use addRequirements() here to declare subsystem dependencies.
    time = m_time;
    compressor = compressorSubsystem;
    addRequirements(Robot.m_Compressor); // sets the compressor subsystem as a requirement
  }

  @Override
  public void initialize() { // When the command is first called, this runs
    timer.start();
  }

  @Override
  public void execute() { // When the command is ran, this function runs
    if(getCompressorState() == true) { // If compressor state is true, turn the compressor off
      compressorOff();
    } else if(getCompressorState() == false) {// If compressor state is false, turn the compressor on
      compressorOn();
    }
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() { // When the code is finished running, this function runs
    if (timer.hasElapsed(time)) {

		//Robot.m_Compressor.compressorOff();
    System.out.println("Compressor state is " + getCompressorState());
     return true;
    }

    System.out.println("Compressor state is " + getCompressorState());
    return true;
}
}