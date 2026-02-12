package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.robotcore.hardware.ColorRangeSensor;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.QQTest;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestColorSensorV2;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestColorSensorV3;

import java.util.Arrays;
import java.util.List;

public class BallLocationSensors extends QQMechanism{
    public ColorRangeSensor intakeSensor;
    public ColorRangeSensor transferSensor;
    //public ColorRangeSensor holdingCellSensor;

    @Override
    public void init(HardwareMap hardwareMap) {
        intakeSensor = hardwareMap.get(ColorRangeSensor.class, "intake_sensor");
        transferSensor = hardwareMap.get(ColorRangeSensor.class, "transfer_sensor");
        //holdingCellSensor = hardwareMap.get(ColorRangeSensor.class, "holding_cell_sensor");
    }
    public List<QQTest> getTests(){
        return Arrays.asList(
                new TestColorSensorV2("IntakeSensor", intakeSensor),
                new TestColorSensorV2("TransferSensor", transferSensor)
                //new TestColorSensorV2("HoldingCellSensor", holdingCellSensor)
        );
    }
    public boolean isBallinIntake(){
        if(intakeSensor.getDistance(DistanceUnit.CM) < 100){
            return true;
        }else{
            return false;
        }
    }
    public boolean isBallinTransfer(){
        if(transferSensor.getDistance(DistanceUnit.CM) < 100){
            return true;
        }else{
            return false;
        }
    }
    /*
    public boolean isBallinHoldingCell(){
        if(holdingCellSensor.getDistance(DistanceUnit.INCH) < 2){
            return true;
        }else{
            return false;
        }
    }
     */
}
