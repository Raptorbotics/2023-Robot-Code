// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.ArmTeleop;
import frc.robot.commands.DriveTeleop;
import frc.robot.commands.ShoulderTeleop;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutonomousSequenceOne extends SequentialCommandGroup {

	/** Creates a new AutonomousSequenceOne. */
	public AutonomousSequenceOne() {
		// Add your commands in the addCommands() call, e.g.
		// addCommands(new FooCommand(), new BarCommand());
		addCommands(
			new DriveTeleop(
				Constants.Predetermined.Drive.autonomous,
				Constants.AutonomousSpeeds.Drive.SequenceOne.xInput,
				Constants.AutonomousSpeeds.Drive.SequenceOne.yInput,
				Constants.AutonomousSpeeds.Drive.SequenceOne.zInput,
				1
			),
			new ArmTeleop("Autonomous", Robot.m_arm, 270, 4, true),
			new ShoulderTeleop("Autonomous", Robot.m_shoulder, 270, 3, false),
			new DriveTeleop(
				Constants.Predetermined.Drive.autonomous,
				Constants.AutonomousSpeeds.Drive.SequenceOne.xInput,
				Constants.AutonomousSpeeds.Drive.SequenceOne.yInput,
				Constants.AutonomousSpeeds.Drive.SequenceOne.zInput,
				6
			)
		);
	}
}
