package org.firstinspires.ftc.teamcode.ftc16072.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

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
        if (gamepad1.y) {
            robot.pivot.setDestinationAngleDegrees(35);
        } else if (gamepad1.dpad_up){
            robot.pivot.manualPositionChange(MANUAL_CHANGE);
        } else if (gamepad1.dpad_down){
            robot.pivot.manualPositionChange(-MANUAL_CHANGE);
        }

        if (gamepad1.a) {
            robot.intake.intake();
        } else if (gamepad1.x) {
            robot.intake.outtake();
        } else robot.intake.stop();

//once code for slides is written teleop code has to be written for them too
    }
}
