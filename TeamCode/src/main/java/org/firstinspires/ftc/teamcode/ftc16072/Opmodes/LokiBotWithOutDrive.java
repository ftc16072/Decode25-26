package org.firstinspires.ftc.teamcode.ftc16072.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class LokiBotWithOutDrive extends QQOpmode{
    public void loop(){
        super.loop();
        if (gamepad1.dpad_left){
            robot.leadScrew.in();
        } else if (gamepad1.dpad_right){
            robot.leadScrew.out();
        } else {
            robot.leadScrew.stop();
        }
        if (gamepad1.dpad_up){
            robot.pivot.up();
        } else if (gamepad1.dpad_down){
            robot.pivot.down();
        } else {
            robot.pivot.stop();
        }
        if (gamepad1.a) {
            robot.intake.intake();
        }else if (gamepad1.x) {
            robot.intake.outtake();
        }else{
            robot.intake.stop();
        }

    }
}
