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

	boolean reset;

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
	}

	/** Creates a new ShoulderTeleop. */
	public ShoulderTeleop(String Option, shoulder shoulderSubsystem, double autonomousShoulderExtension, double m_time, boolean m_reset, arm armSubsystem) {
		// Use addRequirements() here to declare subsystem dependencies.
		addRequirements(Robot.m_shoulder);
		option = Option;

		autonomousExtension = autonomousShoulderExtension;
		m_shoulder = shoulderSubsystem;
		m_arm = armSubsystem;

		time = m_time;
		reset = m_reset;
	}

	// Called when the command is initially scheduled.1
	@Override
	public void initialize() {
		timer.start();
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		switch (option) {
			// MANUAL DOWN
			case "Manual Down":
				if (getShoulderAngle() <= 0) {
					System.out.println("Shoulder Height: MINIMUM");
					m_shoulder.setMotorSpeed(0);

					return;
				}
				reduceShoulderAngle(shoulderHeightSpeed);
				break;
			// MANUAL UP
			case "Manual Up":
				if (getShoulderAngle() >= 270) {
					System.out.println("Shoulder Height: MAXIMUM");
					m_shoulder.setMotorSpeed(0);
					return;
				}
				increaseShoulderAngle(shoulderHeightSpeed);
				break;
			// LOW PRESET
			case "Low":
				if (getShoulderAngle() < Constants.Predetermined.Shoulder.Height.low) {
					increaseShoulderAngle(shoulderHeightSpeed);
				} else if (getShoulderAngle() > Constants.Predetermined.Shoulder.Height.low) {
					reduceShoulderAngle(shoulderHeightSpeed);
				} else {
					m_shoulder.setMotorSpeed(0);
					System.out.println("Shoulder Height: LOW PRESET");
				}
				break;
			// MEDIUM PRESET
			case "Medium":
				if (getShoulderAngle() < Constants.Predetermined.Shoulder.Height.medium) {
					increaseShoulderAngle(shoulderHeightSpeed);
				} else if (getShoulderAngle() > Constants.Predetermined.Shoulder.Height.medium) {
					reduceShoulderAngle(shoulderHeightSpeed);
				} else {
					m_shoulder.setMotorSpeed(0);
					System.out.println("Shoulder Height: MEDIUM PRESET");
				}
				break;
			// HIGH PRESET
			case "High":
				if (getShoulderAngle() < Constants.Predetermined.Shoulder.Height.high) {
					increaseShoulderAngle(shoulderHeightSpeed);
				} else if (getShoulderAngle() > Constants.Predetermined.Shoulder.Height.high) {
					reduceShoulderAngle(shoulderHeightSpeed);
				} else {
					m_shoulder.setMotorSpeed(0);
					System.out.println("Shoulder Height: HIGH PRESET");
				}
				break;
			case "Default":
				if(getArmLength() > 0) {
					reduceArmLength(armExtensionSpeed);
				} else if (getShoulderAngle() > 0) {
					reduceShoulderAngle(shoulderHeightSpeed);
				} else {
					m_shoulder.setMotorSpeed(0);
					System.out.println("Shoulder Height: Reset");
				}
				break;
			/* case "Autonomous":

				if (getShoulderAngle() < autonomousExtension) {
					increaseShoulderAngle(shoulderHeightSpeed);
				} else if (getShoulderAngle() > autonomousExtension) {
					reduceShoulderAngle(shoulderHeightSpeed);
				} else {
					m_shoulder.setMotorSpeed(0);
					System.out.println("Shoulder Height: Autonomous");
				}

				if (reset && timer.hasElapsed(time) && getShoulderAngle() > 0) {
					reduceShoulderAngle(shoulderHeightSpeed);
					System.out.println("Shoulder Height: " + getShoulderAngle());

				} else if (getShoulderAngle() == 0 || reset == false && timer.hasElapsed(time)) {
					m_shoulder.setMotorSpeed(0);

				}

				break; */

			default:
				m_shoulder.setMotorSpeed(0);
				break;
		}

		System.out.println("Shoulder Angle: " + getShoulderAngle());
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		m_shoulder.setMotorSpeed(0);
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
		/* if (reset && option == "Autonomous" && timer.hasElapsed(time) && getShoulderAngle() > 0) {
			reduceShoulderAngle(shoulderHeightSpeed);
			System.out.println("Shoulder Height: " + getShoulderAngle());

		} else if (getShoulderAngle() == 0 || reset == false && timer.hasElapsed(time)) {
			m_shoulder.setMotorSpeed(0);
			return true;
		}
 */
		return false;
	}
}
