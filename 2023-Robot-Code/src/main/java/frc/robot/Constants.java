// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

	public static final double m_limiter = .5;

	public final class Predetermined {

		public final class Drive {

			public static final String teleop = "Teleop";
			public static final String autonomous = "Autonomous";
		}

		public final class Arm {

			public static final double speed = 1;

			public final class Extension {

				public static final double low = 100;
				public static final double medium = 50;
				public static final double high = 10;
			}
		}

		public final class Shoulder {

			public static final double speed = 1;

			public final class Height {

				public static final double low = 90;
				public static final double medium = 120;
				public static final double high = 250;
			}
		}
	}

	public final class AutonomousSpeeds {

		public final class Drive {

			public final class SequenceOne {

				public static final double xInput = 0;
				public static final double yInput = .5;
				public static final double zInput = 0;
			}
		}

		public final class Arm {}

		public final class Shoulder {}
	}

	public final class Motors {

		public final class Drivetrain {

			public static final int frontLeftMotorOne = 9;
			public static final int frontLeftMotorTwo = 8;

			public static final int frontRightMotorOne = 2;
			public static final int frontRightMotorTwo = 3;

			public static final int backLeftMotorOne = 6;
			public static final int backLeftMotorTwo = 7;

			public static final int backRightMotorOne = 4;
			public static final int backRightMotorTwo = 5;
		}

		public final class arm {

			public static final int armMotor = 1;
		}

		public final class shoulder {

			public static final int shoulderMotor = 0;
		}

		public final class Speeds {

			public static final double arm = .4;
			public static final double armStop = .19;
			public static final double armRelease = .1;
			public static final double shoulder = .3;
		}
	}

	public final class Controller {

		public static final int m_controller = 0;

		public final class Joystick {

			public static final int m_leftStickY = 1;
			public static final int m_leftStickX = 0;
			public static final int m_rightStickX = 4;

			public static final int m_leftTrigger = 2;
			public static final int m_rightTrigger = 3;
		}

		public final class Buttons {

			public static final int m_aButton = 1;
			public static final int m_bButton = 2;
			public static final int m_xButton = 3;
			public static final int m_yButton = 4;
		}

		public final class Bumpers {

			public static final int m_lBumper = 5;
			public static final int m_rBumper = 6;
		}
	}
}
