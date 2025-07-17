package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.QQTest;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestCRServo;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestColorSensorV3;

import java.util.Arrays;
import java.util.List;

public class Intake extends QQMechanism{
    public static final double TEST_SPEED = 0.5;
    public static final double OUTTAKE_SPEED = 1.0;
    public static final double INTAKE_SPEED = -1.0;
    CRServo intakeServo;
//    RevColorSensorV3 intakeColorSensor;

    @Override
    public void init(HardwareMap hardwareMap) {
        intakeServo =  hardwareMap.get(CRServo.class, "intake_servo");
  //      intakeColorSensor = hardwareMap.get(RevColorSensorV3.class , "intake_color" );

    }
    @Override
    public List<QQTest> getTests() {

        return Arrays.asList(
                new TestCRServo("outtake", intakeServo, TEST_SPEED),
                new TestCRServo("intake", intakeServo, -TEST_SPEED)
    //            new TestColorSensorV3("color", intakeColorSensor)
        );
    }
    public void outtake(){
        intakeServo.setPower(OUTTAKE_SPEED);
    }
    public void intake(){
        intakeServo.setPower(INTAKE_SPEED);
    }
    public void stop(){
        intakeServo.setPower(0);
    }
    public void slow(){intakeServo.setPower(0.5);}
}
