import React from "react";
import Headline from "../Headline";
import Button from "../Button";

const Join = () => {
  return (
    <div className="py-4 md:p-0 pb-20 md:pb-40 flex justify-center  px-4 md:px-0">
      <div className="flex flex-col justify-center items-center bg-slate-900 md:w-8/12 2xl:container  rounded-lg py-4 md:py-12">
        <div className="w-10/12, md:w-1/2 flex flex-col justify-center items-center px-4 gap-4 md:gap-8">
          <Headline
            isTitle={false}
            title="Start to collect for a cause now!"
            isLight={true}
          />
          <Button label="Create a cause" isLight={true} />
        </div>
      </div>
    </div>
  );
};

export default Join;
