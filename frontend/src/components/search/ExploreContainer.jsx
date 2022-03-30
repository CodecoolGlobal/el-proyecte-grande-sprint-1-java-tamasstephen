import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { dataHandler } from "../../data/dataHandler";
import Explore from "./Explore";
import { fetchImages } from "../../utils/util";

const ExploreContainer = () => {
  const { searchParameter } = useParams();
  const [causes, setCauses] = useState([]);

  useEffect(() => {
    const getCauses = async (callback) => {
      if (searchParameter) {
        return await dataHandler.getCausesByName(searchParameter);
      } else {
        return await dataHandler.getAllCauses();
      }
    };
    getCauses(setCauses)
      .then((data) => {
        console.log(data);
        setCauses(data);
        return data;
      })
      .then((data) => fetchImages(data, setCauses));
  }, []);

  return <Explore causes={causes} />;
};

export default ExploreContainer;
