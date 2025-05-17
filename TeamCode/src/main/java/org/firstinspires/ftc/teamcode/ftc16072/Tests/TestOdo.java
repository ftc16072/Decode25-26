package org.firstinspires.ftc.teamcode.ftc16072.Tests;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.GoBildaPinpointDriver;


public class TestOdo extends QQTest {
    GoBildaPinpointDriver testOdo;
    Pose2D testPose;

    public TestOdo(String name, GoBildaPinpointDriver odo, Pose2D testPose) {
        super(name);
        testOdo = odo;
        this.testPose = testPose;
    }

    @Override
    public void run(Telemetry telemetry, boolean on) {
        testOdo.setPosition(testPose);
        Pose2D currentPose = testOdo.getPosition();
        telemetry.addData("Odo X", currentPose.getX(DistanceUnit.MM));
        telemetry.addData("Odo Y", currentPose.getY(DistanceUnit.MM));
        telemetry.addData("Odo Heading", currentPose.getHeading(AngleUnit.RADIANS));
        telemetry.update();
    }
}
