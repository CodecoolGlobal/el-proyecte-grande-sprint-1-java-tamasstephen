import React from "react";

const CoinAmount = ({ amount, setCoinAmount }) => {
  const coinText = amount === 1 ? "coin" : "coins";

  return (
    <button onClick={() => setCoinAmount(amount)}>
      {amount} {coinText}
    </button>
  );
};

export default CoinAmount;
