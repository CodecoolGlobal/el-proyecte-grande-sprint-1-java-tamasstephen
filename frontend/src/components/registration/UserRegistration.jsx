import React, { useRef, useState } from "react";
import { dataHandler } from "../../data/dataHandler";
import StringInput from "./StringInput";
import { useNavigate } from "react-router-dom";
import Error from "./Error";
import Headline from "../Headline";
import Submit from "./Submit";

const UserRegistration = ({ setLoginState }) => {
  let navigate = useNavigate();
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
  const [errorState, setError] = useState({
    boxState: "hidden",
    text: "",
    textState: "invisible",
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
      setError({ boxState: "", text: result.message, textState: "" });
    }
  }

  return (
    <div className="flex flex-col items-center pt-16">
      <form
        onSubmit={registerUser}
        className="px-4 md:w-1/2 max-w-xl flex flex-col justify-center"
      >
        <Headline isTitle={true} title="Register" />
        <div className={errorState.boxState + " pt-4 md:pt-8"}>
          <Error errorState={errorState} />
        </div>
        <div className="pt-4 md:pt-8 pb-4 md:pb-8">
          <StringInput inputProps={emailState} />
          <StringInput inputProps={passwordState} />
        </div>
        <Submit title="Submit" />
      </form>
    </div>
  );
};

export default UserRegistration;
