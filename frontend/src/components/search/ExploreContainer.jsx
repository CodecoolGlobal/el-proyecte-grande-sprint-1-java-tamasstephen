import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { dataHandler } from "../../data/dataHandler";
import Explore from "./Explore";
import { fetchImages, shortenDescription } from "../../utils/util";

const ExploreContainer = () => {
  const { searchParameter } = useParams();
  const [causes, setCauses] = useState([]);

  useEffect(() => {
    const getCauses = async () => {
      console.log("here");
      if (searchParameter) {
        return await dataHandler.getCausesByName(searchParameter);
      } else {
        return await dataHandler.getAllCauses();
      }
    };
    getCauses(setCauses)
      .then((data) => {
        data.forEach((cause) => {
          cause.description = shortenDescription(cause.description);
        });
        setCauses(data);
        return data;
      })
      .then((data) => fetchImages(data, setCauses));
  }, []);

  return <Explore causes={causes} setCauses={setCauses} />;
};

export default ExploreContainer;
