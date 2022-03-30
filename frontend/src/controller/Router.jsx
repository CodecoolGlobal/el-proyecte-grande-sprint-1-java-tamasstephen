import React, { useState } from "react";
import { Routes, Route } from "react-router-dom";
import Landing from "../components/landing/Landing";
import App from "../components/App";
import UserRegistration from "../components/registration/UserRegistration";
import CauseRegistration from "../components/registration/CauseRegistration";
import Login from "../components/registration/Login";
import ProfileWrapper from "../components/registration/ProfileWrapper";

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
        <Route path="login" element={<Login setLoginState={setLoginState} />} />
        <Route path="profile" element={<ProfileWrapper />} />
      </Route>
    </Routes>
  );
};

export default Router;
