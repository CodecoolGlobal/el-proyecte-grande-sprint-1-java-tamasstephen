import React, { useState } from "react";
import CoinAmount from "./CoinAmount";
import SubHeadline from "../SubHeadline";
import InputField from "./InputField";
import Button from "./Button";
import { useParams } from "react-router-dom";
import { dataHandler } from "../../../data/dataHandler";

const DonationForm = ({ setTip}) => {
  const [coinAmount, setCoinAmount] = useState(1);
  const params = useParams();

  async function handlerSubmit(event) {
    event.preventDefault();
    const payload = {
      amount: coinAmount,
      pageLink: params.creatorLink,
      supporter: event.target["name"].value,
      comment: event.target["comment"].value,
    };
    await dataHandler.sendTextJson("creator/support", payload);
    dataHandler.getTipsByCreatorLink(params.creatorLink).then((tips) => {
      setTip(tips);
    });
  }

  return (
    <div className="bg-white mb-8 rounded md:w-3/6 md:h-full flex flex-col items-center">
      <div className="p-4 w-11/12">
        <SubHeadline
          style={"text-xl md:text-2xl font-bold text-indigo-900"}
          name={"Drop Those Coins"}
        />
        <div className="grid grid-cols-3 gap-2 py-2">
          <CoinAmount amount={1} setCoinAmount={setCoinAmount} />
          <CoinAmount amount={5} setCoinAmount={setCoinAmount} />
          <CoinAmount amount={10} setCoinAmount={setCoinAmount} />
        </div>
        <form onSubmit={handlerSubmit}>
          <div>
            <div className="pb-2 pt-2">
              <InputField
                style={
                  "shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                }
                name={"name"}
                placeholder={"Name"}
              />
            </div>
            <InputField
              style={
                "mb-4 h-20 shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
              }
              name={"comment"}
              placeholder={"Comment.. (Optional)"}
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
