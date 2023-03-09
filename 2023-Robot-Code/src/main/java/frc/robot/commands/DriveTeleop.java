// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
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

	/** Creates a new Drive. */
	public DriveTeleop(String Option, double m_xInput, double m_yInput, double m_zInput, double m_time) {
		// Use addRequirements() here to declare subsystem dependencies.

		option = Option;

		yInput = m_yInput;
		zInput = m_zInput;

		time = m_time;

		addRequirements(Robot.m_Drivetrain);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		m_timer.start();
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		double leftStickX = (Robot.m_robotContainer.GetDriverRawAxis(Constants.Controller.Joystick.m_leftStickX)) * (Constants.m_limiter);
		double leftStickY = (Robot.m_robotContainer.GetDriverRawAxis(Constants.Controller.Joystick.m_leftStickY)) * (Constants.m_limiter);
		double rightStickX = (Robot.m_robotContainer.GetDriverRawAxis(Constants.Controller.Joystick.m_rightStickX)) * (Constants.m_limiter);

		switch (option) {
			case "Teleop":
				if(tempLimiter < 1) {
					tempLimiter += .5;
				}
				Robot.m_Drivetrain.setMotorSpeed(leftStickX * tempLimiter, leftStickY * tempLimiter, rightStickX * tempLimiter, 0);
				//Robot.m_Drivetrain.setMotorSpeed(leftStickX, leftStickY, rightStickX, 0);
				break;
			case "Autonomous":
				if (m_timer.get() < time) {
					Robot.m_Drivetrain.setMotorSpeed(xInput, yInput, zInput, 0);
				}

				break;
			default:
				Robot.m_Drivetrain.setMotorSpeed(0, 0, 0, 0);
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
