import {registerEmailInputEle} from "../elements/register-email-input-ele.mjs";

export const validateRegisterEmailInput = (email) => {
    if (!email) {
        alert("이메일을 입력해주세요!");
        return false;
    }

    if (!email.includes("@")) {
        alert("이메일 형식이 아닙니다..");
        registerEmailInputEle.value = "";
        return false;
    }

    return true;
};
