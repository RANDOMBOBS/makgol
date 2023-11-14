const {callValidateEmailApi} = require("../../backend/call-validate-email-api.js");
const {registerEmailInputEle} = require("../../elements/register-email-input-ele.js");
const {validateRegisterEmailInput} = require("../../logic/validate-register-email-input.mjs");

export const emailValidateButton = () => {
    const emailValidateButtonEle = document.querySelector("#email_validate");

    emailValidateButtonEle.addEventListener("click", async (e) => {
        e.preventDefault();

        const email = registerEmailInputEle.value;
        const result = validateRegisterEmailInput(email)

        if (!result) return

        callValidateEmailApi(email)
            .then(() => {
                alert("사용가능한 이메일입니다!")
            })
            .catch(() => {
                alert("중복된 이메일입니다..")
                registerEmailInputEle.value = "";
            })
    });
};

