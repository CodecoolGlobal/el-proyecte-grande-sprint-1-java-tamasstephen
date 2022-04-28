import Navigation from "./Navigation";
import Footer from "./Footer";
import { Outlet } from "react-router-dom";

function App({ userLogin, setLoginState, adminState }) {
  return (
    <>
      <div className="flex flex-col min-h-screen justify-between bg-slate-100 overflow-hidden">
        <div>
          <Navigation setLoginState={setLoginState} userLogin={userLogin} adminState={adminState} />
          <Outlet />
        </div>
        <Footer />
      </div>
    </>
  );
}

export default App;
