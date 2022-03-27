import React from "react";

const Lead = ({ text }) => {
  return (
    <p className="text-xl md:text-3xl text-slate-700 text-center pt-3 pb-8 lg:py-6">
      {text}
    </p>
  );
};

export default Lead;
