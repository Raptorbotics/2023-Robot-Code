// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class drivetrain extends SubsystemBase {

	/** Creates a new ExampleSubsystem. */
	private PWMSparkMax frontLeftMotorOne = new PWMSparkMax(Constants.Motors.Drivetrain.frontLeftMotorOne);
	private PWMSparkMax frontLeftMotorTwo = new PWMSparkMax(Constants.Motors.Drivetrain.frontLeftMotorTwo);

	private PWMSparkMax frontRightMotorOne = new PWMSparkMax(Constants.Motors.Drivetrain.frontRightMotorOne);
	private PWMSparkMax frontRightMotorTwo = new PWMSparkMax(Constants.Motors.Drivetrain.frontRightMotorTwo);

	private PWMSparkMax backLeftMotorOne = new PWMSparkMax(Constants.Motors.Drivetrain.backLeftMotorOne);
	private PWMSparkMax backLeftMotorTwo = new PWMSparkMax(Constants.Motors.Drivetrain.backLeftMotorTwo);

	private PWMSparkMax backRightMotorOne = new PWMSparkMax(Constants.Motors.Drivetrain.backRightMotorOne);
	private PWMSparkMax backRightMotorTwo = new PWMSparkMax(Constants.Motors.Drivetrain.backRightMotorTwo);

	MotorControllerGroup m_frontLeft = new MotorControllerGroup(frontLeftMotorOne, frontLeftMotorTwo);
	MotorControllerGroup m_frontRight = new MotorControllerGroup(frontRightMotorOne, frontRightMotorTwo);
	MotorControllerGroup m_backleft = new MotorControllerGroup(backLeftMotorOne, backLeftMotorTwo);
	MotorControllerGroup m_backRight = new MotorControllerGroup(backRightMotorOne, backRightMotorTwo);

	public drivetrain() {
		backLeftMotorOne.setInverted(true);
		backLeftMotorTwo.setInverted(true);
		backRightMotorOne.setInverted(true);
		backRightMotorTwo.setInverted(true);
		frontLeftMotorTwo.setInverted(true);




		Robot.drive = new MecanumDrive(m_frontLeft, m_backleft, m_frontRight, m_backRight);
	}

	public void setMotorSpeed(double xAxis, double yAxis, double zAxis, double d) {
		Robot.drive.driveCartesian(xAxis, yAxis, zAxis);
	}

	public boolean exampleCondition() {
		// Query some boolean state, such as a digital sensor.
		return false;
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}

	@Override
	public void simulationPeriodic() {
		// This method will be called once per scheduler run during simulation
	}
	public void MotorTest(){

		System.out.print("FRONT LEFT MOTOR ONE " + frontLeftMotorOne.get()+ " ");
		System.out.print("FRONT LEFT MOTOR TWO " + frontLeftMotorTwo.get()+ " ");


		System.out.print(" FRONT RIGHT MOTOR ONE " + frontRightMotorOne.get()+ " ");
		System.out.print(" FRONT RIGHT MOTOR TWO " + frontRightMotorTwo.get()+ " ");

	
		System.out.print(" BACK LEFT MOTOR ONE " + backLeftMotorOne.get()+ " ");
		System.out.print(" BACK LEFT MOTOR TWO " + backLeftMotorTwo.get()+ " ");

	
		System.out.print(" BACK RIGHT MOTOR ONE " + backRightMotorOne.get()+ " ");
		System.out.print(" BACK RIGHT MOTOR TWO " + backRightMotorTwo.get()+ " ");

	}
}
