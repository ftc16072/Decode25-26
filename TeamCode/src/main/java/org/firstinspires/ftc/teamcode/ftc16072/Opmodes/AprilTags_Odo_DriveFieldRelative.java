package org.firstinspires.ftc.teamcode.ftc16072.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

@TeleOp
public class AprilTags_Odo_DriveFieldRelative extends QQOpmode{
    public boolean isRed = true;
    public void init_loop(){
        super.init_loop();
        if(gamepad1.x){
            isRed = false;
        }else if(gamepad1.b){
            isRed = true;
        }
        telemetry.addData("Alliance", isRed ? "Red" : "Blue");

    }
    public void loop(){
        super.loop();
        telemetry.addData("Alliance", isRed ? "Red" : "Blue");
        double bearingToTargetDegrees = robot.camera.getBearingToTargetDegrees(isRed);
        telemetry.addData("Bearing Degrees", bearingToTargetDegrees);

        double turnSpeed = calculateTurn(bearingToTargetDegrees);
        if(!gamepad1.left_bumper){
            turnSpeed = gamepad1.right_stick_x;
        }
        nav.driveFieldRelative(-gamepad1.left_stick_y, gamepad1.left_stick_x, turnSpeed);
        telemetry.addData("Turn Speed", turnSpeed);
        telemetry.addData("Pose2D:",robot.odoPods.getPose());
        robot.camera.telemetryAprilTag(telemetry);
        if(robot.camera.isAprilTagVisible()){
            telemetry.addLine("OdoPod Reset");
            telemetry.addData("AprilTag X", robot.camera.getPosXInches());
            telemetry.addData("AprilTag Y", robot.camera.getPosYInches());
            telemetry.addData("AprilTag H", robot.camera.getHeadingDegrees());
            robot.odoPods.setPose(new Pose2D(DistanceUnit.INCH, robot.camera.getPosXInches(), robot.camera.getPosYInches(), AngleUnit.DEGREES, robot.camera.getHeadingDegrees()));
        }
    }


    private double lastError = 0;
    private double sumErrors = 0;
    ElapsedTime timer = new ElapsedTime();
    private double calculateTurn(double bearingDegrees){
        double KP = 0.02;
        double KI = 0;
        double KD = 0.005;
        double maxSpeed = 0.75;
/*
        if (bearingDegrees < -30){
            return -0.5;
        }
        if (bearingDegrees > 30){
            return 0.5;
        }
*/

        double error = bearingDegrees - 0;
        double derivative = (error - lastError) / timer.seconds();
        double speed = (KP * error) + (KD * derivative);
        if(Math.abs(speed) > maxSpeed){
            speed = maxSpeed * Math.signum(speed);
        }
        if(Math.abs(bearingDegrees) < 3){
            speed = 0;
        }
        lastError = error;
        return speed;
    }
}
