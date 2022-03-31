import React from "react";
import Donation from "../../model/Donation";

const Donations = ({ items }) => {
  console.log(items);
  return (
    <ul>
      {items?.map((tip, index) => (
        <Donation
          key={index}
          donator={tip.supporter}
          amount={tip.amount}
          comment={tip.comment}
          time={tip.date}
        />
      ))}
    </ul>
  );
};

export default Donations;
