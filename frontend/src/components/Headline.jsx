import React from "react";

const Headline = ({ isTitle, title, isLight }) => {
  const color = isLight ? "text-slate-100 " : "text-indigo-900";
  const classes =
    "text-3xl w-8/12 md:w-max md:text-4xl lg:text-5xl text-indigo-900 font-bold tracking-tighter xl:max-w-screen-lg " +
    color;
  return isTitle ? (
    <h1 className={classes}>{title}</h1>
  ) : (
    <h2 className={classes + " text-center"}>{title}</h2>
  );
};

export default Headline;
