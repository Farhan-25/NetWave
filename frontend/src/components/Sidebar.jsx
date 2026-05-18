import { Link } from "react-router-dom";
import "../styles/sidebar.css";

function Sidebar() {
    return (
        <div className="sidebar">
            <h1 className="logo">Bank Transaction System</h1>
            <Link to="/">Dashboard</Link>
            <Link to="/accounts">Accounts</Link>
            <Link to="/transactions">Transactions</Link>
            <Link to="/schedule">Activity Scheduler</Link>
            <Link to="/banker">Banker's Algorithm</Link>
            <Link to="/simulation">Deadlock Prevention</Link>
            <Link to="/deadlock">Deadlock Detection</Link>
        </div>
    );
}
export default Sidebar;
