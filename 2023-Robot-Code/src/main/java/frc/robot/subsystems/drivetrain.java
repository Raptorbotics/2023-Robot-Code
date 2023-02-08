// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class drivetrain extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  public drivetrain() {}

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public CommandBase exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.

    PWMSparkMax frontLeftMotor = new PWMSparkMax(Constants.Drivetrain.frontLeftMotor);
    PWMSparkMax frontRightMotor = new PWMSparkMax(Constants.Drivetrain.frontRightMotor);
    PWMSparkMax backLeftMotor = new  PWMSparkMax(Constants.Drivetrain.backLeftMotor);
    PWMSparkMax backRightMotor = new PWMSparkMax(Constants.Drivetrain.backRightMotor);

  


    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
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

  public setMotorSpeed(){

    Robot.m_drivetrain.
  }
}
