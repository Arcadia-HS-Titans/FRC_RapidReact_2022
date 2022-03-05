function createTitle(name) {
    return "<h1 class=\"topic animateInRight\">" + name + "</h1>";
}

let color_sensor = 
createTitle("Color Sensor:") + 
`
<div class = "centerImg">
<p class = "explanation animateInRight">The color sensor's role is to check whether or not a ball taken is is of the correct team.</p>
<img src="assets/colorsensor.png" width="750px" class = "animateInRight" id = "pixycamauto" alt="ColorSensor">
</div>
`

//Component functions
//Auto
function pixycam_auto() {
    document.getElementById("info").innerHTML = 
        createTitle("Pixycam Auto:") +
        `
        <div class = "centerImg">
        <p class = "explanation animateInRight">The pixycam's role in auto is to track the high goal before firing the pre-loaded ball.</p>
        <img src="assets/pixycamauto.png" width="750px" class = "animateInRight" id = "pixycamauto" alt="PixycamAuto">
        </div>
        `;
}

function color_auto() {
    document.getElementById("info").innerHTML = color_sensor;
}

function encoder_auto() {
    document.getElementById("info").innerHTML = 
        createTitle("Encoder Auto:") +
        `
        <div class = "centerImg">
        <p class = "explanation animateInRight">The Encoder's role during auto is to control the distance which the robot travels.</p>
        <img src="assets/encoderauto.png" width="750px" class = "animateInRight" id = "encoderauto" alt="EncoderAuto">
        </div>
        `;
}

//Teleop
function pixycam_teleop() {
    document.getElementById("info").innerHTML = 
        createTitle("Pixycam Teleop:") +
        `
        <div class = "centerImg">
        <p class = "explanation animateInRight">The Pixycam's role during teleop is to collect data about the position of the goal.</p>
        <img src="assets/pixycamteleop.png" width="625px" class = "animateInRight" id = "pixycamteleop" alt="PixycamTeleop">
        </div>
        `;
}

function color_teleop() {
    document.getElementById("info").innerHTML = color_sensor;
}

function encoder_teleop() {
    document.getElementById("info").innerHTML = 
        createTitle("Encoder Teleop:") +
        `
        <div class = "centerImg">
        <p class = "explanation animateInRight">The Encoder's role during teleop is to collect data about RPM and speed.</p>
        <img src="assets/encoderteleop.png" width="625px" class = "animateInRight" id = "encoderteleop" alt="EncoderTeleop">
        </div>
        `;
}