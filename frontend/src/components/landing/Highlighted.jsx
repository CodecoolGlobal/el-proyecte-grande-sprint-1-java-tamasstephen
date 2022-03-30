import React, { useEffect, useState } from "react";
import Cause from "../model/Cause";
import Headline from "../Headline";
import { shortenDescription } from "../../utils/util.js";
import { dataHandler } from "../../data/dataHandler";
import { fetchImages } from "../../utils/util.js";

const Highlighted = () => {
  const [causes, setCause] = useState([]);

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
    <div className="flex flex-col justify-center items-center px-4 md:py-0 pb-20 md:pb-40">
      <div className="flex justify-center pb-4 md:pb-12">
        <Headline isTitle={false} title="Highlighted Causes" />
      </div>
      <div className="flex flex-col md:grid grid-flow-col gap-4 container mx-xl">
        {causes.map((cause, index) => {
          return <Cause key={index} cause={cause} />;
        })}
      </div>
    </div>
  );
};

export default Highlighted;
