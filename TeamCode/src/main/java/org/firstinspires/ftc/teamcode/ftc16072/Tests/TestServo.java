package org.firstinspires.ftc.teamcode.ftc16072.Tests;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TestServo extends QQTest{
    Servo testServo;
    double onPosition;
    double offPosition;

    public TestServo(String name, Servo servo, double onPosition, double offPosition) {
        super(name);
        testServo = servo;
        this.onPosition = onPosition;
        this.offPosition = offPosition;
    }
    @Override
    public void run(Telemetry telemetry, boolean on) {
        if(on){
            telemetry.addData(name, onPosition);
            testServo.setPosition(onPosition);
        }else{
            telemetry.addData(name, offPosition);
            testServo.setPosition(offPosition);
        }
    }
}
