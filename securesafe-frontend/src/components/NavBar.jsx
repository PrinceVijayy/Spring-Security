import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import SignUp from "../pages/SignUp";
import SignIn from "../pages/SignIn";

const NavBar = () => {
  return (
    <div>
      <Routes>
        {/* =====================Log In============================== */}
        <Route path="/" element={<SignUp />} />
        <Route path="/signIn" element={<SignIn />} />
      </Routes>
    </div>
  );
};

export default NavBar;
