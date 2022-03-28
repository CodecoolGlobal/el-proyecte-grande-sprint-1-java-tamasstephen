import React, { useEffect, useState } from "react";
import Cause from "../model/Cause";
import Headline from "../Headline";
import { shortenDescription } from "../../utils/util.js";

const Highlighted = () => {
  const [causes, setCause] = useState([]);

  useEffect(() => {
    const fetchCauses = async () => {
      const result = await fetch("http://localhost:8080/highlighted");
      return await result.json();
    };

    fetchCauses().then((data) => {
      const myData = [...data];
      myData.forEach((data) => {
        data.description = shortenDescription(data.description);
      });
      setCause(data);
    });
  }, []);

  return (
    <div className="flex flex-col justify-center">
      <div className="flex justify-center">
        <Headline isTitle={false} title="Highlighted Causes" />
      </div>
      <div>
        {causes.map((cause, index) => (
          <Cause
            key={index}
            name={causes[index]?.causeName}
            description={causes[index]?.description}
            category={causes[index]?.category}
          />
        ))}
      </div>
    </div>
  );
};

export default Highlighted;
