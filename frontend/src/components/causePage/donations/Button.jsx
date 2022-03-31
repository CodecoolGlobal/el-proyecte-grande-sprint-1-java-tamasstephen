import React from "react";

const Button = ({ moneyAmount }) => {
  return (
    <button
      className="w-full bg-indigo-700 shadow-indigo-500/30 hover:shadow-lg hover:shadow-indigo-500/50 hover:-translate-y-0.5 hover:bg-indigo-900 transition-all duration-200 text-white font-bold py-2 px-4 rounded"
      type="submit"
    >
      {" "}
      <p className="">Pay ${moneyAmount}</p>
    </button>
  );
};

export default Button;
