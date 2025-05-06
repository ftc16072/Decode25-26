package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.QQTest;

import java.util.List;

abstract public class QQMechanism {
    List <QQTest> tests;
    abstract public void init(HardwareMap hardwareMap);
    public void update(Telemetry telemetry){
    }
    public List <QQTest> getTests(){
        return tests;
    }
    public String getName(){
        return this.getClass().getSimpleName();
    }

}
