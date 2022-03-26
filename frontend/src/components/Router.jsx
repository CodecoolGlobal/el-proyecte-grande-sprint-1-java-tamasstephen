import React from "react";
import { Routes, Route } from "react-router-dom";
import Landing from "./Landing";
import App from "./App";

const Router = () => {
  return (
    <Routes>
      <Route path="/" element={<App />}>
        <Route index element={<Landing />} />
      </Route>
    </Routes>
  );
};

export default Router;
