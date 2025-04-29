package org.firstinspires.ftc.teamcode.ftc16072.Tests;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TestMotor extends QQTest{
    DcMotor testMotor;
    double speed;

    public TestMotor(String name, DcMotor motor, double speed) {
        super(name);
        testMotor = motor;
        this.speed = speed;
    }

    @Override
    void run(Telemetry telemetry, boolean on) {
        if (on){
            testMotor.setPower(speed);
        }
        else{
            testMotor.setPower(0);
        }
    }
}
