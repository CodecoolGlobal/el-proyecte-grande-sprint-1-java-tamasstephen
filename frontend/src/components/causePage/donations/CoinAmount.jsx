import React from "react";

const CoinAmount = ({ amount, setCoinAmount }) => {
  const coinText = amount === 1 ? "coin" : "coins";

  return (
    <button className="bg-gray-500 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded mr-1 ml-2" onClick={() => setCoinAmount(amount)}>
      <div className="flex flex-row">
        {amount} <p className="">{coinText}</p>
      </div>
      
    </button>
  );
};

export default CoinAmount;
