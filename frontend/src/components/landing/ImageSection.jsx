import React from "react";
import Headline from "../Headline";
import Lead from "./Lead";

const ImageSection = () => {
  return (
    <section className="flex justify-center bg-gradient-to-b from-slate-200 via-slate-300 to-late-100">
      <div className="md:w-1/2 flex flex-col items-center px-4 md:px-0">
        <Headline isTitle={false} title="Join the thousands of supporters" />
        <Lead text="And help an important cause to prosper" />
        <div className="px-8 md:px-0">
          <img
            src="./volunteer.jpg"
            className="w-max rounded-lg aspect-video object-cover object-center"
            alt="Charity foundraiser"
          />
        </div>
      </div>
    </section>
  );
};

export default ImageSection;
