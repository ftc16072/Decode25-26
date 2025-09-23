package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;


import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.QQTest;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestCRServo;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestColorSensorV3;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestTouchSensor;

import org.firstinspires.ftc.teamcode.ftc16072.Tests.QQTest;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestMotor;

import java.util.Arrays;
import java.util.List;

public class Intake extends QQMechanism{
    public static final double TEST_SPEED = 0.5;
    public static final double OUTTAKE_SPEED = 1.0;
    public static final double INTAKE_SPEED = -1.0;
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
                new TestMotor("outtake", intakeMotor, TEST_SPEED),
                new TestMotor("intake", intakeMotor, -TEST_SPEED),
                new TestTouchSensor("limitSwitch", limitSwitch)
    //            new TestColorSensorV3("color", intakeColorSensor)
        );
    }
    public void outtake(){
        intakeMotor.setPower(OUTTAKE_SPEED);
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
