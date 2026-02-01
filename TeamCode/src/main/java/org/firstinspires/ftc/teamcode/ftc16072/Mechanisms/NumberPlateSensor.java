package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.ftc16072.Tests.QQTest;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestColorSensorV3;

import java.util.Arrays;
import java.util.List;


public class NumberPlateSensor extends QQMechanism{

    public RevColorSensorV3 colorSensor;

    @Override
    public void init(HardwareMap hardwareMap) {
        colorSensor = hardwareMap.get(RevColorSensorV3.class, "NumberPlateSensor");
    }

    @Override
    public List<QQTest> getTests() {
        return Arrays.asList(
                new TestColorSensorV3("NumberPlateSensor",  colorSensor)
        );
    }

    public boolean redAlliance(){
        if (colorSensor.red() > colorSensor.blue()){
            return true;
        }
        else{
            return false;
        }

    }




}
