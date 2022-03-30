import React, { useState } from "react";
import { dataHandler } from "../../data/dataHandler";
import StringInput from "./StringInput";
import { useNavigate } from "react-router-dom";
import Headline from "../Headline";
import Error from "./Error";
import Submit from "./Submit";

const CauseRegistration = () => {
  const navigate = useNavigate();
  const causeInput = {
    name: "causeName",
    type: "text",
    label: "Cause name:",
    placeholder: "Charity for Pete's playground",
    required: "required",
  };

  const pageLink = {
    name: "pageLink",
    type: "text",
    label: "Cause page link:",
    placeholder: "pete_playground",
    required: "required",
  };
  const [errorState, setError] = useState({
    boxState: "hidden",
    text: "",
    textState: "invisible",
  });

  async function sendForm(event) {
    event.preventDefault();
    const formData = new FormData();
    const file = event.target["upload"].files[0];
    formData.append("name", event.target["causeName"].value);
    formData.append("description", event.target["description"].value);
    formData.append("pageLink", event.target["pageLink"].value);
    formData.append("category", event.target["category"].value);
    formData.append("file", file);
    const response = await dataHandler.postMultiPartForm(formData);
    if (response.result === "ok") {
      navigate("/");
    } else {
      setError({ boxState: "", text: response.message, textState: "" });
    }
  }

  return (
    <div className="flex flex-col items-center pt-16">
      <form
        action=""
        onSubmit={sendForm}
        className="px-4 md:w-1/2 max-w-xl flex flex-col justify-center"
      >
        <Headline isTitle={true} title="Create a cause" />
        <div className={errorState.boxState + " pt-4 md:pt-8"}>
          <Error errorState={errorState} />
        </div>
        <div className="pt-4 md:pt-8 pb-4 md:pb-8">
          <StringInput inputProps={causeInput} />
          <StringInput inputProps={pageLink} />
        </div>
        <div className="flex flex-col">
          <label
            htmlFor="category"
            className=" font-medium text-slate-600 pb-1"
          >
            Category:
          </label>
          <select
            name="category"
            id="category"
            className="w-40 py-2 px-4 rounded-lg font-medium text-slate-700 border border-indigo-300  shadow-md shadow-indigo-400/20"
          >
            <option value="charity">Charity</option>
            <option value="ecology">Ecology</option>
            <option value="science">Science</option>
            <option value="music">Music</option>
            <option value="other">Other</option>
          </select>
        </div>
        <div className="flex flex-col pt-4">
          <label
            htmlFor="description"
            className="font-medium text-slate-600 pb-1"
          >
            Description
          </label>
          <textarea
            name="description"
            placeholder="The cause description comes here..."
            minLength="400"
            rows="6"
            className="rounded-lg p-4 border border-indigo-200 shadow-md shadow-indigo-400/20 hover:outline-indigo-500"
            required
          ></textarea>
        </div>
        <div className="flex flex-col pt-4 pb-4 md:pb-8">
          <label htmlFor="upload" className="font-medium text-slate-600 pb-1">
            Image:
          </label>
          <input
            type="file"
            accept="image/png, image/jpeg"
            name="upload"
            className="rounded-lg pr-4"
          />
        </div>
        <Submit title="Submit" />
      </form>
    </div>
  );
};

export default CauseRegistration;
