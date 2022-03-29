import React from "react";

const Submit = ({ title }) => {
  return (
    <button
      type="submit"
      className="py-2 px-6 bg-indigo-700 font-bold text-slate-100 rounded-lg shadow shadow-indigo-500/30 hover:shadow-lg hover:shadow-indigo-500/50 hover:-translate-y-0.5 hover:bg-indigo-900 transition-all duration-200"
    >
      {title}
    </button>
  );
};

export default Submit;
