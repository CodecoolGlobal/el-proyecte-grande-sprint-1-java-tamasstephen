import React, { useEffect, useState } from "react";
import Cause from "../model/Cause";
import Headline from "../Headline";

const Highlighted = () => {
  const [causes, setCause] = useState([]);
  useEffect(() => {
    const fetchCauses = async () => {
      const result = await fetch("http://localhost:8080/all-creators");
      return await result.json();
    };
    fetchCauses().then((data) => setCause(data));
  }, []);
  return (
    <div className="flex justify-center">
      <Headline isTitle={false} title="Highlighted Causes" />
    </div>
  );
};

export default Highlighted;
