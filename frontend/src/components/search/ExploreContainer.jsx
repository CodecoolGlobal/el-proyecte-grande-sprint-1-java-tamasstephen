import React, { useEffect } from "react";
import { useParams } from "react-router-dom";
import { dataHandler } from "../../data/dataHandler";

const ExploreContainer = () => {
  const { searchParameter } = useParams();
  console.log(searchParameter);

  useEffect(() => {
    const getCauses = async () => {
      if (searchParameter) {
        return await dataHandler.getCausesByName(searchParameter);
      } else {
        return await dataHandler.getAllCauses();
      }
    };
    getCauses().then((data) => console.log(data));
  }, []);

  return <div>ExploreContainer</div>;
};

export default ExploreContainer;
