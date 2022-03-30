import React from "react";
import Cause from "../model/Cause";

const Explore = ({ causes }) => {
  return (
    <div>
      <div>
        {causes.map((cause) => (
          <Cause key={parseInt(cause.userId)} cause={cause} />
        ))}
      </div>
    </div>
  );
};

export default Explore;
