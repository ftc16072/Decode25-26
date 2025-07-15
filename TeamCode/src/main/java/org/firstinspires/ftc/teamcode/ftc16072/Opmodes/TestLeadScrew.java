package org.firstinspires.ftc.teamcode.ftc16072.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@SuppressWarnings("unused")
@TeleOp
public class TestLeadScrew extends QQOpmode{
    @Override
    public void loop() {
        super.loop();
        if (gamepad1.dpad_up){
            robot.leadScrew.gotoLength(250, DistanceUnit.MM);
        } else if (gamepad1.dpad_down) {
            robot.leadScrew.gotoLength(0, DistanceUnit.MM );
        }else{
            robot.intake.stop();
        }
    }
}
