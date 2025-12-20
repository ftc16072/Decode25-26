package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.hardware.ams.AMSColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.QQTest;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestServo;

import java.util.Arrays;
import java.util.List;

public class Transfer extends QQMechanism{
    public static final double STORAGE_UP_POSITION = .4;
    // REMEMBER THE POSITIONS FOR THE STORAGE SERVO, THE LEFT ONE ARE INVERSE
    double SHOOTER_POSITION = 0.6; //need to figure out position
    double STORAGE_POSITION = 0.67; //need to figure out position
    //0.7
    //0.55
    double SHOOTER_DOWN_POSITION = 0.55; //need to figure out position
    double STORAGE_DOWN_POSITION = 0.525; //need to figure out position
    double STORAGE_ANGLE_POSITION = 0.68;
    double SHOOTER_ANGLE_POSITION=0.53;


    Servo shooterTransferServo;
    Servo storageTransferServo;

    ElapsedTime elapsedTimeSinceStorageServo = new ElapsedTime();
    boolean shouldMoveToHoldingCell;

    @Override
    public void init(HardwareMap hardwareMap) {
        shooterTransferServo = hardwareMap.get(Servo.class, "shooter_transfer");
        storageTransferServo = hardwareMap.get(Servo.class, "storage_transfer");


    }

    @Override
    public List<QQTest> getTests() {
        return Arrays.asList(
                new TestServo("shooterServo up", shooterTransferServo, SHOOTER_POSITION, SHOOTER_DOWN_POSITION),
                new TestServo("storageServo up", storageTransferServo, STORAGE_POSITION, STORAGE_DOWN_POSITION)
        );
    }
    public void storeBall(){
        storageTransferServo.setPosition(STORAGE_POSITION);
    }

    public void shooterDown(){
        shooterTransferServo.setPosition((SHOOTER_DOWN_POSITION));
    }

    public void storageDown(){
        storageTransferServo.setPosition((STORAGE_DOWN_POSITION));
    }


    public void moveToShooter(){
        shooterTransferServo.setPosition(SHOOTER_POSITION);
        storageTransferServo.setPosition(STORAGE_DOWN_POSITION);
    }
//    public void moveSmallAngle(){
//        shooterTransferServo.setPosition(SHOOTER_ANGLE_POSITION);
//        storageTransferServo.setPosition(STORAGE_ANGLE_POSITION);
//    }
//    public void moveBigAngle(){
//        storageTransferServo.setPosition(.4);
//    }
    public void moveToStorage(Telemetry telemetry){
        storageTransferServo.setPosition(STORAGE_UP_POSITION);

//        if(!shouldMoveToHoldingCell) {
//            shooterTransferServo.setPosition(SHOOTER_ANGLE_POSITION);
//            storageTransferServo.setPosition(STORAGE_ANGLE_POSITION);
//
//            shouldMoveToHoldingCell = true;
//            elapsedTimeSinceStorageServo.reset();
//        }
//        if (shouldMoveToHoldingCell){
//            telemetry.addData("Time since pressed", elapsedTimeSinceStorageServo.seconds());
//            if (elapsedTimeSinceStorageServo.seconds() > 1.0){
//                storageTransferServo.setPosition(.4);
//            }
//        }
    }
    public void update(Telemetry telemetry){
        super.update(telemetry);
        telemetry.addData("should move to holding cell", shouldMoveToHoldingCell);
    }

    public void shooterServoUp(){
        shooterTransferServo.setPosition(1);
    }

    public void resetBothDown(){
        shooterTransferServo.setPosition(SHOOTER_DOWN_POSITION);
        storageTransferServo.setPosition(STORAGE_DOWN_POSITION);
    }

    public void startAutoPosition(){
        shooterTransferServo.setPosition(SHOOTER_DOWN_POSITION);
        storageTransferServo.setPosition(STORAGE_POSITION);
    }


}

