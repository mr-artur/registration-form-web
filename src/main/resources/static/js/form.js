const container = document.getElementById("mainContainer");
const form = document.getElementById("form");

const handleSubmit = e => {
    e.preventDefault();
    const inputs = form.getElementsByTagName("input");
    const data = serializeData(inputs);
    resetInputs(inputs);
    const {dataset: {url}} = form;
    sendForm(url, data);
}

const serializeData = inputs => {
    const parameters = [];

    for (let i = 0; i < inputs.length; i++) {
        parameters[i] = inputs[i].name + "=" + inputs[i].value;
    }
    return parameters.join("&");
}

const resetInputs = inputs => {
    for (let i = 0; i < inputs.length; i++) {
        inputs[i].value = "";
    }
}

const sendForm = (url, data) => {
    fetch(
        url,
        {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: data
        }
    ).then(
        data => showSuccess(),
        error => showError()
    );
}

const showSuccess = () => {
    const alert = buildAlert("success", "Data submit was successful");
    container.appendChild(alert);
    setTimeout(() => container.removeChild(alert), 2500);
}

const buildAlert = (type, message) => {
    const div = document.createElement("div");
    div.innerText = message;
    div.classList.add("alert", `alert-${type}`, "mt-4");
    div.style.margin = "auto";
    div.style.maxWidth = "250px";
    return div;
}

const showError = () => {
    const alert = buildAlert("danger", "Error in data submitting");
    container.appendChild(alert);
    setTimeout(() => container.removeChild(alert), 2500);
}

const init = () => {
    form.addEventListener("submit", handleSubmit);
}

if (form) {
    init();
}
