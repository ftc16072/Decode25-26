package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;


import org.firstinspires.ftc.teamcode.ftc16072.Tests.QQTest;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestTouchSensor;

import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestMotor;

import java.util.Arrays;
import java.util.List;

public class Intake extends QQMechanism{
    public static final double EJECT_SPEED = -1.0;
    public static final double INTAKE_SPEED = 1.0;
    DcMotor intakeMotor;
    TouchSensor limitSwitch;
    int balls;
    boolean wasLimitSwitchPressed;

    @Override
    public void init(HardwareMap hardwareMap) {
        intakeMotor =  hardwareMap.get(DcMotor.class, "intakeMotor");
        limitSwitch = hardwareMap.get(TouchSensor.class, "limitSwitch");
  //      intakeColorSensor = hardwareMap.get(RevColorSensorV3.class , "intake_color" );
    }
    @Override
    public List<QQTest> getTests() {

        return Arrays.asList(
                new TestMotor("eject", intakeMotor, EJECT_SPEED / 2),
                new TestMotor("intake", intakeMotor, INTAKE_SPEED / 2),
                new TestTouchSensor("limitSwitch", limitSwitch)
    //            new TestColorSensorV3("color", intakeColorSensor)
        );
    }
    public void eject(){
        intakeMotor.setPower(EJECT_SPEED);
    }
    public void intake(){
        intakeMotor.setPower(INTAKE_SPEED);
    }
    public void stop(){
        intakeMotor.setPower(0);
    }
    public void slow(){
        intakeMotor.setPower(0.5);
    }

    public int numberOfBalls(){
        if(limitSwitch.isPressed() && !wasLimitSwitchPressed){
            balls++;
        }
        wasLimitSwitchPressed = limitSwitch.isPressed();
        return balls;
    }
}
