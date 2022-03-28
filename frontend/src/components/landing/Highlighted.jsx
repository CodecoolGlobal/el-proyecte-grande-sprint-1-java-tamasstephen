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
    <div className="flex flex-col justify-center">
      <div className="flex justify-center">
        <Headline isTitle={false} title="Highlighted Causes" />
      </div>
      <div>
        {causes.map((cause, index) => {
          console.log(cause.imageLink);
          return <Cause key={index} cause={cause} />;
        })}
      </div>
    </div>
  );
};

export default Highlighted;
