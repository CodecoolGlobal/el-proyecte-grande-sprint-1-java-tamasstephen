import React from "react";

const Cause = ({ name, description, category }) => {
  return (
    <div>
      <img src="" alt="" />
      <div>
        <h3>{name}</h3>
        <p>{description}</p>
        <span>{category}</span>
      </div>
    </div>
  );
};

export default Cause;
