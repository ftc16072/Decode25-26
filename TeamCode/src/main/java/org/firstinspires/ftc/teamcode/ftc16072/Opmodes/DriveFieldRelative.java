package org.firstinspires.ftc.teamcode.ftc16072.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class DriveFieldRelative extends QQOpmode{
    public void loop(){
        super.loop();
        nav.driveFieldRelative(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
        telemetry.addData("X", robot.odoPods.getXInches());
        telemetry.addData("Y", robot.odoPods.getYInches());
        telemetry.addData("H",robot.odoPods.getHeadingDegrees());
    }
}
