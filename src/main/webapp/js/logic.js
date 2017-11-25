$(document).ready(function () {
    $("#menu").click(function (event) {
        console.log("ez");
    });
});



var modal = document.getElementById('id01');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
function tabOpen(tab) {
    tab.classList.remove("infoNotDisplayed");
    tab.classList.remove("reduceInfo");
    tab.classList.add("growInfo");
    openedElement = tab;
}


function tabClose(tab) {
    tab.classList.remove("growInfo");
    tab.classList.add("reduceInfo");
    openedElement = null;
}


