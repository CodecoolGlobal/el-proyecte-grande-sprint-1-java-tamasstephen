import React from "react";
import Headline from "../Headline";
import ReasonBox from "./ReasonBox";
import { usePageSectionReveal } from "../../utils/customHooks";

const Reasons = () => {
  const options = {
    root: null,
    rootMargin: "0px",
    threshold: 0.5,
  };

  const [wrapper, isVisible] = usePageSectionReveal(options);

  return (
    <section className="bg-gradient-to-b from-slate-200 via-indigo-100 to-slate-200 px-4 md:px-0 pb-20 md:pb-40">
      <div
        ref={wrapper}
        className={
          "flex flex-col items-center transition-all duration-500 ease-in " +
          isVisible
        }
      >
        <div className="flex justify-center pb-6">
          <Headline isTitle={false} title={"Your support matters!"} />
        </div>
        <div>
          <ReasonBox
            text="Provides emotional support"
            donator="Becca"
            comment="The greatest of all time"
            position="even"
            amount="10"
          />
          <ReasonBox
            text="Help people and make a dream come true"
            donator="George"
            comment="The greatest of all time"
            amount="1"
          />
          <ReasonBox
            text="Show some love to your fellow people!"
            donator="Cynthia"
            position="even"
            amount="5"
          />
        </div>
      </div>
    </section>
  );
};

export default Reasons;
