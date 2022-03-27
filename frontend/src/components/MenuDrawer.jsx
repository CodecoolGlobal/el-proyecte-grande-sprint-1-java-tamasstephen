import React from "react";
import { HiX } from "react-icons/hi";

const MenuDrawer = ({ menuState, stateChanger }) => {
  function closeModal() {
    stateChanger();
  }

  return (
    <div
      className={
        menuState +
        " h-screen w-screen absolute flex items-center justify-center bg-yellow-100 transition-all duration-500 ease-in-out delay-75 z-50"
      }
    >
      <div className="absolute top-6 right-6" onClick={closeModal}>
        <HiX className="text-xl" />
      </div>
      <ul className="text-slate-700 text-3xl font-bold">
        <li>Explore</li>
        <li className="py-4">Login</li>
        <li>Sign Up</li>
      </ul>
    </div>
  );
};

export default MenuDrawer;
