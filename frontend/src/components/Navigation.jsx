import React, { useState } from "react";
import { HiMenu } from "react-icons/hi";
import MenuDrawer from "./MenuDrawer";
import { Link } from "react-router-dom";

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
          <Link
            to="/"
            className="text-xl font-bold text-indigo-900 tracking-tighter md:text-2xl"
          >
            CoinDrop
          </Link>
        </div>
        <ul className="hidden md:flex text-indigo-900 font-bold tracking-tight">
          <li className="pl-7">Explore</li>
          <li className="pl-7">
            <Link to="/user-registration">Login</Link>
          </li>
          <li className="pl-7">Create a Cause</li>
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
