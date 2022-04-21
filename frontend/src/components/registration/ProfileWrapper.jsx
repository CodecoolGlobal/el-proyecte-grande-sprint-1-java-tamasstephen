import React, { useEffect, useState } from "react";
import { dataHandler } from "../../data/dataHandler";
import Profile from "./Profile";

const ProfileWrapper = () => {
  const [userProfile, updateUser] = useState({ email: "" });
  const [description, updateDescription] = useState({description: ""});
  const [title, updateCauseTitle] = useState({title: ""});

  useEffect(() => {
    const getUserProfile = async () => {
      return await dataHandler.getProfile();
    };

    getUserProfile().then((profile) => {
      console.log(profile);
      updateUser({ email: profile.userEntity.email });
      updateDescription({description: profile.profile.description})
      updateCauseTitle({title: profile.profile.causeName})
    });
  }, []);

  return <Profile userProfile={userProfile} description={description} title={title} userUpdate={updateUser}
          updateCauseTitle={updateCauseTitle} descriptionUpdate={updateDescription} />;
};

export default ProfileWrapper;
