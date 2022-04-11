import React from "react";

const CoinAmount = ({ amount, setCoinAmount }) => {
  const coinText = amount === 1 ? "coin" : "coins";

  return (
    <button
      className="bg-indigo-100 hover:bg-orange-400 hover:text-white text-indigo-700 font-bold py-2 px-4 rounded"
      onClick={() => setCoinAmount(amount)}
    >
      <div className="flex flex-row">
        <p className="">
          {amount} {coinText}
        </p>
      </div>
    </button>
  );
};

export default CoinAmount;
