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
	static double armStop = Constants.Motors.Speeds.armStop;
	static double armRelease = Constants.Motors.Speeds.armRelease;
	String option;
	private arm m_arm;

	double time;
	Timer timer = new Timer();

	double autonomousExtension;

	public double getArmLength() { // Gets the length (double) of the arm
		return m_arm.getArmLength();
	}

	public void reduceArmLength(double amount) { // Retracts the arm by an amount which is the negative of armExtensionSpeed
		m_arm.reduceArmLength(amount);
		m_arm.setMotorSpeed(-0.08);
	}

	public void increaseArmLength(double amount) { // Extends the arm by an amount which is the positive of armExtensionSpeed
		m_arm.increaseArmLength(amount);
		m_arm.setMotorSpeed(Constants.Motors.Speeds.arm);
	}
	public ArmTeleop(String Option, arm armSubsystem, double autonomousArmExtension, double m_time) { // When the command is called, passes in all of these values which is used to run the code in this file
		addRequirements(Robot.m_arm); // Adds the Robot.m_arm subsystem as a requirement to use this command

		option = Option;
		autonomousExtension = autonomousArmExtension;
		m_arm = armSubsystem;

		time = m_time;

	}

	// When the command is called for the first time, runs this function
	@Override
	public void initialize() { 
		timer.start();
	}

	// Every time this command is ran or called, this function is executed
	@Override
	public void execute() { 
		switch (option) {
			case "Manual Retract":
				reduceArmLength(armExtensionSpeed); // If the condition above returns false (greater than 0) then the reduceArmLength function will run
				break;
			case "Manual Extend": 
				increaseArmLength(armExtensionSpeed); // If the condition above returns false (lesser than 270) then the increaseArmLength function will run
				break;
			// This case is for emergency purposes in the case that the arm motor value needs to be reset to 0 or the default position
			case "Default":
				if (getArmLength() > 0) {
					reduceArmLength(armExtensionSpeed);
				} else {
					m_arm.setMotorSpeed(armStop);
					System.out.println("Arm Exension: Reset");
				}
			// This is the autonomous case, where the robot will run automatically
			 case "Autonomous":

				if (getArmLength() < autonomousExtension) { // If the arm length is less than the autonomous preset, it will increase the arm length until it reaches the autonomous preset
					increaseArmLength(armExtensionSpeed);
				} else if (getArmLength() > autonomousExtension) {// If the arm length is greater than the autonomous preset, it will decrease the arm length until it reaches the autonomous preset
					reduceArmLength(armExtensionSpeed);
				} else { // If the arm length is already at the autonomous preset, it will refuse to run the motor anymore
					m_arm.setMotorSpeed(armStop);
					System.out.println("Arm Extension: Autonomous");
				}

				if (timer.hasElapsed(time) && getArmLength() > 0) { // If the autonomous timer has been passed and the armLength is greater than 0, it will retract the arm back to 0
					reduceArmLength(armExtensionSpeed);

				} else if (getArmLength() == 0 || timer.hasElapsed(time)) { // If the autonomous timer or the arm length is already 0, the motor speed will be set to 0
					m_arm.setMotorSpeed(armStop);

				}
				break;

			default:
				m_arm.setMotorSpeed(armStop); // In the case that something goes wrong, the motor will be turned off
				break;
		}

		System.out.println("Arm Extension: " + getArmLength());
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) { // When the keybind is let go, the motor will be turned off
		m_arm.setMotorSpeed(armStop);
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() { // If a preset or autonomous preset is finished, it will turn off the motor
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

		} else if (option == "Autonomous" && timer.hasElapsed(time) && getArmLength() == 0) {

			m_arm.setMotorSpeed(armStop);
			return true;

		}

		return false;
	}
}