package org.firstinspires.ftc.teamcode.ftc16072.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.ftc16072.Navigation;
import org.firstinspires.ftc.teamcode.ftc16072.Robot;

public abstract class JLOpmode extends OpMode {
    public Robot robot = new Robot();
    public Navigation nav = new Navigation(robot);

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop(){
        robot.update(telemetry);
    }

}
