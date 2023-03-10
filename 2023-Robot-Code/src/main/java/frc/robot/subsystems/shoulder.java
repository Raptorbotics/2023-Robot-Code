// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class shoulder extends SubsystemBase {

	private double shoulderAngle = 0;

	public void setShoulderAngle(double value) {
		shoulderAngle = value;
	}

	public double getShoulderAngle() {
		return shoulderAngle;
	}

	public void reduceShoulderAngle(double amount) {
		shoulderAngle = shoulderAngle - amount;
	}

	public void increaseShoulderAngle(double amount) {
		shoulderAngle = shoulderAngle + amount;
	}

	PWMVictorSPX shoulderMotor = new PWMVictorSPX(Constants.Motors.shoulder.shoulderMotor);

	public void setMotorSpeed(double axis) {
		shoulderMotor.set(axis);
	}

	/** Creates a new shoulder. */
	public shoulder() {}

	// CHANGE TO FALCON SPEED CONTROLLER TYPE (LOOOK AT ARM CODE FOR 2022 ROBOT)

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
}
