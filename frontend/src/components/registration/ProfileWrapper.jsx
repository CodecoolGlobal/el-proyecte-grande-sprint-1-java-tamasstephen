import React, { useEffect, useState } from "react";
import { dataHandler } from "../../data/dataHandler";
import Profile from "./Profile";

const ProfileWrapper = () => {
  const [userProfile, updateUser] = useState({ email: "" });

  useEffect(() => {
    const getUserProfile = async () => {
      return await dataHandler.getProfile();
    };

    getUserProfile().then((profile) => {
      console.log(profile);
      updateUser({ email: profile.userEntity.email });
    });
  }, []);

  return <Profile userProfile={userProfile} userUpdate={updateUser} />;
};

export default ProfileWrapper;
