// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;

public class solenoid extends SubsystemBase {
  /** Creates a new solenoid. */
  Solenoid solenoidObject = new Solenoid(PneumaticsModuleType.CTREPCM, 1);

  private boolean solenoidState = solenoidObject.get();

  public boolean getSolenoidState() {
    return solenoidState;
  }

  public void setSolenoidStateTrue() {
    solenoidObject.set(true);
    solenoidState = solenoidObject.get();
    System.out.println("The solenoidState has been set to " + solenoidState);
  }
  public void setSolenoidStateFalse() {
    solenoidObject.set(false);
    solenoidState = solenoidObject.get();
    System.out.println("The solenoidState has been set to " + solenoidState);
  }


  public solenoid() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
