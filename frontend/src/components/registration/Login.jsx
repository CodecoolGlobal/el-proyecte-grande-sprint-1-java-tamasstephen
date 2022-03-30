import React, { useState } from "react";
import Headline from "../Headline";
import StringInput from "./StringInput";
import { dataHandler } from "../../data/dataHandler";
import { useNavigate } from "react-router-dom";
import Submit from "./Submit";
import Error from "./Error";

const Login = ({ setLoginState }) => {
  const navigate = useNavigate();
  const [errorState, setError] = useState({
    boxState: "hidden",
    text: "",
    textState: "invisible",
  });

  const email = {
    name: "email",
    type: "email",
    label: "Email:",
    placeholder: "example@myemail.com",
    required: "required",
  };
  const password = {
    name: "password",
    type: "password",
    label: "Password:",
    placeholder: "",
    required: "required",
  };

  async function sendLogin(event) {
    event.preventDefault();
    const email = event.target["email"].value;
    const password = event.target["password"].value;
    const payLoad = {
      email: email,
      password: password,
    };
    const response = await dataHandler.login(payLoad);
    handleLoginResponse(response);
  }

  async function handleLoginResponse(response) {
    if (response.result === "ok") {
      setLoginState({ logout: "", login: "hidden" });
      const contentResponse = await dataHandler.isCreatorProfileSet();
      if (contentResponse.result === "ok") {
        navigate("/");
      } else {
        navigate("/cause-registration");
      }
    } else {
      setError({ boxState: "", text: response.message, textState: "" });
    }
  }

  return (
    <div className="flex flex-col items-center pt-16">
      <form
        className="px-4 md:w-1/2 max-w-xl flex flex-col justify-center"
        onSubmit={sendLogin}
      >
        <Headline isTitle={true} title="Login" />
        <div className={errorState.boxState + " pt-4 md:pt-8"}>
          <Error errorState={errorState} />
        </div>
        <div className="pt-4 md:pt-8 pb-4 md:pb-8">
          <StringInput inputProps={email} />
          <StringInput inputProps={password} />
        </div>
        <Submit title="Submit" />
      </form>
    </div>
  );
};

export default Login;
