import React from "react";
import { useNavigate } from "react-router-dom";

const Hero = () => {

  const navigate = useNavigate();
  function listenForSearch(event) {
    if (event.key === "Enter") {
      const searchPhrase = event.target.value;
      navigate(`/explore/${searchPhrase}`);
    }
  }
  return (
    <div className="flex flex-col items-center pt-16 md:pt-32 xl:pt-44 text-indigo-900 pb-40 bg-gradient-to-b from-slate-100 via-slate-50 to-purple-50">
      <h1 className="text-center text-4xl font-bold tracking-tight md:text-5xl lg:text-6xl xl:text-7xl">
        <span className="text-purple-500">Support</span> your
        <br />
        favourite <span className="text-orange-500">cause</span>
      </h1>
      <p className="text-center text-xl tracking-tight xl:tracking-tighter xl:leading-snug pb-4 pt-4 md:text-3xl xl:text-4xl md:pt-6 md:pb-6  text-slate-600">
        Drop a coin for a cause and
        <br />
        make a dream come true
      </p>
      <input
        type="text"
        placeholder="Find your cause"
        className="h-10 rounded-full shadow-lg shadow-indigo-400/30 pl-4 border border-indigo-300 focus:outline-indigo-500 lg:h-12 lg:w-96"
        onKeyDown={listenForSearch}
      />
    </div>
  );
};

export default Hero;
