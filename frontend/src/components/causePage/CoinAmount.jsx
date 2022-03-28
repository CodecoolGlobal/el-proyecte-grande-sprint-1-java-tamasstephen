import React from 'react'

const CoinAmount = (props) => {
    // let buttonContent = props.amount == 1 ? <p>{props.amount} coin</p>
    const coinText = props.amount == 1 ? "coin" : "coins";
  return (
    <button>{props.amount} {coinText}</button>
  )
}

export default CoinAmount