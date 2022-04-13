import React from "react";
import Donation from "../../model/Donation";

const Donations = ({ items }) => {
  console.log(items);
  return (
    <ul className="flex flex-col gap-2 lg:gap-4">
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
