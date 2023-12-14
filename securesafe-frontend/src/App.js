import { Route, Routes } from "react-router-dom";
import "./App.css";
import SignIn from "./pages/SignIn";
import SignUp from "./pages/SignUp";
import Home from "./pages/Home";

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<SignUp />} />
        <Route path="/signIn" element={<SignIn />} />
        <Route path="/home" element={<Home />} />
      </Routes>
    </div>
  );
}

export default App;
