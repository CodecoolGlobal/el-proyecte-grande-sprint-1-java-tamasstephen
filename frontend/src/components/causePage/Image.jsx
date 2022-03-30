import React from "react";

const Image = (props) => {
  return (
    <img
      className={props.style}
      src={`${props.jpgName}`}
      alt=""
    ></img>
  );
};

export default Image;
