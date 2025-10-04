package org.firstinspires.ftc.teamcode.ftc16072.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.MecanumDrive;

@TeleOp
public class RI5WDrive extends QQOpmode{
    double angle = 45;
    public static final double TRIGGER_THRESHOLD = 0.5;

    public void loop(){
        super.loop();
        nav.driveFieldRelative(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);


        if ((gamepad1.left_trigger > TRIGGER_THRESHOLD)) {
            robot.mecanumDrive.setSpeed(MecanumDrive.Speed.TURBO);
        } else if (gamepad1.left_bumper) {
            robot.mecanumDrive.setSpeed(MecanumDrive.Speed.SLOW);
        } else robot.mecanumDrive.setSpeed(MecanumDrive.Speed.NORMAL);

        if(gamepad1.a){
            robot.intake.intake();
        } else if (gamepad1.x) {
            robot.intake.outtake();
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


         if (gamepad1.dpad_up) {
             angle+=5;
             robot.outtake.setAngle(angle, AngleUnit.DEGREES, telemetry);
         }
         if (gamepad1.dpad_down) {
             angle-=5;
             robot.outtake.setAngle(angle, AngleUnit.DEGREES, telemetry);
         }
    }
}