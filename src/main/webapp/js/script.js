window.addEventListener("load", () => {
    let inp = document.getElementById('find_drug_input');
    $("#find_drug_button").on("click", () => {
        $.get("FrontController?command=find_drug_get_table&drug_name=" + inp.value, function (responseXml) {
            $("#responseTable").html($(responseXml));
            console.log(responseXml);
        });
    })

});