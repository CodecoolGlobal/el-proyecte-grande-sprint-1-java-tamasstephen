import React from "react";
import Headline from "../Headline";

const DonationSuccess = () => {
  return (
    <div className="flex flex-col justify-center items-center pt-8 md:pt-16">
      <Headline isTitle={false} title="Thank you for your donation" />
      <img
        src="./success.png"
        alt="happy girl on a swing"
        className="w-3/4 md:w-1/4"
      />
      <a href="/">
        <p className="text-indigo-500 hover:text-indigo-800 font-bold text-xl hover:underline underline-offset-4">
          Back to homepage
        </p>
      </a>
    </div>
  );
};

export default DonationSuccess;
