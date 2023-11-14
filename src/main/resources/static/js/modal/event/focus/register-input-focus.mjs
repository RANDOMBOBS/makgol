import {registerInputEles} from "../../elements/register-input-eles.mjs";

export const registerInputFocus = () => {
    registerInputEles.forEach((ele) => {
        const inputBorderEle = ele.parentNode.parentNode;

        ele.addEventListener("focus", () => {
            inputBorderEle.style.border = "2px solid #c7a669";
        });

        ele.addEventListener("blur", () => {
            inputBorderEle.style.border = "2px solid #ffe286";
        });
    });
};
