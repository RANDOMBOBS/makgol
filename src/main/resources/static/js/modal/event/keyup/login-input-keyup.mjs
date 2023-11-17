import {loginEmailInputEle} from "../../elements/login-email-input-ele.mjs";
import {loginPasswordInputEle} from "../../elements/login-password-input-ele.mjs";
import {validateInputLength} from "../../logic/validate-input-length.mjs";

export const loginInputKeyup = () => {
    loginEmailInputEle.addEventListener("keyup", validateInputLength.loginEmail);
    loginPasswordInputEle.addEventListener(
        "keyup",
        validateInputLength.loginPassword,
    );
};
