package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.QQTest;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestOdo;

import java.util.Arrays;
import java.util.List;

public class OdoPods extends QQMechanism{
    GoBildaPinpointDriver odo;
    @Override
    public void init(HardwareMap hardwareMap) {
        odo = hardwareMap.get(GoBildaPinpointDriver.class,"odo");
    }
    public Pose2D getPose(){
        return odo.getPosition();
    }
    public void setPose(Pose2D newPose){
        odo.setPosition(newPose);
    }
    @Override
    public List<QQTest> getTests() {
        return Arrays.asList(
                new TestOdo("odoPods", odo)

        );
    }

}
