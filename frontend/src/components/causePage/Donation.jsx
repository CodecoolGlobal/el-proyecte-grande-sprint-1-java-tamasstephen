import React from "react";
import CoinAmount from "./CoinAmount";
import SubHeadline from "./SubHeadline";

const Donation = () => {
  return (
    <>
    <SubHeadline name={"Drop Those Coins"}/>
      <CoinAmount amount={2} />
    </>
  );
};

export default Donation;
