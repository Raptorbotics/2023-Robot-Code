// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.shoulder;
import frc.robot.subsystems.arm;

public class ShoulderTeleop extends CommandBase {

	static double shoulderHeightSpeed = Constants.Predetermined.Shoulder.speed;
	static double armExtensionSpeed = Constants.Predetermined.Arm.speed;
	String option;
	private shoulder m_shoulder;
	private arm m_arm;

	double time;
	Timer timer = new Timer();
	boolean resetBool;

	double tempLimiter = 0;
	double autonomousExtension;

	public double getShoulderAngle() {
		return m_shoulder.getShoulderAngle();
	}

	public void reduceShoulderAngle(double amount) {
		m_shoulder.reduceShoulderAngle(amount);
		m_shoulder.setMotorSpeed(-Constants.Motors.Speeds.shoulder);
	}

	public void increaseShoulderAngle(double amount) {
		m_shoulder.increaseShoulderAngle(amount);
		m_shoulder.setMotorSpeed(Constants.Motors.Speeds.shoulder);
	}

	public double getArmLength() {
		return m_arm.getArmLength();
	}

	public void reduceArmLength(double amount) {
		m_arm.reduceArmLength(amount);
		m_arm.setMotorSpeed(-Constants.Motors.Speeds.arm);
	}

	/** Creates a new ShoulderTeleop. */
	public ShoulderTeleop(String Option, shoulder shoulderSubsystem, double autonomousShoulderExtension, double m_time, boolean reset, arm armSubsystem) {
		// Use addRequirements() here to declare subsystem dependencies.
		addRequirements(Robot.m_shoulder);
		option = Option;

		autonomousExtension = autonomousShoulderExtension;
		m_shoulder = shoulderSubsystem;
		m_arm = armSubsystem;

		time = m_time;
		resetBool = reset;

	}

	// Called when the command is initially scheduled.1
	@Override
	public void initialize() {
		timer.start();
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {

		double rightTrigger = (Robot.m_robotContainer.GetDriverRawAxis(Constants.Controller.Joystick.m_rightTrigger)) ;
		switch (option) {

			case "Teleop":
			if ((Math.abs(rightTrigger) > 0.1)){
				if(tempLimiter < 1) {
					tempLimiter += Constants.Predetermined.Drive.exponentialIncrease;
				}
				Robot.m_shoulder.setMotorSpeed(rightTrigger);
			} else {
				tempLimiter = 0;
				Robot.m_shoulder.setMotorSpeed(0);
			}
				break;
			case "Default":
				if (getShoulderAngle() > 0) {
					if(getArmLength() > 0) {
						reduceArmLength(armExtensionSpeed);
						System.out.println("Arm Extension: " + getArmLength());
						return;
					} else {
						m_arm.setMotorSpeed(0);
					}
					reduceShoulderAngle(shoulderHeightSpeed);
				} else {
					m_shoulder.setMotorSpeed(0);
					System.out.println("Shoulder Height: Reset");
				}
				break;
			 case "Autonomous":

				if (getShoulderAngle() < autonomousExtension) {
					increaseShoulderAngle(shoulderHeightSpeed);
				} else if (getShoulderAngle() > autonomousExtension) {
					reduceShoulderAngle(shoulderHeightSpeed);
				} else {
					m_shoulder.setMotorSpeed(0);
					System.out.println("Shoulder Height: Autonomous");
				}

				if (timer.hasElapsed(time) && getShoulderAngle() > 0) {
					reduceShoulderAngle(shoulderHeightSpeed);

				} else if (getShoulderAngle() == 0 || timer.hasElapsed(time)) {
					m_shoulder.setMotorSpeed(0);

				}

				break; 

			default:
				m_shoulder.setMotorSpeed(0);
				break;
		}

		System.out.println("Shoulder Angle: " + getShoulderAngle());
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) { // When the keybind is let go, the motor will be turned off
		Robot.m_shoulder.setMotorSpeed(Constants.Motors.Speeds.shoulderStop);
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		if (
			(option == "Low" && getShoulderAngle() == Constants.Predetermined.Shoulder.Height.low) ||
			(option == "Medium" && getShoulderAngle() == Constants.Predetermined.Shoulder.Height.medium) ||
			(option == "High" && getShoulderAngle() == Constants.Predetermined.Shoulder.Height.high) ||
			(option == "Default" && getShoulderAngle() == 0)
		) {
			return true;
		}
		 if (option == "Autonomous" && timer.hasElapsed(time) && getShoulderAngle() > 0) {
			if(resetBool == true) {
			reduceShoulderAngle(shoulderHeightSpeed);
			} else if (resetBool == false) {
				return true;
			}

		} else if (option == "Autonomous" && getShoulderAngle() == 0 && timer.hasElapsed(time)) {
			m_shoulder.setMotorSpeed(0);
			return true;
		}
 
		return false;
	}
}