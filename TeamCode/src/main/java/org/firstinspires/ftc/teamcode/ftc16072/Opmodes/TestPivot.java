package org.firstinspires.ftc.teamcode.ftc16072.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@SuppressWarnings("unused")
@TeleOp
public class TestPivot extends QQOpmode {
    public void loop() {
        super.loop();

        if (gamepad1.dpad_down) {
            robot.pivot.setDestinationAngleDegrees(0);
        } else if (gamepad1.dpad_right){
            robot.pivot.setDestinationAngleDegrees(90);
        } else if (gamepad1.dpad_up){
            robot.pivot.setDestinationAngleDegrees(180);
        }
        if(gamepad1.a){
            robot.pivot.moveToSwitch();
        }
        if (gamepad1.b) {
           robot.pivot.stop();
        }
    }
}
