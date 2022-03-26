import React from "react";
import { HiMenu } from "react-icons/hi";

export const Navigation = () => {
  return (
    <nav className="flex justify-center ">
      <div className="p-4 flex justify-between container mx-xl xl:px-[5rem] content-center">
        <div className="logo">
          <a href="/">DropCoin</a>
        </div>
        <ul className="hidden md:flex">
          <li className="pl-4">Explore</li>
          <li className="pl-4">Login</li>
          <li className="pl-4">Sign Up</li>
        </ul>
        <div className="flex items-center hamburger md:hidden">
          <HiMenu />
        </div>
      </div>
    </nav>
  );
};

export default Navigation;
