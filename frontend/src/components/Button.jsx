import React from "react";

const Button = ({ label, isLight }) => {
  const color = isLight
    ? "bg-purple-50 shadow-lg shadow-orange-300/20 text-slate-800 hover:shadow-2xl hover:shadow-orange-500/30 hover:-translate-y-1 hover:text-indigo-600 transition:all duration-200"
    : "bg-purple-600";
  return (
    <button
      className={color + " w-full py-2 px-4 rounded-lg font-bold md:text-xl"}
    >
      {label}
    </button>
  );
};

export default Button;
