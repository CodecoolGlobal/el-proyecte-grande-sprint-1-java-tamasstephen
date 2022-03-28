import React from 'react'

const CoinAmount = (props) => {
    const coinText = props.amount == 1 ? "coin" : "coins";
  return (
    <button>{props.amount} {coinText}</button>
  )
}

export default CoinAmount