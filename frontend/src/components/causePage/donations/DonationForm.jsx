import React, { useState } from "react";
import CoinAmount from "./CoinAmount";
import SubHeadline from "../SubHeadline";
import InputField from "./InputField";
import Button from "./Button";
import { useParams } from "react-router-dom";
import { dataHandler } from "../../../data/dataHandler";

const DonationForm = () => {
  const [coinAmount, setCoinAmount] = useState(1);
  const params = useParams();
  const [inputValue, setInputValue] = useState("");
  const [textAreaValue, setTextAreaValue] = useState("");

  return (
    <div className="bg-white mb-8 rounded-lg md:w-3/6 md:h-full flex flex-col items-center shadow-md shadow-indigo-300/20 ">
      <div className="px-4 py-8 w-11/12">
        <SubHeadline
          style={
            "text-xl md:text-3xl font-medium text-slate-500 pb-2 tracking-tighter"
          }
          name={"Drop Those Coins"}
        />
        <div className="grid grid-cols-3 gap-2 py-2">
          <CoinAmount amount={1} setCoinAmount={setCoinAmount} />
          <CoinAmount amount={5} setCoinAmount={setCoinAmount} />
          <CoinAmount amount={10} setCoinAmount={setCoinAmount} />
        </div>
        <form
          action={
            "http://localhost:8080/create-checkout-session/" +
            params.creatorLink +
            "/" +
            coinAmount
          }
          method="post"
        >
          <div>
            <div className="pb-2 pt-2">
              <InputField
                style={
                  "appearance-none border border-slate-300 rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-indigo-400 bg-slate-100"
                }
                name={"name"}
                placeholder={"Name"}
                valueSetter={setInputValue}
                inputValue={inputValue}
              />
            </div>
            <InputField
              style={
                "mb-4 h-20 appearance-none border border-slate-300 rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-indigo-400 bg-slate-100"
              }
              name={"comment"}
              placeholder={"Comment.. (Optional)"}
              valueSetter={setTextAreaValue}
              inputValue={textAreaValue}
            />
          </div>
          <p className="pb-2 text-slate-800">
            I want to drop <span className="font-bold">${coinAmount}</span> for
            the cause
          </p>
          <Button moneyAmount={coinAmount} />
        </form>
      </div>
    </div>
  );
};

export default DonationForm;
