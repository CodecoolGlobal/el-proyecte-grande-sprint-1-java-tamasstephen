import React from "react";
import Description from "./Description";
import DonationForm from "./donations/DonationForm";
import Image from "./Image";
import SubHeadline from "./SubHeadline";

const CausePage = () => {
  return (
    <>
      <SubHeadline name={"Help Konoha"} />
      <Image jpgName={"./Konohafalu.jpg"} />
      <Description />
      <DonationForm/>
    </>
  );
};

export default CausePage;
