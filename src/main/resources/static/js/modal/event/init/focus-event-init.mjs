import {registerInputFocus} from "../focus/register-input-focus.mjs";

export const focusEventInit = () => {
    const focusEvent = [registerInputFocus];

    focusEvent.forEach((func) => func());
};
