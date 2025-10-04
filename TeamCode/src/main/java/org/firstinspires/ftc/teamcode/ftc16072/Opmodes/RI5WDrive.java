package org.firstinspires.ftc.teamcode.ftc16072.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.MecanumDrive;

@TeleOp
public class RI5WDrive extends QQOpmode{
    double angle = 45;
    public static final double TRIGGER_THRESHOLD = 0.5;
    boolean isRed;

    @Override
    public void init_loop() {
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
        if (gamepad1.right_trigger > TRIGGER_THRESHOLD){
            telemetry.addData("Alliance", isRed ? "Red" : "Blue");
            double bearingToTargetDegrees = robot.camera.getBearingToTargetDegrees(isRed);
            telemetry.addData("Bearing Degrees", bearingToTargetDegrees);
            double turnSpeed = calculateTurn(bearingToTargetDegrees);
            nav.driveFieldRelative(-gamepad1.left_stick_y, gamepad1.left_stick_x, turnSpeed);

        }else{
            nav.driveFieldRelative(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
        }


        if ((gamepad1.left_trigger > TRIGGER_THRESHOLD)) {
            robot.mecanumDrive.setSpeed(MecanumDrive.Speed.TURBO);
        } else if (gamepad1.left_bumper) {
            robot.mecanumDrive.setSpeed(MecanumDrive.Speed.SLOW);
        } else robot.mecanumDrive.setSpeed(MecanumDrive.Speed.NORMAL);

        if(gamepad1.a){
            robot.intake.intake();
        } else if (gamepad1.x) {
            robot.intake.eject();
        }else{
            robot.intake.stop();
        }


//        int balls = robot.intake.numberOfBalls() - robot.outtake.numberOfBallsShot();
//        telemetry.addData("Number of Balls", balls);


        if (gamepad1.y) {
            if (gamepad1.right_bumper) {
                robot.outtake.stop();
            } else {
                robot.outtake.spinUp();
            }
        }

        if ((gamepad1.dpad_left) && (robot.outtake.isReady(telemetry))){
            robot.transfer.moveUp();
        }
        if ((gamepad1.dpad_right)){
            robot.transfer.moveDown();
        }
        else{
            robot.transfer.stop();
        }


         if (gamepad1.dpadUpWasPressed()) {
             angle += 5;
             robot.outtake.setAngle(angle, AngleUnit.DEGREES, telemetry);
         }
         if (gamepad1.dpadDownWasPressed()) {
             angle -= 5;
             robot.outtake.setAngle(angle, AngleUnit.DEGREES, telemetry);
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