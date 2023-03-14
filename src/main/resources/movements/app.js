const image = document.getElementById("character");
let x = 0;
let y = 0;
image.style.position = "absolute";
image.style.left = x + "px";  //X kordínáta
image.style.top = y + "px";   //y kodínáta mozgás

document.addEventListener("keydown", (event) => { //Billentyűzet hallgoató
    switch (event.key) {
        case "ArrowUp":     //FEL GOMB
            y -= 10;
            break;
        case "ArrowDown":    //LE GOMB
            y += 10;
            break;
        case "ArrowLeft":    //BAL GOMB
            x -= 10;
            break;
        case "ArrowRight":    //JOBB GOMB
            x += 10;
            break;
        default:
            return;
    }

    image.style.left = x + "px";
    image.style.top = y + "px";
});