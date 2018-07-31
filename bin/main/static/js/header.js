function openNav() {
    document.getElementById("sideNav").style.width = "250px";
    //document.body.style.backgroundColor = "rgba(0,0,0,0.4)";
    document.getElementById("mask").style.display = "block";
}

function closeNav() {
    document.getElementById("sideNav").style.width = "0";
    //document.body.style.backgroundColor = "white";
    document.getElementById("mask").style.display = "none";
}