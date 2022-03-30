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

  function handlerSubmit(event) {
    event.preventDefault();
    const payload = {
      amount: coinAmount,
      pageLink: params.creatorLink,
      supporter: event.target["name"].value,
      comment: event.target["comment"].value,
    };
    dataHandler.sendTextJson("creator/support", payload);
    dataHandler
      .getTipsByCreatorLink(params.creatorLink)
      .then((tips) => setTip(tips));
  }

  return (
    <div className="bg-slate-100 mt-10 mx-3 mb-8 rounded md:w-3/6 md:h-full md:mt-4">
      <SubHeadline style={"text-xl ml-2"} name={"Drop Those Coins"} />
      <div>
        <CoinAmount amount={1} setCoinAmount={setCoinAmount} />
        <CoinAmount amount={5} setCoinAmount={setCoinAmount} />
        <CoinAmount amount={10} setCoinAmount={setCoinAmount} />
      </div>
      <form onSubmit={handlerSubmit}>
        <div>
          <InputField
            style={
              "ml-2 mb-2 mt-2 w-11/12 shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
            }
            name={"name"}
            placeholder={"Name"}
          />
          <InputField
            style={
              "ml-2 mb-4 w-11/12 h-20 shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
            }
            name={"comment"}
            placeholder={"Comment.. (Optional)"}
          />
        </div>
        <p className="ml-2">I want to drop ${coinAmount} for the cause</p>
        <Button moneyAmount={coinAmount} />
      </form>
    </div>
  );
};

export default DonationForm;
