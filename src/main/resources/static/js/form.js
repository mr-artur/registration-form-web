const form = document.getElementById("form");

const handleSubmit = e => {
    e.preventDefault();
    const data = serializeData();
    const { dataset: { url } } = form;
    sendForm(url, data);
}

const serializeData = () => {
    const inputs = form.getElementsByTagName("input");
    const parameters = [];

    for (let i = 0; i < inputs.length; i++) {
        parameters[i] = inputs[i].name + "=" + inputs[i].value ;
    }
    return parameters.join("&");
}

const sendForm = async (url, data) => {
    const response = await fetch(
        url,
        {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: data
        }
    );
    console.log(response);
}

const init = () => {
    form.addEventListener("submit", handleSubmit);
}

if (form) {
    init();
}
