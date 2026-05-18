import { Routes, Route } from "react-router-dom";
import Sidebar from "./components/Sidebar";
import Dashboard from "./pages/Dashboard";
import Accounts from "./pages/Accounts";
import Transaction from "./pages/Transaction";
import Scheduler from "./pages/Scheduler";
import Banker from "./pages/Banker";
import Simulation from "./pages/Simulation";
import Deadlock from "./pages/Deadlock";

function App() {
  return (
    <div className="layout">
      <Sidebar />
      <div className="main-content">
        <div className="page-container">
          <Routes>
            <Route path="/" element={<Dashboard />} />
            <Route path="/accounts" element={<Accounts />} />
            <Route path="/transactions" element={<Transaction />} />
            <Route path="/schedule" element={<Scheduler />} />
            <Route path="/banker" element={<Banker />} />
            <Route path="/simulation" element={<Simulation />} />
            <Route path="/deadlock" element={<Deadlock />} />
          </Routes>
        </div>
      </div>
    </div>
  );
}
export default App;
