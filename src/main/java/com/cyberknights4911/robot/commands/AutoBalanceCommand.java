package com.cyberknights4911.robot.commands;


import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;

import com.cyberknights4911.robot.constants.Constants;
import com.cyberknights4911.robot.subsystems.SwerveSubsystem;

public class AutoBalanceCommand extends CommandBase {
    
    
    private SwerveSubsystem mSwerveSubsystem;
    Translation2d tilt;


    
    public AutoBalanceCommand (SwerveSubsystem swerveSubsystem){

       mSwerveSubsystem = SwerveSubsystem.getInstance();
        mSwerveSubsystem = swerveSubsystem;
        addRequirements(mSwerveSubsystem);

      
    }
    
    @Override
    public void initialize() {

    }

    @Override
  public void execute() {
    tilt = new Translation2d(getRoll(), getPitch()); 
    tilt = tilt.times(Constants.MAX_SPEED);
    mSwerveSubsystem.setTeleopInputs(
                tilt.getX(),
                tilt.getY(),
                tilt.getAngle().getDegrees(),
                false,
                true,
                true);
  }

  @Override
  public void end(boolean interrupted) {
    mSwerveSubsystem.setTeleopInputs(
        0.0,
        0.0,
        0.0,
        false,
        true,
        true);  
    }

  @Override
  public boolean isFinished() {
    return (tilt.getNorm()<2); //TODO: make constants
  }
    

  private double getRoll() {
    return mSwerveSubsystem.getRoll().getDegrees();
  }

  private double getPitch() {
    return mSwerveSubsystem.getPitch().getDegrees();
  }

  
}