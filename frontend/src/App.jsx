import { Routes, Route } from "react-router-dom";
import Sidebar from "./components/Sidebar";
import Dashboard from "./pages/Dashboard";
import Accounts from "./pages/Accounts";
import Transaction from "./pages/Transaction";
import Scheduler from "./pages/Scheduler";

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
          </Routes>
        </div>
      </div>
    </div>
  );
}
export default App;