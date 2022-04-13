import React from "react";
import Donation from "../model/Donation";

const ReasonBox = ({ text, donator, comment, amount, position }) => {
  const textColor = position === "even" ? "text-purple-700" : "text-orange-500";
  return (
    <div className="w-full flex flex-col md:flex-row border-t-2 items-center border-slate-300 pb-8 md:pb-0 md:py-14 md:border-none">
      <div className={" w-full flex justify-center"}>
        <p
          className={
            "py-6 text-2xl font-bold text-center lg:tracking-tight md:text-4xl md:w-10/12 " +
            textColor
          }
        >
          {text}
        </p>
      </div>
      <div className={"w-full flex justify-center md:"}>
        <div className={" w-10/12"}>
          <Donation donator={donator} comment={comment} amount={amount} />
        </div>
      </div>
    </div>
  );
};

export default ReasonBox;
