import React, { useState } from "react";
import ButtonMid from "../ButtonMid";
import Headline from "../Headline";
import { dataHandler } from "../../data/dataHandler";
import MutableInput from "./MutableInput";
import { HiOutlineMail } from "react-icons/hi";
import Error from "./Error";

const Profile = ({ userProfile, userUpdate }) => {
  const [formVisibility, setFormVisiblity] = useState({ visible: "hidden" });

  const [buttonState, changeButtonState] = useState({
    label: "Edit",
    type: "button",
  });

  const submitButtonState = {
    label: "Update",
    type: "submit",
  };

  const emailInput = {
    type: "email",
    name: "email",
    required: "required",
    label: "New Email",
    placeholder: "",
  };

  const [errorState, setError] = useState({
    boxState: "hidden",
    text: "",
    textState: "invisible",
  });

  async function updateUserDetails(event) {
    event.preventDefault();
    const updateMail = event.target["email"].value;
    const payLoad = { email: updateMail };
    const result = await dataHandler.updateEmail(payLoad);
    if (result.result === "ok") {
      const updateState = { ...userProfile, email: result.email };
      userUpdate(updateState);
      setFormVisiblity({ ...formVisibility, visible: "hidden" });
      changeButtonState({ ...buttonState, label: "Edit" });
      setError({ boxState: "hidden", text: "", textState: "invisible" });
    } else {
      setError({ boxState: "", text: result.message, textState: "" });
    }
  }

  function allowUpdateFields() {
    formVisibility.visible === "hidden"
      ? setFormVisiblity({ ...formVisibility, visible: "" })
      : setFormVisiblity({ ...formVisibility, visible: "hidden" });
    buttonState.label === "Edit"
      ? changeButtonState({
          ...buttonState,
          label: "Cancel",
        })
      : changeButtonState({ ...buttonState, label: "Edit" });
  }

  return (
    <div className="px-4 md:px-0 md:flex md:flex-col md:items-center pt-8 md:pt-16">
      <div className="md:w-8/12 md:max-w-3xl">
        <Headline title="Profile" isTitle={true} />
        <div className="md:flex justify-between pt-8">
          <div className="pb-4 md:pb-0 flex items-center">
            <HiOutlineMail className="text-indigo-400 font-bold text-2xl md:text-3xl" />
            <p className="font-medium text-lg md:text-2xl text-indigo-900 pl-1">
              {userProfile.email}
            </p>
          </div>
          <ButtonMid
            buttonState={buttonState}
            callBack={allowUpdateFields}
            secondary={true}
          />
        </div>
        <div className="pt-4 md:pt-8">
          <Error errorState={errorState} />
        </div>
        <form
          action=""
          onSubmit={updateUserDetails}
          hidden={formVisibility.visible}
          className="pt-8"
        >
          <MutableInput inputProps={emailInput} />
          <div className="flex">
            <ButtonMid buttonState={submitButtonState} secondary={false} />
          </div>
        </form>
        <div>
          <form action=""></form>
        </div>
      </div>
    </div>
  );
};

export default Profile;
