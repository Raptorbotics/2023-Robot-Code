// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.shoulder;

public class ShoulderTeleop extends CommandBase {

	static double  shoulderHeightSpeed = Constants.Predetermined.Shoulder.Speed;
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

			//MANUAL DOWN
			case "Manual Down":
				if (getShoulderAngle() <= 0) {
<<<<<<< HEAD
					System.out.println("Shoulder Height: MINIMUM");
					m_shoulder.setMotorSpeed(0);
=======
					System.out.println("Arm angle is at its minimum");
					m_shoulder.setShoulderMotorSpeed(0);
>>>>>>> 29cb824c379a63e70c363b74f34e263826973574
					return;
				}
				reduceShoulderAngle(shoulderHeightSpeed);
				m_shoulder.setMotorSpeed(-Constants.Motors.Speeds.shoulder);
				break;

			//MANUAL UP
			case "Manual Up":
				if (getShoulderAngle() >= 270) {
<<<<<<< HEAD
					System.out.println("Shoulder Height: MAXIMUM");
					m_shoulder.setMotorSpeed(0);
=======
					System.out.println("Arm angle is at its maximum");
					m_shoulder.setShoulderMotorSpeed(0);
>>>>>>> 29cb824c379a63e70c363b74f34e263826973574
					return;
				}
				increaseShoulderAngle(shoulderHeightSpeed);
				m_shoulder.setMotorSpeed(Constants.Motors.Speeds.shoulder);
				break;

			//LOW PRESET
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

			//MEDIUM PRESET
			case "Medium":
				if (getShoulderAngle() < Constants.Predetermined.Shoulder.Height.medium) {
					m_shoulder.setMotorSpeed(Constants.Motors.Speeds.shoulder);
					increaseShoulderAngle(shoulderHeightSpeed);
				} else if (getShoulderAngle() > Constants.Predetermined.Shoulder.Height.medium) {
					m_shoulder.setMotorSpeed(-Constants.Motors.Speeds.shoulder);
					reduceShoulderAngle(shoulderHeightSpeed);
				} else {
<<<<<<< HEAD
					m_shoulder.setMotorSpeed(0);
					System.out.println("Shoulder Height: MEDIUM PRESET");
=======
					m_shoulder.setShoulderMotorSpeed(0);
					System.out.println("Arm height is already at medium preset");
>>>>>>> 29cb824c379a63e70c363b74f34e263826973574
				}
				break;

			//HIGH PRESET
			case "High":
				if (getShoulderAngle() < Constants.Predetermined.Shoulder.Height.high) {
					m_shoulder.setMotorSpeed(Constants.Motors.Speeds.shoulder);
					increaseShoulderAngle(shoulderHeightSpeed);
				} else if (getShoulderAngle() > Constants.Predetermined.Shoulder.Height.high) {
					m_shoulder.setMotorSpeed(-Constants.Motors.Speeds.shoulder);
					reduceShoulderAngle(shoulderHeightSpeed);
				} else {
<<<<<<< HEAD
					m_shoulder.setMotorSpeed(0);
					System.out.println("Shoulder Height: HIGH PRESET");
=======
					m_shoulder.setShoulderMotorSpeed(0);
					System.out.println("Arm height is already at high preset");
>>>>>>> 29cb824c379a63e70c363b74f34e263826973574
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
	public void end(boolean interrupted) {
		m_shoulder.setMotorSpeed(0);
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		if (
			(option == "Low" && getShoulderAngle() == Constants.Predetermined.Shoulder.Height.low) ||
			(option == "Medium" && getShoulderAngle() == Constants.Predetermined.Shoulder.Height.medium) ||
			(option == "High" && getShoulderAngle() == Constants.Predetermined.Shoulder.Height.high)
		) {
			return true;
		}
		return false;
	}
}
