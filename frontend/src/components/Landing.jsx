import React from "react";

const Landing = () => {
  return (
    <div className="flex flex-col items-center pt-16 text-sky-900 pb-40 bg-gradient-to-b from-slate-100 via-sky-50 to-slate-200">
      <h1 className="text-center text-4xl font-bold tracking-tight md:text-5xl lg:text-6xl">
        Support your
        <br />
        favourite cause
      </h1>
      <p className="text-center text-2xl font-medium tracking-tighter pb-4 pt-4 md:text-3xl md:pt-6 md:pb-6 lg:text-4xl">
        Empower them with unlimited
        <br />
        freedom
      </p>
      <input
        type="text"
        className="h-10 rounded-full drop-shadow-[0_8px_8px_rgba(0,0,0,0.15)] pl-4 focus:outline-sky-600 lg:h-12 lg:w-96"
      />
    </div>
  );
};

export default Landing;
