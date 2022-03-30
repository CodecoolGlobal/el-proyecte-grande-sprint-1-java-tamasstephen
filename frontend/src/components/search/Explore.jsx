import React from "react";
import Cause from "../model/Cause";
import Headline from "../Headline";

const Explore = ({ causes }) => {
  return (
    <div className="px-4 flex justify-center">
      <div className="max-w-7xl pt-8 md:pt-16">
        <Headline isTitle={true} title="Explore" />
        <div className="flex flex-col md:grid md:grid-cols-2 lg:grid-cols-3 gap-4 md:container pt-4 md:pt-16">
          {causes.map((cause) => (
            <Cause key={parseInt(cause.userId)} cause={cause} />
          ))}
        </div>
      </div>
    </div>
  );
};

export default Explore;
