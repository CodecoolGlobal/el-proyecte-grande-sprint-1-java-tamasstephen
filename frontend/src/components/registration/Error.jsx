import React from "react";

const Error = ({ errorState }) => {
  return (
    <div className={errorState.boxState}>
      <p
        className={
          errorState.textState +
          " p-4 bg-red-100 text-red-600 rounded-lg border-2 border-red-400"
        }
      >
        {errorState.text}
      </p>
    </div>
  );
};

export default Error;
