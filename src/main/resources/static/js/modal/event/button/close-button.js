const {registerModalEle} = require("../../elements/register-modal-ele.js");
const {modalCoverEle} = require("../../elements/modal-cover-ele.js")

export const closeButton = ({modal}) => {
    if (modal === "login") {
        document
            .querySelector(".login_modal .modal_head .close_button")
            .addEventListener("click", () => {
                modalCoverEle.style.display = "none";
            });
    } else {
        document
            .querySelector(".register_modal .modal_head .close_button")
            .addEventListener("click", () => {
                modalCoverEle.style.display = "none";
                registerModalEle.style.display = "none";
            });
    }
};
