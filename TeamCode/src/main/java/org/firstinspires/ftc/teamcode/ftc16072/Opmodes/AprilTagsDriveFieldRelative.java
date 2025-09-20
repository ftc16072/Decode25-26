package org.firstinspires.ftc.teamcode.ftc16072.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class AprilTagsDriveFieldRelative extends QQOpmode{
    public void loop(){
        super.loop();
        nav.driveFieldRelative(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
        robot.camera.telemetryAprilTag(telemetry);
    }
}
