import {emailValidateButton} from "../button/email-validate-button.mjs";
import {loginModalButton} from "../button/login-modal-button.mjs";
import {registerModalButton} from "../button/register-modal-button.mjs";
import {registerSubmitButton} from "../button/register-submit-button.mjs";

export const buttonEventInit = () => {
    const buttonEvents = [
        loginModalButton,
        emailValidateButton,
        registerModalButton,
        registerSubmitButton,
    ];

    buttonEvents.forEach((func) => func());
};
