import React from "react";

const ButtonMid = ({ buttonState, callBack, secondary }) => {
  const style = secondary
    ? "bg-orange-50 border-2 border-indigo-400 shadow-md shadow-indgo-400/30 text-indigo-700"
    : "bg-indigo-700 shadow-md shadow-indigo-400/30 text-white";

  return (
    <button
      className={"py-1 px-6 font-medium rounded-lg " + style}
      onClick={callBack}
      type={buttonState.type}
    >
      {buttonState.label}
    </button>
  );
};

export default ButtonMid;
