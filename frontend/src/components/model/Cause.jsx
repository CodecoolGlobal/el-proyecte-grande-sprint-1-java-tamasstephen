import React from "react";

const Cause = ({ cause }) => {
  const category = cause?.category ? cause.category : "general";
  return (
    <div>
      <img src={cause?.imageLink} alt="" />
      <div>
        <h3>{cause?.causeName}</h3>
        <p>{cause?.description}</p>
        <div>
          <span>{category}</span>
        </div>
      </div>
    </div>
  );
};

export default Cause;
