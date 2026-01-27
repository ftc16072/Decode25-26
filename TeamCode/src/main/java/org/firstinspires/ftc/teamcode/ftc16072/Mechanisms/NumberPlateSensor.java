package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.teamcode.ftc16072.Tests.QQTest;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestColorSensorV3;

import java.util.Arrays;
import java.util.List;


public class NumberPlateSensor extends QQMechanism{

    public ColorSensor colorSensor;

    @Override
    public void init(HardwareMap hardwareMap) {

        colorSensor = (ColorSensor) hardwareMap.get(NumberPlateSensor.class, "numberplate_color_sensor");

        final boolean redAlliance = true;


    }

    @Override
    public List<QQTest> getTests() {

        return Arrays.asList(
                new TestColorSensorV3("NumberplateSensor", (RevColorSensorV3) colorSensor)
        );
    }


    public boolean redAlliance(){
        if (colorSensor.red() == 100 && colorSensor.blue() < 100){
             return true;

        }else if (colorSensor.blue() == 100 && colorSensor.red() < 100){
            return false;
        }else{
            return false;
        }

    }




}
