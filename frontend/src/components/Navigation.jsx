import React, { useState } from "react";
import { HiMenu } from "react-icons/hi";
import MenuDrawer from "./MenuDrawer";
import { Link } from "react-router-dom";
import { dataHandler } from "../data/dataHandler";

export const Navigation = ({ userLogin, setLoginState }) => {
  const [drawerState, changeState] = useState("translate-x-full invisible");

  function changeMenuState() {
    const newState =
      drawerState === "translate-x-full invisible"
        ? "left-null translate-x-0"
        : "translate-x-full invisible";
    changeState(newState);
  }

  async function logout() {
    await dataHandler.logout();
    setLoginState({ logout: " hidden", login: "" });
  }

  return (
    <nav className="flex justify-center relative ">
      <MenuDrawer
        menuState={drawerState}
        stateChanger={changeMenuState}
        userLogin={userLogin}
        logout={logout}
      />
      <div className="p-4 flex justify-between container mx-xl  content-center md:px-0 ">
        <div className="logo">
          <Link
            to="/"
            className="text-xl font-bold text-indigo-900 tracking-tighter md:text-2xl"
          >
            CoinDrop
          </Link>
        </div>
        <ul className="hidden md:flex text-indigo-900 font-bold tracking-tight">
          <li className="pl-7">
            <Link to="/explore">Explore</Link>
          </li>
          <li className={"pl-7 " + userLogin["login"]}>
            <Link to="/login">Login</Link>
          </li>
          <li className={"pl-7 " + userLogin["login"]}>
            <Link to="/userEntity-registration">Create a Cause</Link>
          </li>
          <li className={"pl-7 " + userLogin["logout"]}>
            <Link to="/profile">Profile</Link>
          </li>
          <li className={"pl-6 " + userLogin["logout"]} onClick={logout}>
            Logout
          </li>
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
