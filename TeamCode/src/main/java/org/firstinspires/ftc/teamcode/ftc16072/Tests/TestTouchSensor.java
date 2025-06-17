package org.firstinspires.ftc.teamcode.ftc16072.Tests;

import android.text.method.Touch;

import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TestTouchSensor extends QQTest {
    TouchSensor testTouchSensor;

    public TestTouchSensor (String name, TouchSensor touchSensor){
        super(name);
        testTouchSensor = touchSensor;
    }
    @Override
    public void run(Telemetry telemetry, boolean on) {
        telemetry.addData(name, testTouchSensor.isPressed());
    }
}
