import React, { useEffect, useState } from "react";
import { dataHandler } from "../../data/dataHandler";

const ProfileWrapper = () => {
  const [userProfile, setUser] = useState({});

  useEffect(() => {
    const getUserProfile = async () => {
      return await dataHandler.getProfile();
    };

    getUserProfile().then((profile) => {
      setUser(profile);
    });
  }, []);

  return <div>ProfileWrapper</div>;
};

export default ProfileWrapper;
