import { useEffect, useState } from "react";
import api from "../api/api";
import TransactionTable from "../components/TransactionTable";

function Transactions() {

    const [transactions, setTransactions] =
        useState([]);

    const [accounts, setAccounts] =
        useState([]);

    const [toast, setToast] = useState(null);
    const [localRejected, setLocalRejected] = useState([]);

    const [formData, setFormData] =
        useState({
            fromAccountId: "",
            toAccountId: "",
            amount: ""
        });

    useEffect(() => {

        fetchTransactions();

        fetchAccounts();

    }, []);

    const fetchTransactions = async () => {

        const response =
            await api.get("/transactions");

        // Merge backend transactions with local rejected ones
        const allTx = [...response.data, ...localRejected];
        // Sort by ID or time if needed, but simple append is fine
        setTransactions(allTx);
    };

    const fetchAccounts = async () => {

        const response =
            await api.get("/accounts");

        setAccounts(response.data);
    };

    const handleChange = (event) => {

        setFormData({
            ...formData,
            [event.target.name]:
                event.target.value
        });
    };

    const createTransaction = async (
        event
    ) => {

        event.preventDefault();

        try {

            const payload = {
                fromAccountId: parseInt(formData.fromAccountId, 10),
                toAccountId: parseInt(formData.toAccountId, 10),
                amount: parseFloat(formData.amount)
            };

            const response =
                await api.post(
                    "/transactions",
                    payload
                );

            if (response.data.safe) {
                setToast({ text: "Transaction Approved", type: "success" });
            } else {
                setToast({ text: "Unsafe State Detected", type: "error" });
                
                // Backend doesn't save rejected transactions to DB. 
                // We track them locally so they appear in the UI table as requested.
                const rejectedTx = {
                    id: `REJECTED-${Date.now()}`,
                    fromAccount: accounts.find(a => a.id === payload.fromAccountId),
                    toAccount: accounts.find(a => a.id === payload.toAccountId),
                    amount: payload.amount,
                    status: "REJECTED",
                    transactionType: "TRANSFER",
                    startTime: new Date().toISOString()
                };
                setLocalRejected(prev => [...prev, rejectedTx]);
            }
            
            setTimeout(() => setToast(null), 3000);

            await fetchTransactions();
            await fetchAccounts();

            setFormData({
                fromAccountId: "",
                toAccountId: "",
                amount: ""
            });

        } catch (error) {
            console.error("Transaction failed:", error);
            setToast({ text: "Error: Transaction failed", type: "error" });
            setTimeout(() => setToast(null), 3000);
        }
    };

    return (

        <div>

            <h1 className="page-title">
                Transactions
            </h1>

            <form
                className="transaction-form"
                onSubmit={createTransaction}
            >

                <select
                    name="fromAccountId"
                    value={
                        formData.fromAccountId
                    }
                    onChange={handleChange}
                    required
                >

                    <option value="">
                        Sender
                    </option>

                    {
                        accounts.map(
                            (account) => (

                            <option
                                key={account.id}
                                value={account.id}
                            >

                                {
                                    `${account.accountHolder} (₹${account.allocated})`
                                }

                            </option>
                        ))
                    }

                </select>

                <select
                    name="toAccountId"
                    value={
                        formData.toAccountId
                    }
                    onChange={handleChange}
                    required
                >

                    <option value="">
                        Receiver
                    </option>

                    {
                        accounts.map(
                            (account) => (

                            <option
                                key={account.id}
                                value={account.id}
                            >

                                {
                                    `${account.accountHolder} (₹${account.allocated})`
                                }

                            </option>
                        ))
                    }

                </select>

                <input
                    type="number"
                    name="amount"
                    placeholder="Amount"
                    value={formData.amount}
                    onChange={handleChange}
                    required
                />

                <button
                    className="primary-btn"
                    type="submit"
                >

                    Transfer

                </button>

            </form>

            {toast && (
                <div style={{
                    padding: "15px",
                    margin: "20px 0",
                    borderRadius: "5px",
                    color: "#fff",
                    backgroundColor: toast.type === "success" ? "#22c55e" : "#ef4444",
                    textAlign: "center",
                    fontWeight: "bold"
                }}>
                    {toast.text}
                </div>
            )}

            <TransactionTable
                transactions={transactions}
            />

        </div>
    );
}

export default Transactions;