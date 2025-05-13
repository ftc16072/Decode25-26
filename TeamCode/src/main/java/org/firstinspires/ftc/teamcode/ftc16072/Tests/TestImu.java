package org.firstinspires.ftc.teamcode.ftc16072.Tests;

import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TestImu extends QQTest{
    IMU imu;
    public TestImu(String name, IMU imu){
        super(name);
        this.imu = imu;
    }
    @Override
    public void run(Telemetry telemetry, boolean on) {
        telemetry.addData(name, imu.getRobotYawPitchRollAngles());
        if(on){
            imu.resetYaw();
        }
    }

}
