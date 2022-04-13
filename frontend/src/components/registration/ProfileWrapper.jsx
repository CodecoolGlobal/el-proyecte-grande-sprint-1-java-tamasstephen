import React, { useEffect, useState } from "react";
import { dataHandler } from "../../data/dataHandler";
import Profile from "./Profile";

const ProfileWrapper = () => {
  const [userProfile, updateUser] = useState({ email: "" });
  const [description, updateDescription] = useState({description: ""});
  const [causeTitle, updateCauseTitle] = useState({causeTitle: ""});

  useEffect(() => {
    const getUserProfile = async () => {
      return await dataHandler.getProfile();
    };

    getUserProfile().then((profile) => {
      console.log(profile);
      updateUser({ email: profile.userEntity.email });
      updateDescription({description: profile.profile.description})
      updateCauseTitle({causeTitle: profile.profile.causeName})
    });
  }, []);

  return <Profile userProfile={userProfile} description={description} causeTitle={causeTitle} userUpdate={updateUser}
          updateCauseTitle={updateCauseTitle} descriptionUpdate={updateDescription} />;
};

export default ProfileWrapper;
