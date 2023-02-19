// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.shoulder;

public class ShoulderTeleop extends CommandBase {

	static double  shoulderHeightSpeed = Constants.Predetermined.Shoulder.shoulderHeightSpeed;
	String option;
	private shoulder m_shoulder;

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
	public ShoulderTeleop(String Option, shoulder subsystem) {
		// Use addRequirements() here to declare subsystem dependencies.
		option = Option;
		m_shoulder = subsystem;
		addRequirements(m_shoulder);
	}

	// Called when the command is initially scheduled.1
	@Override
	public void initialize() {}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		switch (option) {
			case "Manual Down":
				if (getShoulderAngle() <= 0) {
					System.out.println("Arm height is at its minimum");
					m_shoulder.setShoulderMotorSpeed(0);
					return;
				}
				reduceShoulderAngle(shoulderHeightSpeed);
				m_shoulder.setShoulderMotorSpeed(-Constants.Motors.Speeds.shoulder);
				break;
			case "Manual Up":
				if (getShoulderAngle() >= 270) {
					System.out.println("Arm height is at its maximum");
					m_shoulder.setShoulderMotorSpeed(0);
					return;
				}
				increaseShoulderAngle(shoulderHeightSpeed);
				m_shoulder.setShoulderMotorSpeed(Constants.Motors.Speeds.shoulder);
				break;
			case "Low":
				if (getShoulderAngle() < 90) {
					m_shoulder.setShoulderMotorSpeed(Constants.Motors.Speeds.shoulder);
					increaseShoulderAngle(shoulderHeightSpeed);
				} else if (getShoulderAngle() > 90) {
					m_shoulder.setShoulderMotorSpeed(-Constants.Motors.Speeds.shoulder);
					reduceShoulderAngle(shoulderHeightSpeed);
				} else {
					m_shoulder.setShoulderMotorSpeed(0);
					System.out.println("Arm height is already at low preset");
				}
				break;
			case "Medium":
				if (getShoulderAngle() < 120) {
					m_shoulder.setShoulderMotorSpeed(Constants.Motors.Speeds.shoulder);
					increaseShoulderAngle(shoulderHeightSpeed);
				} else if (getShoulderAngle() > 120) {
					m_shoulder.setShoulderMotorSpeed(-Constants.Motors.Speeds.shoulder);
					reduceShoulderAngle(shoulderHeightSpeed);
				} else {
					m_shoulder.setShoulderMotorSpeed(0);
					System.out.println("Arm height is already at low preset");
				}
				break;
			case "High":
				if (getShoulderAngle() < 250) {
					m_shoulder.setShoulderMotorSpeed(Constants.Motors.Speeds.shoulder);
					increaseShoulderAngle(shoulderHeightSpeed);
				} else if (getShoulderAngle() > 250) {
					m_shoulder.setShoulderMotorSpeed(-Constants.Motors.Speeds.shoulder);
					reduceShoulderAngle(shoulderHeightSpeed);
				} else {
					m_shoulder.setShoulderMotorSpeed(0);
					System.out.println("Arm height is already at low preset");
				}
				break;
			default:
				m_shoulder.setShoulderMotorSpeed(0);
				break;
		}

		System.out.println(getShoulderAngle());
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		m_shoulder.setShoulderMotorSpeed(0);
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		if (
			(option == "Low" && getShoulderAngle() == 90) ||
			(option == "Medium" && getShoulderAngle() == 120) ||
			(option == "High" && getShoulderAngle() == 250)
		) {
			return true;
		}
		return false;
	}
}
