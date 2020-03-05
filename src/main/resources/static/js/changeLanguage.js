const languageButtons = document.getElementsByClassName("btn-language");

const changeLanguage = e => {
    const { target: { value } } = e;
    const { location : { pathname } } = window;
    window.location.replace(pathname + "?lang=" + value);
}

if (languageButtons) {
    for (let i = 0; i < languageButtons.length; i++) {
        languageButtons[i].addEventListener("click", changeLanguage);
    }
}
