
package org.firstinspires.ftc.teamcode.OpModes_Tests.Teleoperated.gamepad;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp(name="gamepad3", group="TeleOp")
//@Disabled

public class gamepad3 extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor motor_esquerdo = null;
    private DcMotor motor_direito = null;
    private DcMotor motor_garra = null;
    private CRServo servoD = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        motor_esquerdo = hardwareMap.get(DcMotor.class, "motor_esquerdo");
        motor_direito = hardwareMap.get(DcMotor.class, "motor_direito");
        motor_garra = hardwareMap.get(DcMotor.class, "motor_garra");
        servoD = hardwareMap.crservo.get("servoD");

        motor_esquerdo.setDirection(DcMotor.Direction.FORWARD);
        motor_direito.setDirection(DcMotor.Direction.REVERSE);
        motor_garra.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            double forca_motor_esquerdo;
            double forca_motor_direito;
            double forca_motor_garra;

            double acelerador_y = gamepad1.left_stick_y;
            double curvas_x = gamepad1.right_stick_x;

            double controlador_garra = gamepad2.left_stick_y;

            forca_motor_esquerdo = Range.clip(acelerador_y - curvas_x, -1.0, 1.0) ;
            forca_motor_direito = Range.clip(acelerador_y + curvas_x, -1.0, 1.0) ;
            forca_motor_garra = Range.clip(controlador_garra + controlador_garra, -1.0, 1.0);

            motor_esquerdo.setPower(forca_motor_esquerdo);
            motor_direito.setPower(forca_motor_direito);
            motor_garra.setPower(forca_motor_garra);

            if (gamepad1.a){
                motor_direito.setPower(0.2);
                motor_esquerdo.setPower(-0.2);
            }
            if (gamepad1.b){
                motor_esquerdo.setPower(-0.2);
                motor_direito.setPower(0.2);
            }
            if (gamepad2.y) {
                servoD.setPower(0.2);
            }
            if (gamepad2.x) {
                servoD.setPower(-0.2);
            }
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", forca_motor_esquerdo, forca_motor_direito);
            telemetry.update();
        }
    }
}



