import Navigation from "./components/Navigation";
import Footer from "./components/Footer";
import { Outlet } from "react-router-dom";

function App() {
  return (
    <>
      <div className="flex flex-col min-h-screen justify-between">
        <div>
          <Navigation />
          <Outlet />
        </div>
        <Footer />
      </div>
    </>
  );
}

export default App;
