import React from "react";

const StringInput = ({ inputProps }) => {
  return (
    <div className="flex flex-col pb-4 last:pb-0">
      <label
        className="pb-1 font-medium text-slate-600"
        htmlFor={`${inputProps["name"]}`}
      >
        {inputProps.label}
      </label>
      <input
        type={`${inputProps["type"]}`}
        name={`${inputProps["name"]}`}
        placeholder={`${inputProps["placeholder"]}`}
        required={inputProps["required"]}
        className="rounded-lg h-10 px-4 border border-indigo-200 shadow-md shadow-indigo-400/20 hover:outline-indigo-500"
        value={inputProps["value"]}
      />
    </div>
  );
};

export default StringInput;
