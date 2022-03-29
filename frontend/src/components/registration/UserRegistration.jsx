import React, { useRef, useState } from "react";
import { dataHandler } from "../../data/dataHandler";
import StringInput from "./StringInput";
import { useNavigate } from "react-router-dom";

const UserRegistration = ({ setLoginState }) => {
  let navigate = useNavigate();
  const errorContainer = useRef(null);
  const [errorContainerState, changeErrorState] = useState("hidden");
  const [errorMessage, changeMessage] = useState("");
  const [emailState, changeEmailValue] = useState({
    name: "email",
    type: "email",
    label: "Email:",
    placeholder: "example@myemail.com",
    required: "required",
  });
  const [passwordState, changePassword] = useState({
    name: "password",
    type: "password",
    label: "Password:",
    placeholder: "",
  });

  async function registerUser(event) {
    event.preventDefault();
    const email = event.target["email"].value;
    const password = event.target["password"].value;
    const payLoad = {
      email: email,
      password: password,
      required: "required",
    };
    const result = await dataHandler.registerUser(payLoad);
    if (result.result === "ok") {
      setLoginState({ logout: "", login: "hidden" });
      navigate("/cause-registration");
    } else {
      changeMessage(result.message);
      changeErrorState("");
    }
  }

  return (
    <form onSubmit={registerUser}>
      <div>
        <p ref={errorContainer} className={errorContainerState}>
          {errorMessage}
        </p>
      </div>
      <StringInput inputProps={emailState} />
      <StringInput inputProps={passwordState} />
      <button type="submit">Submit</button>
    </form>
  );
};

export default UserRegistration;
