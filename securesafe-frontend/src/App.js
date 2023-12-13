import { BrowserRouter } from "react-router-dom";
import "./App.css";
import SignIn from "./pages/SignIn";

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <SignIn />
      </div>
    </BrowserRouter>
  );
}

export default App;
