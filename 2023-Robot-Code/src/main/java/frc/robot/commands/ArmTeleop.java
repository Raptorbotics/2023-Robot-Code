// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class ArmTeleop extends CommandBase {

	Timer m_timer = new Timer();
	double m_time;
	boolean buttonPressedFinal;

	/** Creates a new ArmTeleop. */
	public ArmTeleop(double time, Boolean buttonPressedOne) {
		buttonPressedFinal = buttonPressedOne;

		m_time = time;
		// Use addRequirements() here to declare subsystem dependencies.
		addRequirements(Robot.m_arm);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		m_timer.start();
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		Robot.m_arm.setMotorSpeed(Constants.motorSpeeds.setArmMotorSpeed);
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		Robot.m_arm.setMotorSpeed(0);
		m_timer.reset();
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		if (buttonPressedFinal = true) {
			return m_timer.get() > m_time;
		} else if (buttonPressedFinal = false) {
			return false;
		} else {
			return true;
		}
	}
}
