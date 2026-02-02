package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.QQTest;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestServo;

import java.util.Arrays;
import java.util.List;

@Configurable
public class Transfer extends QQMechanism{
    public static double STORAGE_UP_POSITION = 0.4;
    public static double SHOOTER_UP_POSITION = 0.4;
    public static double STORAGE_DOWN_POSITION = 0.75;
    public static double SHOOTER_DOWN_POSITION = 0.24;
    // public static double STORAGE_ANGLE_POSITION = 0.68;
    public static double SHOOTER_FLAT_POSITION = 0.15;


    Servo shooterTransferServo;
    Servo storageTransferServo;

    ElapsedTime elapsedTime = new ElapsedTime();
    boolean shouldMoveToHoldingCell;

    @Override
    public void init(HardwareMap hardwareMap) {
        shooterTransferServo = hardwareMap.get(Servo.class, "shooter_transfer");
        storageTransferServo = hardwareMap.get(Servo.class, "storage_transfer");
    }

    @Override
    public List<QQTest> getTests() {
        return Arrays.asList(
                new TestServo("shooterServo up", shooterTransferServo, SHOOTER_UP_POSITION, SHOOTER_DOWN_POSITION),
                new TestServo("storageServo up", storageTransferServo, STORAGE_UP_POSITION, STORAGE_DOWN_POSITION)
        );
    }
    public void storeBall(){
        storageTransferServo.setPosition(STORAGE_UP_POSITION);
    }

    public void shooterDown(){
        shooterTransferServo.setPosition((SHOOTER_DOWN_POSITION));
    }

    public void storageDown(){
        storageTransferServo.setPosition((STORAGE_DOWN_POSITION));
    }


    public void moveToShooter(){
        shooterTransferServo.setPosition(SHOOTER_UP_POSITION);
    }

    String IntoStorageStep = "MOVING_TO_FLAT";
    public void moveIntoStorage(){
        switch(IntoStorageStep){
            case "MOVING_TO_FLAT":
                // 1. Move shooter to flat
                shooterTransferServo.setPosition(SHOOTER_FLAT_POSITION);
                elapsedTime.reset();
                IntoStorageStep = "WAIT_FOR_BALL_TO_ROLL";
                break;
            case "WAIT_FOR_BALL_TO_ROLL":
                // 2. 5 ms later, move storage up
                if (elapsedTime.milliseconds() > 250){
                    storageTransferServo.setPosition(STORAGE_UP_POSITION);
                    elapsedTime.reset();
                    IntoStorageStep = "WAIT_FOR_STORAGE";
                }
                break;
            case "WAIT_FOR_STORAGE":
            // 3. 5 ms later, move shooter back to down
                if(elapsedTime.milliseconds() > 100){
                    shooterTransferServo.setPosition(SHOOTER_DOWN_POSITION);
                    IntoStorageStep = "DONE";
                }
                break;
            case "DONE":
                if (Math.abs(storageTransferServo.getPosition() - STORAGE_DOWN_POSITION) < 0.1){
                    IntoStorageStep = "MOVING_TO_FLAT";
                }
        }
    }

    String outOfStorageStep = "MOVING_TO_FLAT";

    // 1.storage moves down (x)
    // 2. Shooter servo moves flat
    // 3. ball rolls (wait), move shooter back to normal
    public void moveOutOfStorage() {
        switch (outOfStorageStep) {
            case "MOVING_TO_FLAT":
                shooterTransferServo.setPosition(SHOOTER_FLAT_POSITION);
                storageTransferServo.setPosition(STORAGE_DOWN_POSITION);
                 elapsedTime.reset();
                 outOfStorageStep = "WAIT_FOR_STORAGE";
                 break;
            case "WAIT_FOR_STORAGE":
                 if (elapsedTime.milliseconds() > 1_000){
                     shooterTransferServo.setPosition(SHOOTER_DOWN_POSITION);
                     outOfStorageStep = "DONE";
                 }
                 break;
            case "DONE":
                if (Math.abs(storageTransferServo.getPosition() - STORAGE_UP_POSITION) < 0.1){
                    outOfStorageStep = "MOVING_TO_FLAT";
                }
                break;

        }
    }


    public void update(Telemetry telemetry){
        super.update(telemetry);
        telemetry.addData("should move to holding cell", shouldMoveToHoldingCell);
    }

    public void resetBothDown(){
        shooterTransferServo.setPosition(SHOOTER_DOWN_POSITION);
        storageTransferServo.setPosition(STORAGE_DOWN_POSITION);
    }

    public void startAutoPosition(){
        shooterTransferServo.setPosition(SHOOTER_DOWN_POSITION);
        storageTransferServo.setPosition(STORAGE_UP_POSITION);
    }

    public void setPosition(boolean shooter, boolean storage){
        if (shooter){
            shooterTransferServo.setPosition(SHOOTER_UP_POSITION);
        }
        if (storage){
            moveIntoStorage();
            if(!shooter && (shooterTransferServo.getPosition() == SHOOTER_DOWN_POSITION)){
                shooterTransferServo.setPosition(SHOOTER_DOWN_POSITION);
            }
        }else {
            moveOutOfStorage();
            if(!shooter){
                shooterTransferServo.setPosition(SHOOTER_DOWN_POSITION);
            }
        }
    }
}

