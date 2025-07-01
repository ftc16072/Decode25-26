package org.firstinspires.ftc.teamcode.ftc16072.Tests;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class TestColorSensorV3 extends QQTest {
    RevColorSensorV3 testColorSensor;

    public TestColorSensorV3(String name, RevColorSensorV3 colorSensor){
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
