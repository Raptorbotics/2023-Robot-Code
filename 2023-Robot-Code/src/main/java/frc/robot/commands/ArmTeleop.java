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

	//static double armStop = Constants.Motors.Speeds.armStop;
	//static double armRelease = Constants.Motors.Speeds.armRelease;
	String option;
	private arm m_arm;

	double autonomousExtension;

	public ArmTeleop(arm armSubsystem, String optionString) { // When the command code in this file
		option = optionString;
		m_arm = armSubsystem;

		addRequirements(m_arm); // Adds the Robot.m_arm subsystem as a requirement to use this command
	}

	// When the command is called for the first time, runs this function
	@Override
	public void initialize() {
	}

	// Every time this command is ran or called, this function is executed
	@Override
	public void execute() {
		switch (option) {
			case "increase":
			m_arm.changeArmLength(1);
				break;
			case "decrease":
			m_arm.changeArmLength(-1);
				break;
			case "set":
			m_arm.setArmLength(0);
				break;
		
			default:
				break;
		}
		System.out.println("Arm Rotations: " + (m_arm.getArmLength() / 2048));
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) { // When the keybind is let go, the motor will be turned off
		m_arm.changeArmLength(0);
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() { // If a preset or autonomous preset is finished, it will turn off the motor
		if(option == "set") {
			return true;
		}
		return false;
	}
}