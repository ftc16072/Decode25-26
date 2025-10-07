package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.QQTest;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestOdo;

import java.util.Arrays;
import java.util.List;

public class OdoPods extends QQMechanism{
    GoBildaPinpointDriver pinpoint;
    static boolean isInitialized;
    @Override
    public void init(HardwareMap hardwareMap) {
        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class,"pinpoint");
        if (!isInitialized) {
            configurePinpoint();
        }
    }
    public Pose2D getPose(){
        pinpoint.update();
        return pinpoint.getPosition();
    }
    public void setPose(Pose2D newPose){
        pinpoint.setPosition(newPose);
    }
    @Override
    public List<QQTest> getTests() {
        return Arrays.asList(
                new TestOdo("odoPods", pinpoint)

        );
    }
    public void update(Telemetry telemetry) {
        pinpoint.update();
    }

    private void configurePinpoint(){
        /*
         *  Set the odometry pod positions relative to the point that you want the position to be measured from.
         *
         *  The X pod offset refers to how far sideways from the tracking point the X (forward) odometry pod is.
         *  Left of the center is a positive number, right of center is a negative number.
         *
         *  The Y pod offset refers to how far forwards from the tracking point the Y (strafe) odometry pod is.
         *  Forward of center is a positive number, backwards is a negative number.
         */
        pinpoint.setOffsets(-84.0, -168.0, DistanceUnit.MM); //these are tuned for 3110-0002-0001 Product Insight #1
        pinpoint.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        pinpoint.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD,
                GoBildaPinpointDriver.EncoderDirection.FORWARD);
        pinpoint.resetPosAndIMU();
        isInitialized = true;
    }

}
