package org.firstinspires.ftc.teamcode.ftc16072.Tests;

import android.graphics.Color;

import com.qualcomm.hardware.sparkfun.SparkFunLEDStick;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TestLEDs extends QQTest {
    SparkFunLEDStick testLEDs;

    public TestLEDs (String name, SparkFunLEDStick sparkFunLEDStick){
        super(name);
        testLEDs = sparkFunLEDStick;
    }
    @Override
    public void run (Telemetry telemetry, boolean on) {
        if (on) {
            testLEDs.setColor(Color.RED);
        } else {
            testLEDs.turnAllOff();
        }
    }
}
