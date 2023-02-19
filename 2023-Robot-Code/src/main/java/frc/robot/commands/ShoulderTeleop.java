// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class ShoulderTeleop extends CommandBase {
  public String option;
  public static double shoulderHeight = 0;
  double shoulderHeightSpeed = Constants.m_shoulderHeightSpeed;
  /** Creates a new ShoulderTeleop. */
  public ShoulderTeleop(String Option) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.m_shoulder);
    option = Option;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  
    switch (option) {
      case "Manual Down":
        if(shoulderHeight <= 0) {
         System.out.println("Arm height is at its minimum");
          return;
      }
      shoulderHeight = shoulderHeight - shoulderHeightSpeed;
        Robot.m_shoulder.setShoulderMotorSpeed(-Constants.motorSpeeds.shoulderMotorSpeed);
      break;
      case "Manual Up":
        if(shoulderHeight >= 270) {
          System.out.println("Arm height is at its maximum");
          return;
      }
      shoulderHeight = shoulderHeight + shoulderHeightSpeed;
        Robot.m_shoulder.setShoulderMotorSpeed(Constants.motorSpeeds.shoulderMotorSpeed);
      break;
      case "Low":
        if(shoulderHeight < 90){
          Robot.m_shoulder.setShoulderMotorSpeed(Constants.motorSpeeds.shoulderMotorSpeed);
          shoulderHeight = shoulderHeight + shoulderHeightSpeed;
      } else if(shoulderHeight > 90) {
          Robot.m_shoulder.setShoulderMotorSpeed(-Constants.motorSpeeds.shoulderMotorSpeed);
          shoulderHeight = shoulderHeight - shoulderHeightSpeed;
        }
        else{
          Robot.m_shoulder.setShoulderMotorSpeed(0);
          System.out.println("Arm height is already at low preset");
        }
      break;
      case "Medium":
        if(shoulderHeight < 120){
          Robot.m_shoulder.setShoulderMotorSpeed(Constants.motorSpeeds.shoulderMotorSpeed);
          shoulderHeight = shoulderHeight + shoulderHeightSpeed;
      } else if(shoulderHeight > 120) {
          Robot.m_shoulder.setShoulderMotorSpeed(-Constants.motorSpeeds.shoulderMotorSpeed);
          shoulderHeight = shoulderHeight - shoulderHeightSpeed;
        }
        else{
          Robot.m_shoulder.setShoulderMotorSpeed(0);
          System.out.println("Arm height is already at low preset");
        }
      break;
      case "High":
        if(shoulderHeight < 250){
          Robot.m_shoulder.setShoulderMotorSpeed(Constants.motorSpeeds.shoulderMotorSpeed);
          shoulderHeight = shoulderHeight + shoulderHeightSpeed;
      } else if(shoulderHeight > 250) {
          Robot.m_shoulder.setShoulderMotorSpeed(-Constants.motorSpeeds.shoulderMotorSpeed);
          shoulderHeight = shoulderHeight - shoulderHeightSpeed;
        }
        else{
          Robot.m_shoulder.setShoulderMotorSpeed(0);
          System.out.println("Arm height is already at low preset");
        }
      break;

      default:
        Robot.m_shoulder.setShoulderMotorSpeed(0);
      break;
     
    }

    System.out.println(shoulderHeight);
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.m_shoulder.setShoulderMotorSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if((option == "Low" && shoulderHeight == 90) || (option == "Medium" && shoulderHeight == 120) ||(option == "High" && shoulderHeight == 250) ) {
      return true;
    }
    return false;
  }
}
