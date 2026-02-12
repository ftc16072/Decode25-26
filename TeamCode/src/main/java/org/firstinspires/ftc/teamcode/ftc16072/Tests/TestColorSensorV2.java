package org.firstinspires.ftc.teamcode.ftc16072.Tests;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class TestColorSensorV2 extends QQTest {
    ColorRangeSensor testColorSensor;

    public TestColorSensorV2(String name, ColorRangeSensor colorSensor){
        super(name);
        testColorSensor = colorSensor;
    }
    @Override
    public void run(Telemetry telemetry, boolean on) {
        telemetry.addData(name + ":red", testColorSensor.red());
        telemetry.addData(name + ":green", testColorSensor.green());
        telemetry.addData(name + ":blue", testColorSensor.blue());
        telemetry.addData(name + ":distance (cm)", testColorSensor.getDistance(DistanceUnit.CM));
    }
}
