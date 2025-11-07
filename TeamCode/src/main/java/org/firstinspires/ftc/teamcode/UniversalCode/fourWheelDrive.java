package org.firstinspires.ftc.teamcode.UniversalCode;


import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;

public class fourWheelDrive {
    private DcMotor front_left_drive;
    private DcMotor back_left_drive;
    private DcMotor front_right_drive;
    private DcMotor back_right_drive;
    double forwardsVelocity;
    double horizontalVelocity;
    double Dir_x, Dir_y;
    double speed = 0.3;

    /**
     * Initailization.
     * @param hardwareMap the robot hardware map
     */
    public void Init(@NonNull com.qualcomm.robotcore.hardware.HardwareMap hardwareMap,
                     boolean fl_reverse,
                     boolean fr_reverse,
                     boolean bl_reverse,
                     boolean br_reverse){
        front_left_drive = hardwareMap.get(DcMotor.class, "fld");
        back_left_drive = hardwareMap.get(DcMotor.class, "bld");
        front_right_drive = hardwareMap.get(DcMotor.class, "frd");
        back_right_drive = hardwareMap.get(DcMotor.class, "brd");


        // You will have to determine which motor to reverse for your robot.
        // In this example, the right motor was reversed so that positive
        // applied power makes it move the robot in the forward direction.
        //front_left_drive.setDirection(DcMotor.Direction.REVERSE);
        if (fl_reverse) {
            front_left_drive.setDirection(DcMotor.Direction.REVERSE);
        }
        if (fr_reverse) {
            front_right_drive.setDirection(DcMotor.Direction.REVERSE);
        }        if (bl_reverse) {
            back_left_drive.setDirection(DcMotor.Direction.REVERSE);
        }        if (br_reverse) {
            back_right_drive.setDirection(DcMotor.Direction.REVERSE);
        }




    }
    private double Clamp(double value, double min, double max) {
        return Math.min(max, Math.max(value, min));
    }


    /**
     * Update loop for Four Wheel Drive
     * @param telemetry It's the telemetry...
     * @param gamepad1 It's Gamepad 1, idk
     * @param gamepad2 It's Gamepad 2, I guess...
     */
    public void Update(@NonNull org.firstinspires.ftc.robotcore.external.Telemetry telemetry, @NonNull com.qualcomm.robotcore.hardware.Gamepad gamepad1,
                       com.qualcomm.robotcore.hardware.Gamepad gamepad2) {
        // Mario Cart Controls
        forwardsVelocity = -gamepad1.left_trigger + gamepad1.right_trigger;
        horizontalVelocity = gamepad1.left_stick_x  * speed;
        // Right Stick
        Dir_x = gamepad1.right_stick_x;
        forwardsVelocity -= gamepad1.right_stick_y;

        // Put loop blocks here.
        // The Y axis of a joystick ranges from -1 in its topmost position to +1 in its bottommost position.
        // We negate this value so that the topmost position corresponds to maximum forward power.
        front_left_drive.setPower(Clamp((((forwardsVelocity + Dir_x) * speed) - horizontalVelocity), -1.0, 1.0));
        front_right_drive.setPower(Clamp(((forwardsVelocity - Dir_x) * speed) + horizontalVelocity, -1.0, 1.0));

        back_left_drive.setPower(Clamp(((forwardsVelocity - Dir_x) * speed) - horizontalVelocity, -1.0, 1.0));
        back_right_drive.setPower(Clamp(((forwardsVelocity + Dir_x )* speed) + horizontalVelocity, -1.0, 1.0));
        // Telemetry is how we send data to the driverhub
        telemetry.addData("Power", forwardsVelocity);
        telemetry.addData("direction", horizontalVelocity);
    }

}
