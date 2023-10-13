// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.TalonFXSimCollection;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.armConstants;

public class arm extends SubsystemBase {

	WPI_TalonFX armMotor = new WPI_TalonFX(armConstants.armMotor);
	TalonFXSimCollection armMotorSim = new TalonFXSimCollection(armMotor);
	TalonFXConfiguration config = new TalonFXConfiguration();

	public void setArmLength(double position) {
		armMotor.set(ControlMode.Position, position);
	}
	/* 
	public void changeArmLength(double amount) {
		armMotor.set(ControlMode.Position, armMotor.getSelectedSensorPosition() + amount);
	}
	*/

	public void changeArmLength(double amount) {
		int kMeasuredPosHorizontal = 840; //Position measured when arm is horizontal
		double kTicksPerDegree = 4096 / 360; //Sensor is 1:1 with arm rotation
		double currentPos = armMotor.getSelectedSensorPosition();
		double degrees = (currentPos - kMeasuredPosHorizontal) / kTicksPerDegree;
		double radians = java.lang.Math.toRadians(degrees);
		double cosineScalar = java.lang.Math.cos(radians);

		double maxGravityFF = 0.07;

		armMotor.set(ControlMode.MotionMagic, currentPos + amount, DemandType.ArbitraryFeedForward, maxGravityFF * cosineScalar);
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
		config.primaryPID.selectedFeedbackSensor = TalonFXFeedbackDevice.IntegratedSensor.toFeedbackDevice();
		config.neutralDeadband = 0.001;
		config.peakOutputForward = .4;
		config.peakOutputReverse = .4;
		config.slot0.kP = 0;
		config.slot0.kI = 0;
		config.slot0.kD = 0;
		config.slot0.kF = 0;
		config.slot0.integralZone = 0; //kIZone
		config.slot0.closedLoopPeakOutput = 0.2;
		config.slot0.allowableClosedloopError = 0;
		config.slot0.closedLoopPeriod = 1; //in Ms
		armMotor.configAllSettings(config);
		armMotor.setStatusFramePeriod(StatusFrame.Status_12_Feedback1, 20, 20);
		armMotor.selectProfileSlot(0, 0);

		//https://v5.docs.ctr-electronics.com/en/stable/ch16_ClosedLoop.html
		//https://v5.docs.ctr-electronics.com/en/stable/ch16_ClosedLoop.html#sensor-preparation
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}

	public void simulationPeriodic() {
	}
}
