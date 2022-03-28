import React from "react";
import { Routes, Route } from "react-router-dom";
import Landing from "../components/landing/Landing";
import App from "../components/App";
import UserRegistration from "../components/UserRegistration";

const Router = () => {
  return (
    <Routes>
      <Route path="/" element={<App />}>
        <Route index element={<Landing />} />
        <Route path="user-registration" element={<UserRegistration />} />
      </Route>
    </Routes>
  );
};

export default Router;
