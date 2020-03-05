const form = document.getElementById("form");
const alertSuccess = document.getElementById("alert-success");
const alertError = document.getElementById("alert-error");

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
    show(alertSuccess);
    setTimeout(() => hide(alertSuccess), 2500);
}

const showError = () => {
    show(alertError);
    setTimeout(() => hide(alertError), 2500);
}

const hide = element => {
    element.classList.add("hidden");
}

const show = element => {
    element.classList.remove("hidden");
}

const init = () => {
    form.addEventListener("submit", handleSubmit);
}

if (alertSuccess) {
    hide(alertSuccess);
}

if (alertError) {
    hide(alertError);
}

if (form) {
    init();
}
