// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.shoulder;

public class ShoulderTeleop extends CommandBase {

	static double shoulderHeightSpeed = Constants.Predetermined.Shoulder.speed;
	String option;
	private shoulder m_shoulder;

	double time;
	Timer timer = new Timer();

	boolean reset;

	double autonomousExtension;

	public double getShoulderAngle() {
		return m_shoulder.getShoulderAngle();
	}

	public void reduceShoulderAngle(double amount) {
		m_shoulder.reduceShoulderAngle(amount);
	}

	public void increaseShoulderAngle(double amount) {
		m_shoulder.increaseShoulderAngle(amount);
	}

	/** Creates a new ShoulderTeleop. */
	public ShoulderTeleop(String Option, shoulder subsystem, double autonomousShoulderExtension, double m_time,
			boolean m_reset) {
		// Use addRequirements() here to declare subsystem dependencies.
		option = Option;
		m_shoulder = subsystem;
		autonomousExtension = autonomousShoulderExtension;

		reset = m_reset;

		time = m_time;

		addRequirements(m_shoulder);
	}

	// Called when the command is initially scheduled.1
	@Override
	public void initialize() {
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
				m_shoulder.setMotorSpeed(-Constants.Motors.Speeds.shoulder);
				break;

			// MANUAL UP
			case "Manual Up":
				if (getShoulderAngle() >= 270) {
					System.out.println("Arm angle is at its maximum");
					m_shoulder.setMotorSpeed(0);
					return;
				}
				increaseShoulderAngle(shoulderHeightSpeed);
				m_shoulder.setMotorSpeed(Constants.Motors.Speeds.shoulder);
				break;

			// LOW PRESET
			case "Low":
				if (getShoulderAngle() < Constants.Predetermined.Shoulder.Height.low) {
					m_shoulder.setMotorSpeed(Constants.Motors.Speeds.shoulder);
					increaseShoulderAngle(shoulderHeightSpeed);
				} else if (getShoulderAngle() > Constants.Predetermined.Shoulder.Height.low) {
					m_shoulder.setMotorSpeed(-Constants.Motors.Speeds.shoulder);
					reduceShoulderAngle(shoulderHeightSpeed);
				} else {
					m_shoulder.setMotorSpeed(0);
					System.out.println("Shoulder Height: LOW PRESET");
				}
				break;

			// MEDIUM PRESET
			case "Medium":
				if (getShoulderAngle() < Constants.Predetermined.Shoulder.Height.medium) {
					m_shoulder.setMotorSpeed(Constants.Motors.Speeds.shoulder);
					increaseShoulderAngle(shoulderHeightSpeed);
				} else if (getShoulderAngle() > Constants.Predetermined.Shoulder.Height.medium) {
					m_shoulder.setMotorSpeed(-Constants.Motors.Speeds.shoulder);
					reduceShoulderAngle(shoulderHeightSpeed);
				} else {

					m_shoulder.setMotorSpeed(0);
					System.out.println("Shoulder Height: MEDIUM PRESET");

				}
				break;

			// HIGH PRESET
			case "High":
				if (getShoulderAngle() < Constants.Predetermined.Shoulder.Height.high) {
					m_shoulder.setMotorSpeed(Constants.Motors.Speeds.shoulder);
					increaseShoulderAngle(shoulderHeightSpeed);
				} else if (getShoulderAngle() > Constants.Predetermined.Shoulder.Height.high) {
					m_shoulder.setMotorSpeed(-Constants.Motors.Speeds.shoulder);
					reduceShoulderAngle(shoulderHeightSpeed);
				} else {

					m_shoulder.setMotorSpeed(0);
					System.out.println("Shoulder Height: HIGH PRESET");

				}
				break;

			case "Autonomous":

				if (getShoulderAngle() < autonomousExtension) {
					Robot.m_shoulder.setMotorSpeed(Constants.Motors.Speeds.shoulder);
					increaseShoulderAngle(shoulderHeightSpeed);
				} else if (getShoulderAngle() > autonomousExtension) {
					Robot.m_shoulder.setMotorSpeed(-Constants.Motors.Speeds.shoulder);
					reduceShoulderAngle(shoulderHeightSpeed);
				} else {
					Robot.m_shoulder.setMotorSpeed(0);
					System.out.println("Shoulder Height: Autonomous");
				}

				if (reset && timer.hasElapsed(time) && getShoulderAngle() > 0) {

					Robot.m_shoulder.setMotorSpeed(-Constants.Motors.Speeds.shoulder);
					reduceShoulderAngle(shoulderHeightSpeed);
					System.out.println("Shoulder Height: " + getShoulderAngle());

				} else if (getShoulderAngle() == 0 || reset == false && timer.hasElapsed(time)) {
					Robot.m_shoulder.setMotorSpeed(0);

				}

				break;

			case "Default":

				if (getShoulderAngle() > 0) {
					Robot.m_shoulder.setMotorSpeed(-Constants.Motors.Speeds.shoulder);
					reduceShoulderAngle(shoulderHeightSpeed);
				} else {
					Robot.m_shoulder.setMotorSpeed(0);
					System.out.println("Shoulder Height: Reset");
				}

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
		if ((option == "Low" && getShoulderAngle() == Constants.Predetermined.Shoulder.Height.low) ||
				(option == "Medium" && getShoulderAngle() == Constants.Predetermined.Shoulder.Height.medium) ||
				(option == "High" && getShoulderAngle() == Constants.Predetermined.Shoulder.Height.high) ||
				(option == "Default" && getShoulderAngle() == 0)) {
			return true;
		}
		if (reset && option == "Autonomous" && timer.hasElapsed(time) && getShoulderAngle() > 0) {

			Robot.m_shoulder.setMotorSpeed(-Constants.Motors.Speeds.shoulder);
			reduceShoulderAngle(shoulderHeightSpeed);
			System.out.println("Shoulder Height: " + getShoulderAngle());

		} else if (getShoulderAngle() == 0 || reset == false && timer.hasElapsed(time)) {
			Robot.m_shoulder.setMotorSpeed(0);
			return true;
		}

		return false;

	}
}
