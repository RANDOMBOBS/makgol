import {loginModalEle} from "../../elements/login-modal-ele.mjs";
import {closeButton} from "./close-button.mjs";
import {registerModalEle} from "../../elements/register-modal-ele.mjs";

export const registerModalButton = () => {
    const registerModalButtonEle = document.querySelector(
        ".user_option > span:nth-child(1)",
    );

    registerModalButtonEle.addEventListener("click", () => {
        loginModalEle.style.display = "none";
        registerModalEle.style.display = "block";
    });

    closeButton({modal: "register"});
};
