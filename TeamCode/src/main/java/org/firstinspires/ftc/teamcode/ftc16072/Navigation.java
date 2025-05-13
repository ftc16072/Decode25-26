package org.firstinspires.ftc.teamcode.ftc16072;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Navigation {
    Robot robot;
    public Navigation(Robot robot){
       this.robot = robot;
    }
    public void driveFieldRelative(double forward, double strafe, double rotate){
        // to convert cartesian to polar
        double theta = Math.atan2(forward, strafe);
        double r = Math.hypot(forward, strafe);

        // rotate angle
        double robotAngle = robot.controlHub.getYaw(AngleUnit.RADIANS);
        theta = AngleUnit.normalizeRadians(theta - robotAngle);

        // convert back to cartesian
        double newForward = r * Math.sin(theta);
        double newStrafe = r * Math.cos(theta);

        robot.mecanumDrive.move(newForward, newStrafe, rotate);

    }
}
