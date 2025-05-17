package org.firstinspires.ftc.teamcode.ftc16072.Tests;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.GoBildaPinpointDriver;


public class TestOdo extends QQTest {
    GoBildaPinpointDriver testOdo;

    public TestOdo(String name, GoBildaPinpointDriver odo) {
        super(name);
        testOdo = odo;
    }

    @Override
    public void run(Telemetry telemetry, boolean on) {
        if(on){
            testOdo.setPosition(new Pose2D(DistanceUnit.MM,0,0,AngleUnit.RADIANS,0));
        }else{
            Pose2D currentPose = testOdo.getPosition();
            telemetry.addData("Odo X", currentPose.getX(DistanceUnit.MM));
            telemetry.addData("Odo Y", currentPose.getY(DistanceUnit.MM));
            telemetry.addData("Odo Heading", currentPose.getHeading(AngleUnit.DEGREES));
            telemetry.update();
        }
    }
}
