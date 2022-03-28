import React from "react";

const CardTitle = ({ title }) => {
  return (
    <h3 className="font-bold text-lg text-indigo-900 hover:text-indigo-500">
      {title}
    </h3>
  );
};

export default CardTitle;
