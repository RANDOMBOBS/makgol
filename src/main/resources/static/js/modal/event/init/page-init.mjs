import {buttonEventInit} from "./button-event-init.mjs";
import {keyupEventInit} from "./keyup-event-init.mjs";
import {focusEventInit} from "./focus-event-init.mjs";

const pageInit = () => {
    const inits = [buttonEventInit, keyupEventInit, focusEventInit];
    inits.forEach((init) => init());
};

window.addEventListener("load", pageInit);
