import React from "react";

const User = ({ user }) => {
  return (
    <div >
      <li className="mb-4 font-medium text-lg md:text-xl text-indigo-900 pl-1 rounded-md	">{user}</li>
    </div>
  );
};

export default User;
