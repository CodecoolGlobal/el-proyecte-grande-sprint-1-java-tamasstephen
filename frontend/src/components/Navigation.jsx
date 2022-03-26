import React from "react";

export const Navigation = () => {
  return (
    <nav className="flex justify-center">
      <div className="p-4 flex justify-between container mx-xl xl:px-[5rem]">
        <div className="logo">
          <a href="/">DropCoin</a>
        </div>
        <ul className="hidden md:flex">
          <li className="pl-4">Explore</li>
          <li className="pl-4">Login</li>
          <li className="pl-4">Sign Up</li>
        </ul>
        <div className="hamburger md:hidden">close</div>
      </div>
    </nav>
  );
};

export default Navigation;
