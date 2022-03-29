import React, { useState } from "react";
import { Routes, Route } from "react-router-dom";
import Landing from "../components/landing/Landing";
import App from "../components/App";
import UserRegistration from "../components/registration/UserRegistration";
import CauseRegistration from "../components/registration/CauseRegistration";

const Router = () => {
  const [userLogin, setLoginState] = useState({
    logout: "hidden",
    login: "",
  });

  return (
    <Routes>
      <Route
        path="/"
        element={<App setLoginState={setLoginState} userLogin={userLogin} />}
      >
        <Route index element={<Landing />} />
        <Route
          path="user-registration"
          element={
            <UserRegistration
              setLoginState={setLoginState}
              userLogin={userLogin}
            />
          }
        ></Route>
        <Route path="cause-registration" element={<CauseRegistration />} />
      </Route>
    </Routes>
  );
};

export default Router;
