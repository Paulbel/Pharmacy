var openedElement = null;
var shadeIsOpened = true;

$(document).ready(function () {
    $(".bar").click(function (event) {
        var bar = event.target.parentNode;
        var infodiv = bar.childNodes.item(5);
        if (openedElement == null) {
            tabOpen(infodiv);
        }
        else if(openedElement == infodiv){
            tabClose(infodiv);
        }
        else {
            tabClose(openedElement);
            tabOpen(infodiv);
        }
    });

    $(".arrow").click(function (event) {
        var arrow = event.target;

        arrow.classList.add()
        if(shadeIsOpened == true) {
            shadeIsOpened = false;
            arrow.classList.remove("rotateArrowTop");
            arrow.classList.add("rotateArrowDown");
        }
        else {

            shadeIsOpened = true;
            arrow.classList.remove("rotateArrowDown");
            arrow.classList.add("rotateArrowTop");
        }
    })
});

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


