import {modalCoverEle} from "../../elements/modal-cover-ele.mjs";
import {loginModalEle} from "../../elements/login-modal-ele.mjs";
import {closeButton} from "./close-button.mjs";

export const loginModalButton = () => {
    const loginButtonEle = document.querySelector(".login_modal_button");

    loginButtonEle.addEventListener("click", () => {
        modalCoverEle.style.display = "block";
        loginModalEle.style.display = "block";
    });

    closeButton({modal: "login"});
};
