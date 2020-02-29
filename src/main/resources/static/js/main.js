const loginForm = document.getElementById("loginForm");

const handleSubmit = e => {
    e.preventDefault();
    const serializedData = window.location.href.split("?")[1];
    sendForm(serializedData);
}

const sendForm = async data => {
    const response = await fetch(
        "/login",
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
    loginForm.addEventListener("submit", handleSubmit);
}

if (loginForm) {
    init();
}
