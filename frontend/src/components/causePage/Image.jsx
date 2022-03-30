import React from "react";

const Image = (props) => {
  return (
    <img
      className={props.style}
      src={require(`${props.jpgName}`)}
      alt=""
    ></img>
  );
};

export default Image;
