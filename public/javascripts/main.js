function openModel(amodel, amodelBtn, id, value) {
    var model = document.getElementById(amodel);
    var modelBtn = document.getElementsByClassName(amodelBtn);
    var id = document.getElementById(id);
    var closeBtn = document.getElementsByClassName("closebtn");

    for (var i = 0; i < modelBtn.length; i++) {
        modelBtn[i].addEventListener('click', showModel, false);
    }

    function showModel() {
        document.getElementById("myCanvasNav").style.width = "100%";
        document.getElementById("myCanvasNav").style.opacity = "0.8";
        model.className = "open";
    }

    for (var i = 0; i < closeBtn.length; i++) {
        closeBtn[i].addEventListener('click', closeModel, false);
    }

    function closeModel() {
        document.getElementById("myCanvasNav").style.width = "0%";
        document.getElementById("myCanvasNav").style.opacity = "0";
        model.className = "close";
    }
    if (id !== null) {
        id.innerHTML = value;
        id.parentElement.nextElementSibling.setAttribute("value", value);
    }
}

var search = document.getElementById("index");

search.addEventListener("keypress", function(event) {
    // If the user presses the "Enter" key on the keyboard
    if (event.key === "Enter") {
        // Cancel the default action, if needed
        event.preventDefault();
        // Trigger the button element with a click
        document.getElementById("searchIndex").setAttribute("value", search.value);
        document.getElementById("searchForm").submit();
        console.log("Enter Clicked");
    }
});
