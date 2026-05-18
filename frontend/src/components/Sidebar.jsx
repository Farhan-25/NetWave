import { Link } from "react-router-dom";
import "../styles/sidebar.css";
function Sidebar() {
    return (
        <div className="sidebar">
            <h1 className="logo">Bank Transaction System</h1>
            <Link to="/">Dashboard</Link>
            <Link to="/accounts">Accounts</Link>
            <Link to="/transactions">Transactions</Link>
            <Link to="/schedule">Optimal Schedule</Link>
        </div>
    );
}
export default Sidebar;