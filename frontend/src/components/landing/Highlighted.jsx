import React, { useEffect, useState } from "react";
import Cause from "../model/Cause";
import Headline from "../Headline";
import { shortenDescription } from "../../utils/util.js";
import { dataHandler } from "../../data/dataHandler";
import { fetchImages } from "../../utils/util.js";
import { usePageSectionReveal } from "../../utils/customHooks";

const Highlighted = () => {
  const [causes, setCause] = useState([]);

  const options = {
    root: null,
    rootMargin: "0px",
    threshold: 0.5,
  };

  const [wrapper, isVisible] = usePageSectionReveal(options);
  const [cards, areVisible] = usePageSectionReveal(options);

  useEffect(() => {
    const fetchCauses = async () => {
      return await dataHandler.getHighlights();
    };

    fetchCauses().then((data) => {
      const myData = [...data];
      myData.forEach((data) => {
        data.description = shortenDescription(data.description);
      });
      fetchImages(myData, setCause);
    });
  }, []);

  return (
    <div className="flex flex-col justify-center items-center px-4 md:py-0 pb-20 md:pb-40 bg-gradient-to-b from-slate-200">
      <div
        ref={wrapper}
        className={
          "flex justify-center pb-4 md:pb-12 transition-all duration-500 ease-in " +
          isVisible
        }
      >
        <Headline isTitle={false} title="Highlighted Causes" />
      </div>
      <div
        ref={cards}
        className={
          "flex flex-col md:grid grid-flow-col gap-4 container mx-xl  transition-all duration-500 ease-in " +
          areVisible
        }
      >
        {causes.map((cause, index) => {
          return <Cause key={index} cause={cause} />;
        })}
      </div>
    </div>
  );
};

export default Highlighted;
