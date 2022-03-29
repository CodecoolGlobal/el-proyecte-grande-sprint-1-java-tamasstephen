import React, { useState } from "react";
import CoinAmount from "./CoinAmount";
import SubHeadline from "../SubHeadline";
import InputField from "./InputField";
import Button from "./Button";

const DonationForm = () => {
  const [coinAmount, setCoinAmount] = useState(1);

  function handlerSubmit(event) {
    event.preventDefault();
    console.log(event.target["comment"].value);
    console.log(event.target["name"].value);
  }

  return (
    <>
      <SubHeadline name={"Drop Those Coins"} />
      <div>
        <CoinAmount amount={1} setCoinAmount={setCoinAmount} />
        <CoinAmount amount={5} setCoinAmount={setCoinAmount} />
        <CoinAmount amount={10} setCoinAmount={setCoinAmount} />
      </div>
      <form onSubmit={handlerSubmit}>
        <div>
          <InputField name={"name"} placeholder={"Name"} />
          <InputField name={"comment"} placeholder={"Comment.. (Optional)"} />
        </div>

        <p>I want to drop ${coinAmount} for the cause</p>
        <Button moneyAmount={coinAmount} />
      </form>
    </>
  );
};

export default DonationForm;
