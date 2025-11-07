package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.UniversalCode.fourWheelDrive;

@TeleOp(name = "11089Omnidrive", group="Linear OpMode")
public class _11089Omnidrive extends LinearOpMode {

    private DcMotor front_left_drive;
    private DcMotor back_left_drive;
    private DcMotor front_right_drive;
    private DcMotor back_right_drive;
    private DcMotor Arm, Arm2;
    double forwardsVelocity;
    double horizontalVelocity;
    double Dir_x, Dir_y;
    double speed = 0.3;

    private double Clamp(double value, double min, double max) {
        return Math.min(max, Math.max(value, min));
    }
    /**
     * This function is executed when this OpMode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        front_left_drive = hardwareMap.get(DcMotor.class, "fld");
        back_left_drive = hardwareMap.get(DcMotor.class, "bld");
        front_right_drive = hardwareMap.get(DcMotor.class, "frd");
        back_right_drive = hardwareMap.get(DcMotor.class, "brd");
        Arm = hardwareMap.get(DcMotor.class, "Arm");
        Arm2 = hardwareMap.get(DcMotor.class, "Arm2");
        // You will have to determine which motor to reverse for your robot.
        // In this example, the right motor was reversed so that positive
        // applied power makes it move the robot in the forward direction.
        //front_left_drive.setDirection(DcMotor.Direction.REVERSE);
            //front_left_drive.setDirection(DcMotor.Direction.REVERSE);
            front_right_drive.setDirection(DcMotor.Direction.REVERSE);
            //back_left_drive.setDirection(DcMotor.Direction.REVERSE);
            back_right_drive.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.

            while (opModeIsActive()) {
                // Mario Cart Controls
                forwardsVelocity = -gamepad1.left_trigger + gamepad1.right_trigger;
                horizontalVelocity = gamepad1.left_stick_x  * speed; // janky but works cause I don't feel like changing it all
                // Right Stick
                Dir_x = gamepad1.right_stick_x;
                forwardsVelocity -= gamepad1.right_stick_y;
                if (gamepad1.right_bumper ) {
                    Arm.setPower(-1.0);
                    Arm2.setPower(-1.0);
                } else if (gamepad1.left_bumper) {
                    Arm.setPower(1.0);
                    Arm2.setPower(1.0);
                } else {
                    Arm.setPower(0.0);
                    Arm2.setPower(0.0);
                }
                // Put loop blocks here.
                // The Y axis of a joystick ranges from -1 in its topmost position to +1 in its bottommost position.
                // We negate this value so that the topmost position corresponds to maximum forward power.
                front_left_drive.setPower(Clamp((((forwardsVelocity + Dir_x) * speed) + horizontalVelocity), -1.0, 1.0));
                front_right_drive.setPower(Clamp(((forwardsVelocity - Dir_x) * speed) - horizontalVelocity, -1.0, 1.0));

                back_left_drive.setPower(Clamp(((forwardsVelocity - Dir_x) * speed) + horizontalVelocity, -1.0, 1.0));
                back_right_drive.setPower(Clamp(((forwardsVelocity + Dir_x )* speed) - horizontalVelocity, -1.0, 1.0));
                // Telemetry is how we send data to the driverhub
                telemetry.addData("Power", forwardsVelocity);
                telemetry.addData("direction", horizontalVelocity);
                telemetry.update();
            }
        }
    }
}


