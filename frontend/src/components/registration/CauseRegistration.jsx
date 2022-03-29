import React from "react";
import { dataHandler } from "../../data/dataHandler";
import StringInput from "./StringInput";
import { useNavigate } from "react-router-dom";

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
      alert(response.message);
    }
  }

  return (
    <div>
      <form action="" onSubmit={sendForm}>
        <StringInput inputProps={causeInput} />
        <StringInput inputProps={pageLink} />
        <div>
          <label htmlFor="category">Category:</label>
          <select name="category" id="category">
            <option value="charity">Charity</option>
            <option value="ecology">Ecology</option>
            <option value="science">Science</option>
            <option value="music">Music</option>
            <option value="other">Other</option>
          </select>
        </div>
        <div>
          <label htmlFor="description">Description</label>
          <textarea
            name="description"
            placeholder="The cause description comes here..."
            required
          ></textarea>
        </div>
        <div>
          <label htmlFor="upload">Image:</label>
          <input type="file" accept="image/png, image/jpeg" name="upload" />
        </div>
        <button type="submit">Submit</button>
      </form>
    </div>
  );
};

export default CauseRegistration;
