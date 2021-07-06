/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.OpModes_Tests.Teleoperated.gamepad;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="gamepad2", group="TeleOp")
//@Disabled
public class gamepad2 extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor motor_esquerdo = null;
    private DcMotor motor_direito = null;
    @Override

    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        motor_esquerdo  = hardwareMap.get(DcMotor.class, "motor_esquerdo");
        motor_direito = hardwareMap.get(DcMotor.class, "motor_direito");

        motor_esquerdo.setDirection(DcMotor.Direction.FORWARD);
        motor_direito.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            double forca_motor_esquerdo;
            double forca_motor_direito;

            double acelerador_y = gamepad1.left_stick_y;
            double curvas_x = gamepad1.left_stick_x;

            forca_motor_esquerdo = Range.clip(acelerador_y - curvas_x, -1.0, 1.0) ;
            forca_motor_direito = Range.clip(acelerador_y + curvas_x, -1.0, 1.0) ;

            motor_esquerdo.setPower(forca_motor_esquerdo);
            motor_direito.setPower(forca_motor_direito);

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", forca_motor_esquerdo, forca_motor_direito);
            telemetry.update();

            // MODO ALTERNATIVO ABAIXO:

            /*
                double forca_motor_esquerdo;
                double forca_motor_direito;

                double acelerador_y = gamepad1.left_stick_y;
                double curvas_x = gamepad1.left_stick_x;

                forca_motor_esquerdo = acelerador_y - curvas_x;
                forca_motor_direito = acelerador_y + curvas_x;

                motor_esquerdo.setPower(forca_motor_esquerdo)
                motor_direito.setPower(forca_motor_direito)
             */
        }
    }
}
// Curvas e aceleração num só joystick
