import React, { useState } from "react";
import ButtonMid from "../ButtonMid";
import Headline from "../Headline";
import { dataHandler } from "../../data/dataHandler";
import MutableInput from "./MutableInput";
import { HiOutlineMail } from "react-icons/hi";
import Error from "./Error";
import Description from "../causePage/Description";
import { MdOutlineTitle, MdDescription } from "react-icons/md";
import { data } from "autoprefixer";

const Profile = ({
  userProfile,
  userUpdate,
  description,
  descriptionUpdate,
  title,
  updateCauseTitle,
}) => {
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
    type: "title",
    name: "title",
    required: "required",
    label: "New Cause Title",
    placeholder: "",
  };

  const [errorState, setError] = useState({
    boxState: "hidden",
    text: "",
    textState: "invisible",
  });

  async function updateUserDetails(
    event,
    changeButtonState,
    buttonState,
    targetValue,
    userUpdate,
    formVisibility,
    setFormVisiblity
  ) {
    event.preventDefault();
    const updateMail = event.target[targetValue].value;
    const payLoad = setPayLoadByName(targetValue, updateMail);
    const result = await apiCallDecider(targetValue, payLoad);

    let updateState = updateStateByName(targetValue, result);
    if (result.result === "ok") {
      userUpdate(updateState);
      setFormVisiblity({ ...formVisibility, visible: "hidden" });
      changeButtonState({ ...buttonState, label: "Edit" });
      setError({ boxState: "hidden", text: "", textState: "invisible" });
    } else {
      setError({ boxState: "", text: result.message, textState: "" });
    }
  }

  function setPayLoadByName(name, updateMail) {
    return { [name]: updateMail };
  }

  function updateStateByName(name, result) {
    switch (name) {
      case "email":
        return { ...userProfile, email: result.email };
      case "title":
        return { ...title, title: result.title };
      case "description":
        return { ...description, description: result.description };
    }
  }

  async function apiCallDecider(name, payload) {
    switch (name) {
      case "email":
        return await dataHandler.updateEmail(payload);
      case "title":
        return await dataHandler.updateCauseTitle(payload);
      case "description":
        return await dataHandler.updateCauseDescription(payload);
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
          onSubmit={(event) =>
            updateUserDetails(
              event,
              changeButtonStateEmail,
              buttonStateEmail,
              "email",
              userUpdate,
              formVisibility,
              setFormVisiblity
            )
          }
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
            <p>{title.title}</p>
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
          onSubmit={(event) =>
            updateUserDetails(
              event,
              changeButtonStateTitle,
              buttonStateTitle,
              "title",
              updateCauseTitle,
              causeTitleFormVisibility,
              setCauseTitleFormVisiblity
            )
          }
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
          <div>
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
          onSubmit={(event) =>
            updateUserDetails(
              event,
              changeButtonStateDescription,
              buttonStateDescription,
              "description",
              descriptionUpdate,
              descriptionFormVisibility,
              setDescriptionFormVisiblity
            )
          }
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
