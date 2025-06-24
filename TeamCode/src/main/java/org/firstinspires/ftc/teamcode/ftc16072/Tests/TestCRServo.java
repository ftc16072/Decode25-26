package org.firstinspires.ftc.teamcode.ftc16072.Tests;

import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TestCRServo extends QQTest{
    CRServo testCRServo;
    double speed;

    public  TestCRServo(String name, CRServo crServo, double speed) {
        super(name);
        testCRServo = crServo;
        this.speed = speed;
    }
    @Override
    public void run(Telemetry telemetry, boolean on) {
        if(on){
            testCRServo.setPower(speed);
        }else{
            testCRServo.setPower(0);
        }
    }
}
