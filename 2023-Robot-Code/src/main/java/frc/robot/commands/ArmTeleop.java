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

	boolean reset;
	double autonomousExtension;

	public double getArmAngle() {
		return m_arm.getArmAngle();
	}

	public void reduceArmAngle(double amount) {
		m_arm.reduceArmAngle(amount);
	}

	public void increaseArmAngle(double amount) {
		m_arm.increaseArmAngle(amount);
	}

	public ArmTeleop(String Option, arm subsystem, double autonomousArmExtension, 
	double m_time, boolean m_reset) {
		addRequirements(Robot.m_arm);

		option = Option;
		autonomousExtension = autonomousArmExtension;
		m_arm = subsystem;

		time = m_time;

		reset = m_reset;

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
				if (getArmAngle() <= 0) {
					System.out.println("Arm Extension: MINIMUM");

					Robot.m_arm.setMotorSpeed(0);
					return;
				}
				reduceArmAngle(armExtensionSpeed);
				Robot.m_arm.setMotorSpeed(-Constants.Motors.Speeds.arm);
				break;
			case "Manual Extend":
				if (getArmAngle() >= 270) {
					System.out.println("Arm Extension: MAXIMUM");
					Robot.m_arm.setMotorSpeed(0);
					return;
				}
				increaseArmAngle(armExtensionSpeed);
				Robot.m_arm.setMotorSpeed(Constants.Motors.Speeds.arm);
				break;

			// Low Preset
			case "Low":
				if (getArmAngle() < Constants.Predetermined.Arm.Extension.low) {
					Robot.m_arm.setMotorSpeed(Constants.Motors.Speeds.arm);
					increaseArmAngle(armExtensionSpeed);
				} else if (getArmAngle() > Constants.Predetermined.Arm.Extension.low) {
					Robot.m_arm.setMotorSpeed(-Constants.Motors.Speeds.arm);
					reduceArmAngle(armExtensionSpeed);
				} else {
					Robot.m_arm.setMotorSpeed(0);
					System.out.println("Arm Extension: LOW PRESET");
				}
				break;

			// Medium Preset
			case "Medium":
				if (getArmAngle() < Constants.Predetermined.Arm.Extension.medium) {
					Robot.m_arm.setMotorSpeed(Constants.Motors.Speeds.arm);
					increaseArmAngle(armExtensionSpeed);
				} else if (getArmAngle() > Constants.Predetermined.Arm.Extension.medium) {
					Robot.m_arm.setMotorSpeed(-Constants.Motors.Speeds.arm);
					reduceArmAngle(armExtensionSpeed);
				} else {
					Robot.m_arm.setMotorSpeed(0);
					System.out.println("Arm Extension: MEDIUM PRESET");
				}
				break;

			// High Preset
			case "High":
				if (getArmAngle() < Constants.Predetermined.Arm.Extension.high) {
					Robot.m_arm.setMotorSpeed(Constants.Motors.Speeds.arm);
					increaseArmAngle(armExtensionSpeed);
				} else if (getArmAngle() > Constants.Predetermined.Arm.Extension.high) {
					Robot.m_arm.setMotorSpeed(-Constants.Motors.Speeds.arm);
					reduceArmAngle(armExtensionSpeed);
				} else {
					Robot.m_arm.setMotorSpeed(0);
					System.out.println("Arm Extension: HIGH PRESET");
				}
				break;

		 	case "Default":

				if (getArmAngle() > 0) {
					Robot.m_arm.setMotorSpeed(-Constants.Motors.Speeds.arm);
					reduceArmAngle(armExtensionSpeed);
				} else {
					Robot.m_arm.setMotorSpeed(0);
					System.out.println("Arm Exension: Reset");
				} 

			/* case "Autonomous":

				if (getArmAngle() < autonomousExtension) {
					Robot.m_arm.setMotorSpeed(Constants.Motors.Speeds.arm);
					increaseArmAngle(armExtensionSpeed);
				} else if (getArmAngle() > autonomousExtension) {
					Robot.m_arm.setMotorSpeed(-Constants.Motors.Speeds.arm);
					reduceArmAngle(armExtensionSpeed);
				} else {
					Robot.m_arm.setMotorSpeed(0);
					System.out.println("Arm Extension: Autonomous");
				}

				if (reset && timer.hasElapsed(time) && getArmAngle() > 0) {

					Robot.m_arm.setMotorSpeed(-Constants.Motors.Speeds.arm);
					reduceArmAngle(armExtensionSpeed);
					System.out.println("Arm Extension: " + getArmAngle());

				} else if (getArmAngle() == 0 || reset == false && timer.hasElapsed(time)) {
					Robot.m_arm.setMotorSpeed(0);

				}
				break;
 */

				

			default:
				Robot.m_arm.setMotorSpeed(0);
				break;
		}

		System.out.println("Arm Extension: " + getArmAngle());
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		Robot.m_arm.setMotorSpeed(0);

	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		if ((option == "Low" && getArmAngle() == Constants.Predetermined.Arm.Extension.low) ||
				(option == "Medium" && getArmAngle() == Constants.Predetermined.Arm.Extension.medium) ||
				(option == "High" && getArmAngle() == Constants.Predetermined.Arm.Extension.high) ||
				(option == "Default" && getArmAngle() == 0)// ||
		// (option == "Autonomous" && getArmAngle() == autonomousExtension)
		) {

			return true;
		}
	/* 	if (reset && option == "Autonomous" && timer.hasElapsed(time) && getArmAngle() > 0) {

			Robot.m_arm.setMotorSpeed(-Constants.Motors.Speeds.arm);
			reduceArmAngle(armExtensionSpeed);
			System.out.println("Arm Extension: " + getArmAngle());

		} else if (getArmAngle() == 0 || reset == false && timer.hasElapsed(time)) {
			Robot.m_arm.setMotorSpeed(0);
			return true;
		} */

		return false;
	}
}