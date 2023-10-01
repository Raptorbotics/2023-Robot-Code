// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import com.revrobotics.CANSparkMax;
import com.revrobotics.REVPhysicsSim;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class drivetrain extends SubsystemBase {

	/** Creates a new ExampleSubsystem. */
/*
	private PWMSparkMax frontLeftMotorOne = new PWMSparkMax(DriveConstants.frontLeftMotorOne);
	private PWMSparkMax frontLeftMotorTwo = new PWMSparkMax(DriveConstants.frontLeftMotorTwo);

	private PWMSparkMax frontRightMotorOne = new PWMSparkMax(DriveConstants.frontRightMotorOne);
	private PWMSparkMax frontRightMotorTwo = new PWMSparkMax(DriveConstants.frontRightMotorTwo);

	private PWMSparkMax backLeftMotorOne = new PWMSparkMax(DriveConstants.backLeftMotorOne);
	private PWMSparkMax backLeftMotorTwo = new PWMSparkMax(DriveConstants.backLeftMotorTwo);

	private PWMSparkMax backRightMotorOne = new PWMSparkMax(DriveConstants.backRightMotorOne);
	private PWMSparkMax backRightMotorTwo = new PWMSparkMax(DriveConstants.backRightMotorTwo);
*/

	private final CANSparkMax frontLeftMotorOne = new CANSparkMax(DriveConstants.frontLeftMotorOne, MotorType.kBrushless);
	private final CANSparkMax frontLeftMotorTwo = new CANSparkMax(DriveConstants.frontLeftMotorTwo, MotorType.kBrushless);

	private final CANSparkMax frontRightMotorOne = new CANSparkMax(DriveConstants.frontRightMotorOne, MotorType.kBrushless);
	private final CANSparkMax frontRightMotorTwo = new CANSparkMax(DriveConstants.frontRightMotorTwo, MotorType.kBrushless);

	private final CANSparkMax backLeftMotorOne = new CANSparkMax(DriveConstants.backLeftMotorOne, MotorType.kBrushless);
	private final CANSparkMax backLeftMotorTwo = new CANSparkMax(DriveConstants.backLeftMotorTwo, MotorType.kBrushless);

	private final CANSparkMax backRightMotorOne = new CANSparkMax(DriveConstants.backRightMotorOne, MotorType.kBrushless);
	private final CANSparkMax backRightMotorTwo = new CANSparkMax(DriveConstants.backRightMotorTwo, MotorType.kBrushless);

	private final MotorControllerGroup m_Left = new MotorControllerGroup(frontLeftMotorOne, frontLeftMotorTwo, backLeftMotorOne, backLeftMotorTwo);
	private final MotorControllerGroup m_Right = new MotorControllerGroup(frontRightMotorOne, frontRightMotorTwo, backRightMotorOne, backRightMotorTwo);
	
	private  final DifferentialDrive drive = new DifferentialDrive(m_Left, m_Right);

	public drivetrain() {

	}

	public void setMotorSpeed(double xAxis, double yAxis, double zAxis) {
		drive.arcadeDrive(yAxis, -zAxis);
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}

	public void simulationInit() {
		
		REVPhysicsSim.getInstance().addSparkMax(frontLeftMotorOne, DCMotor.getNEO(1));
		REVPhysicsSim.getInstance().addSparkMax(frontLeftMotorTwo, DCMotor.getNEO(1));
		REVPhysicsSim.getInstance().addSparkMax(frontRightMotorOne, DCMotor.getNEO(1));
		REVPhysicsSim.getInstance().addSparkMax(frontRightMotorTwo, DCMotor.getNEO(1));
		REVPhysicsSim.getInstance().addSparkMax(backLeftMotorOne, DCMotor.getNEO(1));
		REVPhysicsSim.getInstance().addSparkMax(backLeftMotorTwo, DCMotor.getNEO(1));
		REVPhysicsSim.getInstance().addSparkMax(backRightMotorOne, DCMotor.getNEO(1));
		REVPhysicsSim.getInstance().addSparkMax(backRightMotorTwo, DCMotor.getNEO(1));
		System.out.println("REVPhysicsSim added all sparkmaxes");
	}

	@Override
	public void simulationPeriodic() {
		// This method will be called once per scheduler run during simulation
	}
}
