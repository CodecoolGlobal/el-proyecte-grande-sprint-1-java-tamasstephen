import React from "react";
import Cause from "../model/Cause";
import Headline from "../Headline";
import { dataHandler } from "../../data/dataHandler";
import { fetchImages, shortenDescription } from "../../utils/util";

const Explore = ({ causes, setCauses }) => {
  async function searchCause(event) {
    event.preventDefault();
    const newCauses = event.target["newCause"].value;
    const response = await dataHandler.getCausesByName(newCauses);
    response.forEach(
      (item) => (item.description = shortenDescription(item.description))
    );
    fetchImages(response, setCauses);
  }

  return (
    <div className="px-4 flex justify-center">
      <div className="max-w-7xl pt-8 md:pt-16">
        <Headline isTitle={true} title="Explore" />
        <div className="pt-4 md:pt-8">
          <form action="" onSubmit={searchCause}>
            <input
              type="text"
              name="newCause"
              placeholder="Search for Causes"
              className="text-xl md:text-4xl placeholder:text-slate-300 border-b-2 border-slate-500 bg-slate-100 focus:outline-none focus:border-indigo-500 focus:border-b-3"
            />
          </form>
        </div>
        <div className="flex flex-col md:grid md:grid-cols-2 lg:grid-cols-3 gap-4 md:container pt-4 md:pt-16 pb-20 md:pb-40">
          {causes.map((cause) => (
            <Cause key={parseInt(cause.userId)} cause={cause} />
          ))}
        </div>
      </div>
    </div>
  );
};

export default Explore;
