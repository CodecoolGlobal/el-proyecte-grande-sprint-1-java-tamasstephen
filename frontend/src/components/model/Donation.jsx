import React from "react";
import { BsCoin } from "react-icons/bs";

const Donation = ({ donator, amount, comment, time }) => {
  const cardTime = time ? time : "Just now...";
  return (
    <div className="flex mb-2 p-4 bg-white rounded-lg w-full shadow-md shadow-indigo-300/40">
      <div className="pt-1 pr-3">
        <BsCoin className="text-3xl text-indigo-500 font-bold" />
      </div>
      <div>
        <h3 className="text-xl md:text-2xl text-indigo-900">
          <span className="font-bold">{donator}</span> dropped {amount}{" "}
          {Number(amount) > 1 ? "coins" : "coin"}
        </h3>
        <p className="text-slate-700 py-1">{comment}</p>
        <p className="text-slate-500">{cardTime}</p>
      </div>
    </div>
  );
};

export default Donation;
