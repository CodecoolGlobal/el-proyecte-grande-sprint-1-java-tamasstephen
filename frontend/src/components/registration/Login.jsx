import React from "react";
import Headline from "../Headline";
import StringInput from "./StringInput";

const Login = () => {
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

  function handleLogin(event) {
    event.preventDefault();
    const email = event.target["email"].value;
    const password = event.target["password"].value;
    console.log(email, password);
  }

  return (
    <form onSubmit={handleLogin}>
      <Headline isTitle={false} title="Login" />
      <StringInput inputProps={email} />
      <StringInput inputProps={password} />
      <button type="submit">Login</button>
    </form>
  );
};

export default Login;
