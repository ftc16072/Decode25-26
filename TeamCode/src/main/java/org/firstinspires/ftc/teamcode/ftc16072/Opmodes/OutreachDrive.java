package org.firstinspires.ftc.teamcode.ftc16072.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.MecanumDrive;

@TeleOp
public class OutreachDrive  extends QQOpmode{
    public static final double TRIGGER_THRESHOLD = 0.5;
    public void loop(){
        super.loop();
        nav.driveFieldRelative(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);

        if ((gamepad1.left_trigger > TRIGGER_THRESHOLD) &&
                (gamepad1.right_trigger > TRIGGER_THRESHOLD)) {
            robot.mecanumDrive.setSpeed(MecanumDrive.Speed.TURBO);
        } else if (gamepad1.left_bumper) {
            robot.mecanumDrive.setSpeed(MecanumDrive.Speed.NORMAL);
        } else robot.mecanumDrive.setSpeed(MecanumDrive.Speed.SLOW);
    }
}
