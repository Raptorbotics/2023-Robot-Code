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
import frc.robot.commands.SolenoidTeleop;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutonomousSequenceOne extends SequentialCommandGroup {

	/** Creates a new AutonomousSequenceOne. */
	public AutonomousSequenceOne() {
		// Add your commands in the addCommands() call, e.g.
		// addCommands(new FooCommand(), new BarCommand());
		addCommands(
			new SolenoidTeleop(Robot.solenoidObject),

			new DriveTeleop(
				Constants.Predetermined.Drive.autonomous,
				0,
				-1,
				0,
				0 //time robot will move in said direction
			)
		
			
		
			

			
			 
		);
	}
}
