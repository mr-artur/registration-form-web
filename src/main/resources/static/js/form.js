const form = document.getElementById("form");
const signInSuccess = document.getElementById("sign-in-success");
const signInError = document.getElementById("sign-in-error");
const signUpSuccess = document.getElementById("sign-up-success");
const signUpError = document.getElementById("sign-up-error");

const handleSubmit = e => {
    e.preventDefault();
    const inputs = form.getElementsByClassName("input");
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
        if (!(inputs[i] instanceof HTMLSelectElement)) {
            inputs[i].value = "";
        }
    }
}

const showResponseMessage = () => {
    return response => {
        const {status} = response;
        if (status == 200) {
            showTemporarily(signInSuccess);
        } else if (status == 201) {
            showTemporarily(signUpSuccess);
        } else if (status == 404) {
            showTemporarily(signInError);
        } else if (status == 409) {
            showTemporarily(signUpError);
        }
    };
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
    ).then(showResponseMessage());
}

const showTemporarily = alert => {
    show(alert);
    setTimeout(() => hide(alert), 2500);
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

if (signInSuccess) {
    hide(signInSuccess);
}

if (signInError) {
    hide(signInError);
}

if (signUpSuccess) {
    hide(signUpSuccess);
}

if (signUpError) {
    hide(signUpError);
}

if (form) {
    init();
}
