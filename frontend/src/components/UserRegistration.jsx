import React, { useRef, useState } from "react";
import { dataHandler } from "../data/dataHandler";
import { handleFormResponse } from "../utils/util";

const UserRegistration = () => {
  const email = useRef(null);
  const password = useRef(null);
  const errorContainer = useRef(null);
  const [errorContainerState, changeErrorState] = useState("hidden");
  const [errorMessage, changeMessage] = useState("");

  async function registerUser(event) {
    event.preventDefault();
    const email = event.target["email"].value;
    const password = event.target["password"].value;
    const payLoad = {
      email: email,
      password: password,
    };
    const result = await dataHandler.registerUser(payLoad);
    handleFormResponse(result, "/", changeErrorState, changeMessage);
  }

  return (
    <form onSubmit={registerUser}>
      <div>
        <p ref={errorContainer} className={errorContainerState}>
          {errorMessage}
        </p>
        <label htmlFor="email">Email:</label>
        <input ref={email} type="email" name="email" />
      </div>
      <div>
        <label htmlFor="password">Password:</label>
        <input ref={password} type="password" name="password" />
      </div>
      <button type="submit">Submit</button>
    </form>
  );
};

export default UserRegistration;
