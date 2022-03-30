import React from "react";

const Tag = ({ title }) => {
  return (
    <div className="py-1 px-2 inline-block bg-orange-100 rounded-lg uppercase text-sm">
      {title}
    </div>
  );
};

export default Tag;
