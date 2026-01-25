package org.firstinspires.ftc.teamcode.ftc16072.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.MecanumDrive;

//@TeleOp
public class TestTransfer extends QQOpmode {
    public static final double TRIGGER_THRESHOLD = 0.5;
    public double angleDegrees = 35;
    public boolean isRed = true;

    public boolean storageUp;
    public boolean shooterUp;

    public void init_loop() {
        super.init_loop();
        if (gamepad1.x) {
            isRed = false;
        } else if (gamepad1.b) {
            isRed = true;
        }
        telemetry.addData("Alliance", isRed ? "Red" : "Blue");

    }

    @Override
    public void start(){
        robot.odoPods.setPose(new Pose2D(DistanceUnit.INCH, 0, 0, AngleUnit.DEGREES, 0));
        super.start();
        robot.transfer.resetBothDown();
        robot.outtake.spinUp();
    }

    @Override
    public void loop() {
       super.loop();
       if (gamepad1.dpad_up){
           shooterUp=true;
       }
       if (gamepad1.dpad_down){
           shooterUp=false;

       }
      if (gamepad1.dpad_right){
          storageUp=true;
      }
      if (gamepad1.dpad_left){
          storageUp=false;
      }
      telemetry.addData("Storage Up", storageUp);
      telemetry.addData("Shooter Up", shooterUp);
      robot.transfer.setPosition(shooterUp,storageUp);

    }
}



