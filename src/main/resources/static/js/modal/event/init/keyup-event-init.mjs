import {loginInputKeyup} from "../keyup/login-input-keyup.mjs";
import {registerInputKeyup} from "../keyup/register-input-keyup.mjs";
import {nameInputKeyup} from "../keyup/name-input-keyup.mjs";

export const keyupEventInit = () => {
    const keyupEvents = [loginInputKeyup, registerInputKeyup, nameInputKeyup];

    keyupEvents.forEach((func) => func());
};
