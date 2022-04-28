import React, { useState } from "react";
import { Routes, Route } from "react-router-dom";
import Landing from "../components/landing/Landing";
import App from "../components/App";
import CausePage from "../components/causePage/CausePage";
import UserRegistration from "../components/registration/UserRegistration";
import CauseRegistration from "../components/registration/CauseRegistration";
import Login from "../components/registration/Login";
import ProfileWrapper from "../components/registration/ProfileWrapper";
import ExploreContainer from "../components/search/ExploreContainer";
import DonationSuccess from "../components/causePage/DonationSuccess";
import UserPage from "../components/UserPage";

const Router = () => {
  const [userLogin, setLoginState] = useState({
    logout: "hidden",
    login: "",
  });

  // 'hi'.length === 2 ? 'hello world' : 'test'
  
  const [adminState, setAdminState] = useState(
    localStorage.isAdmin === true
      ? { btnForAdmin: "", btnForUser: "hidden" }
      : { btnForAdmin: "hidden", btnForUser: "" }
  );

  // console.log(adminState);
  // console.log(adminState.btnForAdmin);
  // console.log(adminState.btnForUser);
  // console.log(adminState.btnForAdmin);

  return (
    <Routes>
      <Route
        path="/"
        element={<App setLoginState={setLoginState} userLogin={userLogin} adminState={adminState} />}
      >
        <Route index element={<Landing />} />
        <Route path=":creatorLink" element={<CausePage />} />
        <Route
          path="userEntity-registration"
          element={
            <UserRegistration
              setLoginState={setLoginState}
              userLogin={userLogin}
            />
          }
        ></Route>
        <Route path="cause-registration" element={<CauseRegistration />} />
        <Route path="success" element={<DonationSuccess />} />
        <Route path="login" element={<Login setLoginState={setLoginState} />} />
        <Route path="profile" element={<ProfileWrapper />} />
        <Route path="explore" element={<ExploreContainer />} />
        <Route path="explore/:searchParameter" element={<ExploreContainer />} />
        <Route path="users" element={<UserPage />} />
      </Route>
    </Routes>
  );
};

export default Router;
