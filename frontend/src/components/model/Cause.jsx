import React from "react";
import CardTitle from "../CardTitle";
import Tag from "../Tag";

const Cause = ({ cause }) => {
  const category = cause?.category ? cause.category : "general";
  return (
    <div className="rounded-lg bg-white overflow-hidden shadow-lg shadow-indigo-500/30 hover:shadow-2xl hover:shadow-orange-500/30 hover:-translate-y-1 transition-all ease-in duration-200">
      <img
        src={cause?.imageLink}
        alt=""
        className="w-full  aspect-video object-cover"
      />
      <div className="p-4">
        <CardTitle title={cause?.causeName} />
        <p className="text-slate-700 pb-2 h-12">{cause?.description}</p>
        <div>
          <Tag title={category} />
        </div>
      </div>
    </div>
  );
};

export default Cause;
