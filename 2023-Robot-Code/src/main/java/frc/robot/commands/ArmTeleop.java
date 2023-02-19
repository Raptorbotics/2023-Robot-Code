// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.lang.constant.Constable;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class ArmTeleop extends CommandBase {

	public String option;
	public static double armExtension  = 0;
	double armExtensionSpeed = Constants.Predetermined.Arm.armExtensionSpeed;

	/** Creates a new ArmTeleop. */
	public ArmTeleop(String Option) {
		addRequirements(Robot.m_arm);
		option = Option;
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		switch (option) {
			case "Manual Retract":
				if (armExtension <= 0) {
					System.out.println("Arm height is at its minimum");
					return;
				}
				armExtension = armExtension - armExtensionSpeed;
				Robot.m_arm.setArmMotorSpeed(-Constants.Motors.Speeds.arm);
				break;
			case "Manual Extend":
				if (armExtension >= 270) {
					System.out.println("Arm height is at its maximum");
					return;
				}
				armExtension = armExtension + armExtensionSpeed;
				Robot.m_arm.setArmMotorSpeed(Constants.Motors.Speeds.arm);
				break;
			case "Low":
				if (armExtension < Constants.Predetermined.Arm.Extension.low) {
					Robot.m_arm.setArmMotorSpeed(Constants.Motors.Speeds.arm);
					armExtension = armExtension + armExtensionSpeed;
				} else if (armExtension > Constants.Predetermined.Arm.Extension.low) {
					Robot.m_arm.setArmMotorSpeed(-Constants.Motors.Speeds.arm);
					armExtension = armExtension - armExtensionSpeed;
				} else {
					Robot.m_arm.setArmMotorSpeed(0);
					System.out.println("Arm height is already at low preset");
				}
				break;
			case "Medium":
				if (armExtension < Constants.Predetermined.Arm.Extension.medium) {
					Robot.m_arm.setArmMotorSpeed(Constants.Motors.Speeds.arm);
					armExtension = armExtension + armExtensionSpeed;
				} else if (armExtension > Constants.Predetermined.Arm.Extension.medium) {
					Robot.m_arm.setArmMotorSpeed(-Constants.Motors.Speeds.arm);
					armExtension = armExtension - armExtensionSpeed;
				} else {
					Robot.m_arm.setArmMotorSpeed(0);
					System.out.println("Arm height is already at low preset");
				}
				break;
			case "High":
				if (armExtension < Constants.Predetermined.Arm.Extension.high) {
					Robot.m_arm.setArmMotorSpeed(Constants.Motors.Speeds.arm);
					armExtension = armExtension + armExtensionSpeed;
				} else if (armExtension > Constants.Predetermined.Arm.Extension.high) {
					Robot.m_arm.setArmMotorSpeed(-Constants.Motors.Speeds.arm);
					armExtension = armExtension - armExtensionSpeed;
				} else {
					Robot.m_arm.setArmMotorSpeed(0);
					System.out.println("Arm height is already at low preset");
				}
				break;
			default:
			Robot.m_arm.setArmMotorSpeed(0);
				break;
		}

		System.out.println(armExtension);
	}
	

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		Robot.m_arm.setArmMotorSpeed(0);
	
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		if (
			(option == "Low" && armExtension == Constants.Predetermined.Arm.Extension.low) ||
			(option == "Medium" && armExtension == Constants.Predetermined.Arm.Extension.medium) ||
			(option == "High" && armExtension == Constants.Predetermined.Arm.Extension.high)
		) {
			return true;
		}
	return false;
}}

