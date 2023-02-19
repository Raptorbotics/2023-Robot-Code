// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class ShoulderTeleop extends CommandBase {
  public String option;
  public static double armHeight = 0;
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
        if(armHeight <= 0) {
         System.out.println("Arm height is at its minimum");
          return;
      }
        armHeight = armHeight - 1;
        Robot.m_shoulder.setShoulderMotorSpeed(-Constants.motorSpeeds.shoulderMotorSpeed);
      break;
      case "Manual Up":
        if(armHeight >= 270) {
          System.out.println("Arm height is at its maximum");
          return;
      }
        armHeight = armHeight + 1;
        Robot.m_shoulder.setShoulderMotorSpeed(Constants.motorSpeeds.shoulderMotorSpeed);
      break;
      case "Low":
        if(armHeight < 90){
          Robot.m_shoulder.setShoulderMotorSpeed(Constants.motorSpeeds.shoulderMotorSpeed);
          armHeight = armHeight + 1;
      } else if(armHeight > 90) {
          Robot.m_shoulder.setShoulderMotorSpeed(-Constants.motorSpeeds.shoulderMotorSpeed);
          armHeight = armHeight - 1;
        }
        else{
          Robot.m_shoulder.setShoulderMotorSpeed(0);
          System.out.println("Arm height is already at low preset");
        }
      break;
      case "Medium":
        if(armHeight < 120){
          Robot.m_shoulder.setShoulderMotorSpeed(Constants.motorSpeeds.shoulderMotorSpeed);
          armHeight = armHeight + 1;
      } else if(armHeight > 120) {
          Robot.m_shoulder.setShoulderMotorSpeed(-Constants.motorSpeeds.shoulderMotorSpeed);
          armHeight = armHeight - 1;
        }
        else{
          Robot.m_shoulder.setShoulderMotorSpeed(0);
          System.out.println("Arm height is already at low preset");
        }
      break;
      case "High":
        if(armHeight < 250){
          Robot.m_shoulder.setShoulderMotorSpeed(Constants.motorSpeeds.shoulderMotorSpeed);
          armHeight = armHeight + 1;
      } else if(armHeight > 250) {
          Robot.m_shoulder.setShoulderMotorSpeed(-Constants.motorSpeeds.shoulderMotorSpeed);
          armHeight = armHeight - 1;
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

    System.out.println(armHeight);
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.m_shoulder.setShoulderMotorSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if((option == "Low" && armHeight == 90) || (option == "Medium" && armHeight == 120) ||(option == "High" && armHeight == 250) ) {
      return true;
    }
    return false;
  }
}
