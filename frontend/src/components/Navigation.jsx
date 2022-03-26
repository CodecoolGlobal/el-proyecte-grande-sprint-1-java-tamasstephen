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
    <nav className="flex justify-center relative">
      <MenuDrawer menuState={drawerState} stateChanger={changeMenuState} />
      <div className="p-4 flex justify-between container mx-xl xl:px-[5rem] content-center">
        <div className="logo">
          <a href="/">DropCoin</a>
        </div>
        <ul className="hidden md:flex">
          <li className="pl-4">Explore</li>
          <li className="pl-4">Login</li>
          <li className="pl-4">Sign Up</li>
        </ul>
        <div
          className="flex items-center hamburger md:hidden"
          onClick={changeMenuState}
        >
          <HiMenu />
        </div>
      </div>
    </nav>
  );
};

export default Navigation;
