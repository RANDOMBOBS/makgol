import {registerNameInputEle} from "../../elements/register-name-input-ele.mjs";
import {validateInputLength} from "../../logic/validate-input-length.mjs";

export const nameInputKeyup = () => {
    registerNameInputEle.addEventListener(
        "keyup",
        validateInputLength.registerName,
    );
};
