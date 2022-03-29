import React from "react";
import Headline from "../Headline";
import StringInput from "./StringInput";
import { dataHandler } from "../../data/dataHandler";
import { useNavigate } from "react-router-dom";

const Login = ({ setLoginState }) => {
  const navigate = useNavigate();

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
    console.log(response);
    handleLogin(response);
  }

  function handleLogin(response) {
    if (response.result === "ok") {
      setLoginState({ logout: "", login: "hidden" });
      navigate("/");
    } else {
      alert(response.message);
    }
  }

  return (
    <form onSubmit={sendLogin}>
      <Headline isTitle={false} title="Login" />
      <StringInput inputProps={email} />
      <StringInput inputProps={password} />
      <button type="submit">Login</button>
    </form>
  );
};

export default Login;
