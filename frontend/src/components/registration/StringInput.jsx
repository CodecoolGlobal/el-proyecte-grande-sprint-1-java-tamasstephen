import React from "react";

const StringInput = ({ inputProps }) => {
  return (
    <div>
      <label htmlFor={`${inputProps["name"]}`}>{inputProps.label}</label>
      <input
        type={`${inputProps["type"]}`}
        name={`${inputProps["name"]}`}
        placeholder={`${inputProps["placeholder"]}`}
        required={inputProps["required"]}
      />
    </div>
  );
};

export default StringInput;
