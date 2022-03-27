import React from "react";

const Hero = () => {
  return (
    <div className="flex flex-col items-center pt-16 md:pt-32 text-indigo-900 pb-40 bg-gradient-to-b from-slate-100 via-slate-50 to-purple-50">
      <h1 className="text-center text-4xl font-bold tracking-tight md:text-5xl lg:text-6xl">
        <span className="text-purple-500">Support</span> your
        <br />
        favourite <span className="text-orange-500">caouses</span>
      </h1>
      <p className="text-center text-2xl tracking-tight pb-4 pt-4 md:text-3xl md:pt-6 md:pb-6 lg:text-4xl text-slate-700">
        Empower them with unlimited
        <br />
        freedom
      </p>
      <input
        type="text"
        className="h-10 rounded-full shadow-lg shadow-indigo-400/30 pl-4 border border-indigo-300 focus:outline-indigo-500 lg:h-12 lg:w-96"
      />
    </div>
  );
};

export default Hero;
