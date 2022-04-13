import React, { useState } from "react";
import ButtonMid from "../ButtonMid";
import Headline from "../Headline";
import { dataHandler } from "../../data/dataHandler";
import MutableInput from "./MutableInput";
import { HiOutlineMail } from "react-icons/hi";
import Error from "./Error";
import Description from "../causePage/Description";
import { MdOutlineTitle, MdDescription } from "react-icons/md";

const Profile = ({ userProfile, userUpdate, description, causeTitle }) => {
  // console.log(description);

  const [formVisibility, setFormVisiblity] = useState({ visible: "hidden" });

  const [descriptionFormVisibility, setDescriptionFormVisiblity] = useState({
    visible: "hidden",
  });

  const [causeTitleFormVisibility, setCauseTitleFormVisiblity] = useState({
    visible: "hidden",
  });

  const [buttonStateEmail, changeButtonStateEmail] = useState({
    label: "Edit",
    type: "button",
  });
  const [buttonStateTitle, changeButtonStateTitle] = useState({
    label: "Edit",
    type: "button",
  });
  const [buttonStateDescription, changeButtonStateDescription] = useState({
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

  const descriptionInput = {
    type: "description",
    name: "description",
    required: "required",
    label: "New Description",
    placeholder: "",
  };

  const causeTitleInput = {
    type: "causeTitle",
    name: "causeTitle",
    required: "required",
    label: "New Cause Title",
    placeholder: "",
  };

  const [errorState, setError] = useState({
    boxState: "hidden",
    text: "",
    textState: "invisible",
  });

  async function updateUserDetails(event, changeButtonState, buttonState, targetValue) {
    event.preventDefault();
    const updateMail = event.target[targetValue].value;
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

  function allowUpdateFields(
    setFormVisiblity,
    formVisibility,
    buttonState,
    changeButtonState
  ) {
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
            buttonState={buttonStateEmail}
            callBack={allowUpdateFields}
            secondary={true}
            updateFormFUnction={setFormVisiblity}
            formVisibility={formVisibility}
            changeButtonState={changeButtonStateEmail}
          />
        </div>
        <div className="pt-4 md:pt-8">
          <Error errorState={errorState} />
        </div>
        <form
          action=""
          onSubmit={(event) =>updateUserDetails(event, changeButtonStateEmail, buttonStateEmail, "email")}
          hidden={formVisibility.visible}
          className="pt-8"
        >
          <MutableInput inputProps={emailInput} />
          <div className="flex">
            <ButtonMid buttonState={submitButtonState} secondary={false} />
          </div>
        </form>
        <div className="md:flex justify-between pt-8">
          <div className="pb-4 md:pb-0 flex items-center">
            <MdOutlineTitle className="text-indigo-400 font-bold text-2xl md:text-3xl" />
            <p>{causeTitle.causeTitle}</p>
          </div>
          <ButtonMid
            buttonState={buttonStateTitle}
            callBack={allowUpdateFields}
            secondary={true}
            updateFormFUnction={setCauseTitleFormVisiblity}
            formVisibility={causeTitleFormVisibility}
            changeButtonState={changeButtonStateTitle}
          />
        </div>
        <form
          action=""
          onSubmit={(event) =>updateUserDetails(event, changeButtonStateTitle, buttonStateTitle, "causeTitle")}
          hidden={causeTitleFormVisibility.visible}
          className="pt-8"
        >
          <MutableInput inputProps={causeTitleInput} />
          <div className="flex">
            <ButtonMid buttonState={submitButtonState} secondary={false} />
          </div>
        </form>

        <div className="md:flex justify-between pt-8">
          <div className="pb-4 md:pb-0 flex items-center">
            <MdDescription className="text-indigo-400 font-bold text-2xl md:text-3xl" />
            <div className="w-4/5">
              <Description description={description.description} />
            </div>
          </div>
          <div >
            <div className="md:pt-32">
              <ButtonMid
              buttonState={buttonStateDescription}
              callBack={allowUpdateFields}
              secondary={true}
              updateFormFUnction={setDescriptionFormVisiblity}
              formVisibility={descriptionFormVisibility}
              changeButtonState={changeButtonStateDescription}
            />
            </div>
          </div>
        </div>
        <form
          action=""
          onSubmit={(event) =>updateUserDetails(event, changeButtonStateDescription, buttonStateDescription, "description")}
          hidden={descriptionFormVisibility.visible}
          className="pt-8"
        >
          <label
        className="pb-1 font-medium text-slate-600"
        htmlFor={`${descriptionInput["name"]}`}
      >
        {descriptionInput.label}
      </label>
          <textarea
            name="description"
            placeholder="The new cause description comes here..."
            minLength="400"
            rows="6"
            className="rounded-lg p-4 border border-indigo-200 w-full shadow-md shadow-indigo-400/20 hover:outline-indigo-500"
            required
          ></textarea>
          <div className="flex">
            <ButtonMid buttonState={submitButtonState} secondary={false} />
          </div>
        </form>
      </div>
    </div>
  );
};

export default Profile;
