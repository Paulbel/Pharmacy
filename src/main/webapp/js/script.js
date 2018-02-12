window.addEventListener("load", () => {
    let drugInput = document.getElementById('find_drug_input');
    $("#find_drug_button").on("click", () => {
        $.get("FrontController?command=find_drug_get_table&drug_name=" + drugInput.value, function (responseXml) {
            $("#responseTable").html($(responseXml));
            console.log(responseXml);
        });
    });

    let manufacturerInput = document.getElementById('find_manufacturer_input');
    $("#find_manufacturer_button").on("click", () => {
        $.get("FrontController?command=find_manufacturer_get_table&manufacturer_name=" + manufacturerInput.value, function (responseXml) {
            $("#responseTable").html($(responseXml));
            console.log(responseXml);
        });
    });

    let manufacturerSelectInput = document.getElementById('find_manufacturer_select_input');
    $("#find_manufacturer_select_button").on("click", () => {
        $.get("FrontController?command=find_manufacturer_get_select&manufacturer_name=" + manufacturerSelectInput.value, function (responseXml) {
            $("#responseSelect").html($(responseXml));
            console.log(responseXml);
        });
    })
});