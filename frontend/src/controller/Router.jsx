import React from "react";
import { Routes, Route } from "react-router-dom";
import Landing from "../components/landing/Landing";
import App from "../components/App";
import UserRegistration from "../components/registration/UserRegistration";
import CauseRegistration from "../components/registration/CauseRegistration";

const Router = () => {
  return (
    <Routes>
      <Route path="/" element={<App />}>
        <Route index element={<Landing />} />
        <Route path="user-registration" element={<UserRegistration />} />
        <Route path="cause-registration" element={<CauseRegistration />} />
      </Route>
    </Routes>
  );
};

export default Router;
