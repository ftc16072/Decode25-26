package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.ftc16072.Tests.QQTest;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestColorSensorV3;

import java.util.Arrays;
import java.util.List;

public class BallLocationSensors extends QQMechanism{
    public RevColorSensorV3 intakeSensor;
    public RevColorSensorV3 transferSensor;
    public RevColorSensorV3 holdingCellSensor;

    @Override
    public void init(HardwareMap hardwareMap) {
        intakeSensor = hardwareMap.get(RevColorSensorV3.class, "intake_sensor");
        transferSensor = hardwareMap.get(RevColorSensorV3.class, "transfer_sensor");
        holdingCellSensor = hardwareMap.get(RevColorSensorV3.class, "holding_cell_sensor");
    }
    public List<QQTest> getTests(){
        return Arrays.asList(
                new TestColorSensorV3("IntakeSensor", intakeSensor),
                new TestColorSensorV3("TransferSensor", transferSensor),
                new TestColorSensorV3("HoldingCellSensor", holdingCellSensor)
        );
    }
}
