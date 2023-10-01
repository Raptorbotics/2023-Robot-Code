// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain;

public class DriveTeleop extends CommandBase {

	private DoubleSupplier leftStickX;
	private DoubleSupplier leftStickY;
	private DoubleSupplier rightStickX;
	private drivetrain m_drivetrain;


	/** Creates a new Drive. */
	public DriveTeleop(DoubleSupplier leftStickXInput, DoubleSupplier leftStickYInput, DoubleSupplier rightStickXInput, drivetrain drivetrain) { // Call all file
		m_drivetrain = drivetrain;
		leftStickX = leftStickXInput;
		leftStickY = leftStickYInput;
		rightStickX = rightStickXInput;

		// Use addRequirements() here to declare subsystem dependencies.


		addRequirements(m_drivetrain); // Set the drivetrain subsystem as a requirement to run this file
	}

	@Override
	public void initialize() { // When the command is first called, this function runs
	}

	@Override // When the command is called, this function runs
	public void execute() {
		m_drivetrain.setMotorSpeed(leftStickX.getAsDouble(), leftStickY.getAsDouble(), rightStickX.getAsDouble());
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		m_drivetrain.setMotorSpeed(0, 0, 0);
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}
