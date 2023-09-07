// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.PowerDistribution;
import frc.robot.Constants;
import frc.robot.Robot;

public class DriveTeleop extends CommandBase {

	String option;
	double xInput;
	double yInput;
	double zInput;
	double time;
	double tempLimiter = 0;
	

	Timer m_timer = new Timer();

	//static double exponentialIncrease = .7;

	Timer m_teleopTimer = new Timer();
	  
	

	/** Creates a new Drive. */
	public DriveTeleop(String Option, double m_xInput, double m_yInput, double m_zInput, double m_time) { // Call all inputs needed to run the file
		// Use addRequirements() here to declare subsystem dependencies.

		option = Option;

		yInput = m_yInput;
		zInput = m_zInput;

		time = m_time;

		addRequirements(Robot.m_Drivetrain); // Set the drivetrain subsystem as a requirement to run this file
	}

	@Override
	public void initialize() { // When the command is first called, this function runs
		m_timer.start();
	}

	@Override // When the command is called, this function runs
	public void execute() {
		double leftStickX = (Robot.m_robotContainer.GetDriverRawAxis(Constants.Controller.Joystick.m_leftStickX)) ;
		double leftStickY = (Robot.m_robotContainer.GetDriverRawAxis(Constants.Controller.Joystick.m_leftStickY)) ;
		double rightStickX = (Robot.m_robotContainer.GetDriverRawAxis(Constants.Controller.Joystick.m_rightStickX)) ;


		//System.out.println(bonk.getTotalCurrent());
		//leftStickX = exponentialChange(leftStickX);
		switch (option) {
			case "Teleop":
					System.out.println(Robot.m_Drivetrain.getTalonPosition());
				
					Robot.m_Drivetrain.setMotorSpeed(leftStickX , leftStickY , rightStickX , 0);
					Robot.m_Drivetrain.setTalonSpeed(leftStickY);
					if(leftStickX >.1 || leftStickY > .1 || rightStickX > .1){
					Robot.m_Drivetrain.MotorTest();
					}
				
				//System.out.println(tempLimiter);

				//Robot.m_Drivetrain.setMotorSpeed(leftStickX, leftStickY, rightStickX, 0);
				break;
			case "Autonomous":
				if (m_timer.get() < time) {
					Robot.m_Drivetrain.setMotorSpeed(xInput, yInput, zInput, 0);
				}

				break;
			default:
				Robot.m_Drivetrain.setMotorSpeed(0, 0, 0, 0);
				Robot.m_Drivetrain.setTalonSpeed(0);
		}
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		tempLimiter = 0;
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		if (option == "Autonomous") {
			if (m_timer.hasElapsed(time)) {
				Robot.m_Drivetrain.setMotorSpeed(0, 0, 0, 0);
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
}
