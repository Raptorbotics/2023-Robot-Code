// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.TalonFXSimCollection;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.armConstants;

public class arm extends SubsystemBase {

	WPI_TalonFX armMotor = new WPI_TalonFX(armConstants.armMotor);

	TalonFXSimCollection armMotorSim = new TalonFXSimCollection(armMotor);

	public void setArmLength(double position) {
		armMotor.set(ControlMode.Position, position);
	}
	/* 
	public void changeArmLength(double amount) {
		armMotor.set(ControlMode.Position, armMotor.getSelectedSensorPosition() + amount);
	}
	*/

	public void changeArmLength(double amount) {
		armMotor.set(ControlMode.PercentOutput, amount * .4);
	}




	public double getArmLength() {
		return armMotor.getSelectedSensorPosition();
	}

	public arm() {
		final ErrorCode resetStatus = armMotor.setSelectedSensorPosition(0);
		if(resetStatus != ErrorCode.valueOf(0)) {
			System.out.println("Something went wrong with resetting armMotor back to position: " + resetStatus);
		} else {
			System.out.println("ArmMotor Reset Status: " + resetStatus);
		}
		armMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 30);
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}

	public void simulationPeriodic() {
	}
}
