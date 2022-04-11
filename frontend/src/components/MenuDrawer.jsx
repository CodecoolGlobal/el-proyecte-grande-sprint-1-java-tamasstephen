import React from "react";
import { HiX } from "react-icons/hi";
import { Link } from "react-router-dom";

const MenuDrawer = ({ menuState, stateChanger, userLogin, logout }) => {
  function closeModal() {
    stateChanger();
  }

  function handleLogout() {
    logout();
    closeModal();
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
        <li onClick={closeModal}>
          <Link to="/explore">Explore</Link>
        </li>
        <li className={"py-4 " + userLogin["login"]} onClick={closeModal}>
          <Link to="login">Login</Link>
        </li>
        <li onClick={closeModal} className={userLogin["login"]}>
          <Link to="userEntity-registration">Sign Up</Link>
        </li>
        <li onClick={closeModal} className={"py-4 " + userLogin["logout"]}>
          <Link to="/profile">Profile</Link>
        </li>
        <li className={userLogin["logout"]}>
          <Link onClick={handleLogout} to="/">
            Logout
          </Link>
        </li>
      </ul>
    </div>
  );
};

export default MenuDrawer;
