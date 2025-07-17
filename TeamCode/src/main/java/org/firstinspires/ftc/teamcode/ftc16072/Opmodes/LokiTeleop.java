package org.firstinspires.ftc.teamcode.ftc16072.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.MecanumDrive;

@TeleOp
public class LokiTeleop extends QQOpmode{

    public static final double TRIGGER_THRESHOLD = 0.5;
    public static final int MANUAL_CHANGE = 5;

    public void loop(){
        super.loop();
        robot.mecanumDrive.move(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);

        if (gamepad1.left_trigger > TRIGGER_THRESHOLD) {
            robot.mecanumDrive.setSpeed(MecanumDrive.Speed.TURBO);
        } else if (gamepad1.left_bumper) {
            robot.mecanumDrive.setSpeed(MecanumDrive.Speed.SLOW);
        } else robot.mecanumDrive.setSpeed(MecanumDrive.Speed.NORMAL);
//parts of gamepad can be changed later

        if (gamepad1.dpad_up){
            robot.pivot.up();
        } else if (gamepad1.dpad_down){
            robot.pivot.down();
        } else {
            robot.pivot.stop();
        }

        if (gamepad1.a) {
            robot.intake.intake();
        } else if (gamepad1.x) {
            robot.intake.outtake();
        } else{
            robot.intake.slow();
        }

        if (gamepad1.dpad_left){
            robot.leadScrew.in();
        } else if (gamepad1.dpad_right){
            robot.leadScrew.out();
        } else {
            robot.leadScrew.stop();
        }
/*
        if(gamepad1.right_trigger > TRIGGER_THRESHOLD){
            robot.leadScrew.gotoLength(250, DistanceUnit.MM);
        } else if (gamepad1.right_bumper) {
            robot.leadScrew.gotoLength(0, DistanceUnit.MM );
        }
*/
//once code for slides is written teleop code has to be written for them too
    }
}
