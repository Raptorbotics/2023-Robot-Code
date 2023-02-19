// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class ShoulderTeleop extends CommandBase {

	double shoulderHeightSpeed = Constants.Predetermined.shoulder.m_shoulderHeightSpeed;
	String option;
	public double getShoulderAngle() {
		return Robot.m_shoulder.getShoulderAngle();
	}

	public void reduceShoulderAngle(double amount) {
		Robot.m_shoulder.reduceShoulderAngle(amount);
	}

	public void increaseShoulderAngle(double amount) {
		Robot.m_shoulder.increaseShoulderAngle(amount);
	}

	/** Creates a new ShoulderTeleop. */
	public ShoulderTeleop(String Option) {
		// Use addRequirements() here to declare subsystem dependencies.
		addRequirements(Robot.m_shoulder);
option = Option;
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		Robot.m_shoulder.setShoulderAngle(Constants.Predetermined.shoulder.initialShoulderAngle);
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		switch (option) {
			case "Manual Down":
				if (getShoulderAngle() <= 0) {
					System.out.println("Arm height is at its minimum");
					return;
				}
				reduceShoulderAngle(shoulderHeightSpeed);
				Robot.m_shoulder.setShoulderMotorSpeed(-Constants.motorSpeeds.shoulderMotorSpeed);
				break;
			case "Manual Up":
				if (getShoulderAngle() >= 270) {
					System.out.println("Arm height is at its maximum");
					return;
				}
				increaseShoulderAngle(shoulderHeightSpeed);
				Robot.m_shoulder.setShoulderMotorSpeed(Constants.motorSpeeds.shoulderMotorSpeed);
				break;
			case "Low":
				if (getShoulderAngle() < 90) {
					Robot.m_shoulder.setShoulderMotorSpeed(Constants.motorSpeeds.shoulderMotorSpeed);
					increaseShoulderAngle(shoulderHeightSpeed);
				} else if (getShoulderAngle() > 90) {
					Robot.m_shoulder.setShoulderMotorSpeed(-Constants.motorSpeeds.shoulderMotorSpeed);
					reduceShoulderAngle(shoulderHeightSpeed);
				} else {
					Robot.m_shoulder.setShoulderMotorSpeed(0);
					System.out.println("Arm height is already at low preset");
				}
				break;
			case "Medium":
				if (getShoulderAngle() < 120) {
					Robot.m_shoulder.setShoulderMotorSpeed(Constants.motorSpeeds.shoulderMotorSpeed);
					increaseShoulderAngle(shoulderHeightSpeed);
				} else if (getShoulderAngle() > 120) {
					Robot.m_shoulder.setShoulderMotorSpeed(-Constants.motorSpeeds.shoulderMotorSpeed);
					reduceShoulderAngle(shoulderHeightSpeed);
				} else {
					Robot.m_shoulder.setShoulderMotorSpeed(0);
					System.out.println("Arm height is already at low preset");
				}
				break;
			case "High":
				if (getShoulderAngle() < 250) {
					Robot.m_shoulder.setShoulderMotorSpeed(Constants.motorSpeeds.shoulderMotorSpeed);
					increaseShoulderAngle(shoulderHeightSpeed);
				} else if (getShoulderAngle() > 250) {
					Robot.m_shoulder.setShoulderMotorSpeed(-Constants.motorSpeeds.shoulderMotorSpeed);
					reduceShoulderAngle(shoulderHeightSpeed);
				} else {
					Robot.m_shoulder.setShoulderMotorSpeed(0);
					System.out.println("Arm height is already at low preset");
				}
				break;
			default:
				Robot.m_shoulder.setShoulderMotorSpeed(0);
				break;
		}

		System.out.println(getShoulderAngle());
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		Robot.m_shoulder.setShoulderMotorSpeed(0);
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
