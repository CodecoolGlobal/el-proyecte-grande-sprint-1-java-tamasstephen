import React from "react";

const Headline = ({ isTitle, title, titleFollow }) => {
  const classes =
    "text-3xl w-8/12 md:w-max md:text-4xl lg:text-5xl text-indigo-900 font-bold tracking-tighter";
  return isTitle ? (
    <h1 className={classes}>
      {title}
      <br />
      {titleFollow}
    </h1>
  ) : (
    <h2 className={classes + " text-center"}>
      {title}
      <br />
      {titleFollow}
    </h2>
  );
};

export default Headline;
