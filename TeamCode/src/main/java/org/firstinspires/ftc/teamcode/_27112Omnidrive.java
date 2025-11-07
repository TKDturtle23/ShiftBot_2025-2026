package org.firstinspires.ftc.teamcode;
import org.firstinspires.ftc.teamcode.UniversalCode.*;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "27112Omnidrive", group="Linear OpMode")
public class _27112Omnidrive extends LinearOpMode {

fourWheelDrive drive;
    private DcMotor spin1;
    private DcMotor spin2;

    private Servo arm1;
    private Servo arm2;
    private Servo move1;
    private Servo move2;
    /**
     * This function is executed when this OpMode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        drive = new fourWheelDrive();

        drive.Init(hardwareMap, false, true, false, true);
        spin1 = hardwareMap.get(DcMotor.class, "spin1");
        spin2 = hardwareMap.get(DcMotor.class, "spin2");
        arm1 = hardwareMap.get(Servo.class, "arm1");
        arm2 = hardwareMap.get(Servo.class, "arm2");
        move1 = hardwareMap.get(Servo.class, "move1");
        move2 = hardwareMap.get(Servo.class, "move2");
        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.

            while (opModeIsActive()) {
                drive.Update(telemetry, gamepad1, gamepad2);
                if (gamepad1.x) {
                    spin1.setPower(1);
                    spin2.setPower(-1);
                } else {
                    spin1.setPower(0);
                    spin2.setPower(0);
                }
                if (gamepad1.right_bumper) {
                    arm1.setPosition(1.0);

                    arm2.setPosition(0.0);
                } else if (gamepad1.left_bumper) {
                    arm1.setPosition(0.0);
                    arm2.setPosition(1.0);
                } else {
                    arm1.setPosition(0.5);
                    arm2.setPosition(0.5);
                }
                if (gamepad1.y) {
                    move1.setPosition(1);
                    move2.setPosition(0);
                }
                if (gamepad1.b) {
                    move1.setPosition(0);
                    move2.setPosition(1);
                }
                if (gamepad1.a) {
                    move1.setPosition(0.5);
                    move2.setPosition(0.5);
                }
                telemetry.addData("arm rot: ", String.valueOf(arm1.getPosition()));
                telemetry.update();
            }
        }
    }
}


