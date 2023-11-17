const {axios} = window;

exports.callValidateEmailApi = (email) => {
    return axios.post("http://localhost:8090/user/mailCheck", {email});
};

