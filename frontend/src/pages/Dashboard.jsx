import { useEffect, useState } from "react";
import api from "../api/api";
import AccountCard from "../components/AccountCard";
import "../styles/dashboard.css";
function Dashboard() {
    const [accounts, setAccounts] = useState([]);
    useEffect(() => {
        fetchAccounts();
    }, []);
    const fetchAccounts = async () => {
        try {
            const response = await api.get("/accounts");
            setAccounts(response.data);
        } catch (error) {
            console.log("Backend not reachable. Using mock data for Dashboard.", error);
            setAccounts([
                { id: "1", accountHolder: "John Doe", allocated: 15000, maxRequired: 20000, need: 5000, safe: true },
                { id: "2", accountHolder: "Jane Smith", allocated: 25000, maxRequired: 30000, need: 5000, safe: true },
                { id: "3", accountHolder: "Alice Johnson", allocated: 8000, maxRequired: 8000, need: 0, safe: true }
            ]);
        }
    };
    return (
        <div>
            <h1 className="page-title">
                Dashboard
            </h1>
            <div className="card-grid">
                {
                    accounts.map((account) => (
                        <AccountCard
                            key={account.id}
                            account={account}
                        />
                    ))
                }
            </div>
        </div>
    );
}
export default Dashboard;
