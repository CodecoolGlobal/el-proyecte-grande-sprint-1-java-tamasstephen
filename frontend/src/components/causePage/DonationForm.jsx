import React from "react";
import CoinAmount from "./CoinAmount";
import SubHeadline from "./SubHeadline";

const DonationForm = () => {
  return (
    <>
      <SubHeadline name={"Drop Those Coins"} />
      <div>
        <CoinAmount amount={1} />
        <CoinAmount amount={5} />
        <CoinAmount amount={10} />
      </div>
    </>
  );
};

export default DonationForm;
