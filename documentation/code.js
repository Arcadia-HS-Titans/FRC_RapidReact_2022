function createTitle(name) {
    return "<h1 class=\"topic animateInRight\">" + name + "</h1>";
}

//Component functions
//Auto
function pixycam_auto() {
    document.getElementById("info").innerHTML = 
        createTitle("Pixycam Auto:");
}

function color_auto() {
    document.getElementById("info").innerHTML = 
        createTitle("Color Sensor Auto:");
}

function encoder_auto() {
    document.getElementById("info").innerHTML = 
        createTitle("Encoder Auto:");
}

function accel_auto() {
    document.getElementById("info").innerHTML = 
        createTitle("Accelerometer Auto:");
}

//Teleop
function pixycam_teleop() {
    document.getElementById("info").innerHTML = 
        createTitle("Pixycam Teleop:");
}

function color_teleop() {
    document.getElementById("info").innerHTML = 
        createTitle("Color Sensor Teleop:");
}

function encoder_teleop() {
    document.getElementById("info").innerHTML = 
        createTitle("Encoder Teleop:");
}

function accel_teleop() {
    document.getElementById("info").innerHTML = 
        createTitle("Accelerometer Teleop:");
}