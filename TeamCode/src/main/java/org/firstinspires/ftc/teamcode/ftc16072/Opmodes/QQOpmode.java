package org.firstinspires.ftc.teamcode.ftc16072.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.ftc16072.Navigation;
import org.firstinspires.ftc.teamcode.ftc16072.Robot;

public abstract class QQOpmode extends OpMode {
    public Robot robot = new Robot();
    public Navigation nav = new Navigation(robot);
    public boolean isRed;
    public boolean overideNumberPlate = false;

    public double delayTimeSeconds;


    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void init_loop(){
        if (!overideNumberPlate){
            isRed = robot.numberPlateSensor.redAlliance();
        }
        if(gamepad1.x) {
            isRed = false;
            overideNumberPlate = true;
        }
        else if(gamepad1.b){
            isRed = true;
            overideNumberPlate = true;
        }
        if(gamepad1.dpadDownWasPressed()){
            delayTimeSeconds = delayTimeSeconds - 1;
            if(delayTimeSeconds < 0 ){
                delayTimeSeconds = 0;
            }
        }
        if(gamepad1.dpadUpWasPressed()){
            delayTimeSeconds += 1;
            if(delayTimeSeconds > 30 ){
                delayTimeSeconds = 30;
            }
        }
        if (overideNumberPlate) {
            telemetry.addData("Alliance", isRed ? "NOW RED - OVERRIDE!!":"NOW BLUE - OVERRIDE!!");
        }else{
            telemetry.addData("Alliance", isRed ? "Red" : "Blue");
        }
        telemetry.addData("Delay Seconds", delayTimeSeconds);

    }

    public void start(){
        resetRuntime();
    }
    @Override
    public void loop(){
        robot.update(telemetry);
    }

}
