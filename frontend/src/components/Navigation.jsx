import React, { useState } from "react";
import { HiMenu } from "react-icons/hi";
import MenuDrawer from "./MenuDrawer";

export const Navigation = () => {
  const [drawerState, changeState] = useState("translate-x-full invisible");

  function changeMenuState() {
    const newState =
      drawerState === "translate-x-full invisible"
        ? "left-null translate-x-0"
        : "translate-x-full invisible";
    changeState(newState);
  }

  return (
    <nav className="flex justify-center relative ">
      <MenuDrawer menuState={drawerState} stateChanger={changeMenuState} />
      <div className="p-4 flex justify-between container mx-xl xl:px-[5rem] content-center ">
        <div className="logo">
          <a
            href="/"
            className="text-xl font-bold text-slate-500 tracking-tighter md:text-2xl"
          >
            CoinDrop
          </a>
        </div>
        <ul className="hidden md:flex text-slate-600 font-bold text-lg tracking-tight">
          <li className="pl-5">Explore</li>
          <li className="pl-5">Login</li>
          <li className="pl-5">Sign Up</li>
        </ul>
        <div
          className="flex items-center hamburger md:hidden"
          onClick={changeMenuState}
        >
          <HiMenu className="text-xl text-stone-700" />
        </div>
      </div>
    </nav>
  );
};

export default Navigation;