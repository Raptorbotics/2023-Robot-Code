// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.arm;

public class ArmTeleop extends CommandBase {

	static double armExtensionSpeed = Constants.Predetermined.Arm.speed;
	String option;
	private arm m_arm;

	double time;
	Timer timer = new Timer();

	double autonomousExtension;

	public double getArmLength() {
		return m_arm.getArmLength();
	}

	public void reduceArmLength(double amount) {
		m_arm.reduceArmLength(amount);
		Robot.m_arm.setMotorSpeed(-Constants.Motors.Speeds.arm);
	}

	public void increaseArmLength(double amount) {
		m_arm.increaseArmLength(amount);
		Robot.m_arm.setMotorSpeed(Constants.Motors.Speeds.arm);
	}

	public ArmTeleop(String Option, arm armSubsystem, double autonomousArmExtension, double m_time) {
		addRequirements(Robot.m_arm);

		option = Option;
		autonomousExtension = autonomousArmExtension;
		m_arm = armSubsystem;

		time = m_time;

	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		timer.start();
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		switch (option) {
			case "Manual Retract":
				if (getArmLength() <= 0) {
					System.out.println("Arm Extension: MINIMUM");

					m_arm.setMotorSpeed(0);
					return;
				}
				reduceArmLength(armExtensionSpeed);
				break;
			case "Manual Extend":
				if (getArmLength() >= 270) {
					System.out.println("Arm Extension: MAXIMUM");
					m_arm.setMotorSpeed(0);
					return;
				}
				increaseArmLength(armExtensionSpeed);
				break;
			// Low Preset
			case "Low":
				if (getArmLength() < Constants.Predetermined.Arm.Extension.low) {
					increaseArmLength(armExtensionSpeed);
				} else if (getArmLength() > Constants.Predetermined.Arm.Extension.low) {
					reduceArmLength(armExtensionSpeed);
				} else {
					m_arm.setMotorSpeed(0);
					System.out.println("Arm Extension: LOW PRESET");
				}
				break;
			// Medium Preset
			case "Medium":
				if (getArmLength() < Constants.Predetermined.Arm.Extension.medium) {
					increaseArmLength(armExtensionSpeed);
				} else if (getArmLength() > Constants.Predetermined.Arm.Extension.medium) {
					reduceArmLength(armExtensionSpeed);
				} else {
					m_arm.setMotorSpeed(0);
					System.out.println("Arm Extension: MEDIUM PRESET");
				}
				break;
			// High Preset
			case "High":
				if (getArmLength() < Constants.Predetermined.Arm.Extension.high) {
					increaseArmLength(armExtensionSpeed);
				} else if (getArmLength() > Constants.Predetermined.Arm.Extension.high) {
					reduceArmLength(armExtensionSpeed);
				} else {
					m_arm.setMotorSpeed(0);
					System.out.println("Arm Extension: HIGH PRESET");
				}
				break;
			case "Default":
				if (getArmLength() > 0) {
					reduceArmLength(armExtensionSpeed);
				} else {
					m_arm.setMotorSpeed(0);
					System.out.println("Arm Exension: Reset");
				}
			 case "Autonomous":

				if (getArmLength() < autonomousExtension) {
					increaseArmLength(armExtensionSpeed);
				} else if (getArmLength() > autonomousExtension) {
					reduceArmLength(armExtensionSpeed);
				} else {
					m_arm.setMotorSpeed(0);
					System.out.println("Arm Extension: Autonomous");
				}

				if (timer.hasElapsed(time) && getArmLength() > 0) {
					reduceArmLength(armExtensionSpeed);
					System.out.println("Arm Extension: " + getArmLength());

				} else if (getArmLength() == 0 || timer.hasElapsed(time)) {
					m_arm.setMotorSpeed(0);

				}
				break;

			default:
				m_arm.setMotorSpeed(0);
				break;
		}

		System.out.println("Arm Extension: " + getArmLength());
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		m_arm.setMotorSpeed(0);
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		if (
			(option == "Low" && getArmLength() == Constants.Predetermined.Arm.Extension.low) ||
			(option == "Medium" && getArmLength() == Constants.Predetermined.Arm.Extension.medium) ||
			(option == "High" && getArmLength() == Constants.Predetermined.Arm.Extension.high) ||
			(option == "Default" && getArmLength() == 0)  ||
			 (option == "Autonomous" && getArmLength() == autonomousExtension)
		) {
			return true;
		}
		 	if (option == "Autonomous" && timer.hasElapsed(time) && getArmLength() > 0) {

			reduceArmLength(armExtensionSpeed);
			System.out.println("Arm Extension: " + getArmLength());

		} else if (getArmLength() == 0 || timer.hasElapsed(time)) {
			m_arm.setMotorSpeed(0);
			return true;
		} 

		return false;
	}
}
