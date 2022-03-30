import React from "react";
import Description from "./Description";
import DonationForm from "./donations/DonationForm";
import Image from "./Image";
import SubHeadline from "./SubHeadline";
import Donation from "../model/Donation";
import Headline from "../Headline";

const CausePage = () => {
  return (
    <div className="bg-gray-200 md:flex md:flex-col md:items-center md:flex-wrap">
      <div className=" px-4 md:w-8/12 ">
        <Headline title={"Help Konoha"} isTitle={true} />
        <Image style={"m-3 rounded-lg w-350 h-400 md:w-full md:h-650 md:pt-3 md:m-0"} jpgName={"./Konohafalu.jpg"} />
        <div className="fley flex-col md:flex md:flex-row">
          <Description />
          <DonationForm />
        </div>
        <SubHeadline style={"text-3xl mb-4"} name={"Latest donators"} />
        <div className="md:w-6/12">
          <Donation
          donator={"Jani"}
          amount={5}
          comment={"GOOOD MORNING"}
          time={5}
        />
        </div>
        
      </div>
    </div>
  );
};

export default CausePage;
