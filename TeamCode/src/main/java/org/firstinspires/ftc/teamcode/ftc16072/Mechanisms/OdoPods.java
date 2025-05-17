package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.QQTest;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestOdo;

import java.util.Arrays;
import java.util.List;

public class OdoPods extends QQMechanism{
    GoBildaPinpointDriver leftOdoPod;
    GoBildaPinpointDriver rightOdoPod;
    @Override
    public void init(HardwareMap hardwareMap) {
        leftOdoPod = hardwareMap.get(GoBildaPinpointDriver.class,"odo");
        rightOdoPod = hardwareMap.get(GoBildaPinpointDriver.class,"odo");
    }
    public Pose2D getLeftPose(){
        return leftOdoPod.getPosition();
    }
    public void setLeftPose(Pose2D newPose){
        leftOdoPod.setPosition(newPose);
    }
    public Pose2D getRightPose(){
        return rightOdoPod.getPosition();
    }
    public void setRightPose(Pose2D newPose){
        rightOdoPod.setPosition(newPose);
    }
    @Override
    public List<QQTest> getTests() {

        return Arrays.asList(
                new TestOdo("leftOdo", leftOdoPod,leftOdoPod.getPosition()),
                new TestOdo("rightOdo", rightOdoPod,rightOdoPod.getPosition())
        );
    }
}
