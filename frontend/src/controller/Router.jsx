import React, { useState } from "react";
import { Routes, Route } from "react-router-dom";
import Landing from "../components/landing/Landing";
import App from "../components/App";
import CausePage from "../components/causePage/CausePage";
import UserRegistration from "../components/registration/UserRegistration";
import CauseRegistration from "../components/registration/CauseRegistration";
import Login from "../components/registration/Login";


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
        <Route path="create-cause" element={<CausePage/>}/>
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
      </Route>
    </Routes>
  );
};

export default Router;
