import React, { useRef } from "react";
import { dataHandler } from "../data/dataHandler";

const UserRegistration = () => {
  const email = useRef(null);
  const password = useRef(null);

  async function registerUser(event) {
    event.preventDefault();
    const email = event.target["email"].value;
    const password = event.target["password"].value;
    const payLoad = {
      email: email,
      password: password,
    };
    const result = await dataHandler.registerUser(payLoad);
    console.group(result);
  }

  return (
    <form onSubmit={registerUser}>
      <div>
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
