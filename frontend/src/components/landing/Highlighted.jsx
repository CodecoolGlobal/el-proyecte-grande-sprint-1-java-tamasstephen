import React, { useEffect, useState } from "react";
import Cause from "../model/Cause";
import Headline from "../Headline";
import { shortenDescription } from "../../utils/util.js";
import { dataHandler } from "../../data/dataHandler";

const Highlighted = () => {
  const [causes, setCause] = useState([]);

  useEffect(() => {
    const fetchCauses = async () => {
      return await dataHandler.getHighlights();
    };

    const fetchImages = async (listOfCauses) => {
      const newCauses = [];
      for (const cause of listOfCauses) {
        const cardImageBlob = await dataHandler.getImageForCard(cause.pageLink);
        const imageUrl = URL.createObjectURL(cardImageBlob);
        const myCause = { ...cause };
        myCause["imageLink"] = imageUrl;
        newCauses.push(myCause);
      }
      console.log(newCauses);
      setCause(() => [...newCauses]);
    };

    fetchCauses().then((data) => {
      const myData = [...data];
      myData.forEach((data) => {
        data.description = shortenDescription(data.description);
      });
      fetchImages(myData);
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
