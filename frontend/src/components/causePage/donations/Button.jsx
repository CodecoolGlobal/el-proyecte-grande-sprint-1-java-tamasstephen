import React from "react";

const Button = ({ moneyAmount }) => {
  return (
    <button
      className="w-11/12 mb-2 mt-2 bg-gray-800 hover:bg-gray-600 text-white font-bold py-2 px-4 rounded mr-1 ml-2"
      type="submit"
    > <p className="">
      Pay ${moneyAmount}
    </p>
      
    </button>
  );
};

export default Button;
