package org.firstinspires.ftc.teamcode.ftc16072.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@SuppressWarnings("unused")
@TeleOp
public class TestIntake extends QQOpmode{
    @Override
    public void loop() {
        super.loop();
        if (gamepad1.dpad_up){
            robot.intake.outtake();
        } else if (gamepad1.dpad_down) {
            robot.intake.intake();
        }else{
            robot.intake.stop();
        }
    }
}
