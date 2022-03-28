import React, { useState } from "react";
import CoinAmount from "./CoinAmount";
import SubHeadline from "../SubHeadline";
import InputField from "./InputField";
import Button from "./Button";

const DonationForm = () => {

  const [name, setName] = useState('');

  const [comment, setComment] = useState('');

  const [coinAmount, setCoinAmount] = useState(1);

  return (
    <>
      <SubHeadline name={"Drop Those Coins"} />
      <div>
        <CoinAmount amount={1} />
        <CoinAmount amount={5} />
        <CoinAmount amount={10} />
      </div>
      <InputField setstate={setName} placeholder={'Add your name'}/>
      <InputField setstate={setComment} placeholder={'Add a comment if you want'}/>
      <p>I want to drop $ {coinAmount} for the cause</p>
      <Button/>
    </>
  );
};

export default DonationForm;
