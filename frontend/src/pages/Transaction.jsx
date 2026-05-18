import { useEffect, useState } from "react";
import api from "../api/api";
import TransactionTable from "../components/TransactionTable";

function Transactions() {

    const [transactions, setTransactions] = useState([]);
    const [accounts, setAccounts] = useState([]);
    const [toast, setToast] = useState(null);
    const [lastResult, setLastResult] = useState(null);

    const [formData, setFormData] = useState({
        fromAccountId: "",
        toAccountId: "",
        amount: ""
    });

    useEffect(() => {
        fetchTransactions();
        fetchAccounts();
    }, []);

    const fetchTransactions = async () => {
        const response = await api.get("/transactions");
        setTransactions((prev) => {
            const rejected = prev.filter((tx) => String(tx.id).startsWith("REJECTED"));
            return [...response.data, ...rejected];
        });
    };

    const fetchAccounts = async () => {
        const response = await api.get("/accounts");
        setAccounts(response.data);
    };

    const handleChange = (event) => {
        setFormData({
            ...formData,
            [event.target.name]: event.target.value
        });
    };

    const createTransaction = async (event) => {
        event.preventDefault();

        try {
            const payload = {
                fromAccountId: parseInt(formData.fromAccountId, 10),
                toAccountId: parseInt(formData.toAccountId, 10),
                amount: parseFloat(formData.amount)
            };

            const response = await api.post("/transactions", payload);
            setLastResult(response.data);

            if (response.data.safe) {
                setToast({
                    text: `Transaction Approved — Safe sequence: ${(response.data.safeSequence || []).map((p) => "P" + p).join(" → ")}`,
                    type: "success"
                });
            } else {
                const suggestion = response.data.suggestedAmount;
                const suggestionText = suggestion && suggestion > 0
                    ? ` Suggested safe amount: ₹${suggestion} (reduce by ₹${payload.amount - suggestion}).`
                    : " No safe amount found — try a smaller transfer.";
                setToast({
                    text: `Unsafe State Detected — Request rejected.${suggestionText}`,
                    type: "error"
                });

                const rejectedTx = {
                    id: `REJECTED-${Date.now()}`,
                    fromAccount: accounts.find((a) => a.id === payload.fromAccountId),
                    toAccount: accounts.find((a) => a.id === payload.toAccountId),
                    amount: payload.amount,
                    status: "REJECTED",
                    transactionType: "TRANSFER",
                    startTime: new Date().toISOString(),
                    suggestedAmount: suggestion
                };
                setTransactions((prev) => [...prev, rejectedTx]);
            }

            setTimeout(() => setToast(null), 6000);

            if (response.data.safe) {
                await fetchTransactions();
            }
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
            <h1 className="page-title">Transactions</h1>

            <form className="transaction-form" onSubmit={createTransaction}>
                <select
                    name="fromAccountId"
                    value={formData.fromAccountId}
                    onChange={handleChange}
                    required
                >
                    <option value="">Sender</option>
                    {accounts.map((account) => (
                        <option key={account.id} value={account.id}>
                            {`${account.accountHolder} (₹${account.allocated})`}
                        </option>
                    ))}
                </select>

                <select
                    name="toAccountId"
                    value={formData.toAccountId}
                    onChange={handleChange}
                    required
                >
                    <option value="">Receiver</option>
                    {accounts.map((account) => (
                        <option key={account.id} value={account.id}>
                            {`${account.accountHolder} (₹${account.allocated})`}
                        </option>
                    ))}
                </select>

                <input
                    type="number"
                    name="amount"
                    placeholder="Amount"
                    value={formData.amount}
                    onChange={handleChange}
                    required
                />

                <button className="primary-btn" type="submit">Transfer</button>
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

            {lastResult && !lastResult.safe && lastResult.suggestedAmount > 0 && (
                <div className="panel" style={{ marginBottom: "20px", border: "1px solid #fbbf24" }}>
                    <h3 style={{ color: "#fbbf24", marginBottom: "8px" }}>Rollback Suggestion (Bonus)</h3>
                    <p style={{ color: "#cbd5e1" }}>
                        The requested amount of ₹{lastResult.amount} would cause an unsafe state.
                        The smallest safe modification is to reduce the transfer to{" "}
                        <strong>₹{lastResult.suggestedAmount}</strong>{" "}
                        (a reduction of ₹{lastResult.amount - lastResult.suggestedAmount}).
                    </p>
                </div>
            )}

            {lastResult && lastResult.safe && lastResult.safeSequence?.length > 0 && (
                <div className="panel" style={{ marginBottom: "20px", border: "1px solid #4ade80" }}>
                    <h3 style={{ color: "#4ade80", marginBottom: "8px" }}>Safe Sequence After Transfer</h3>
                    <p style={{ color: "#cbd5e1" }}>
                        {lastResult.safeSequence.map((p) => "P" + p).join(" → ")}
                    </p>
                </div>
            )}

            <TransactionTable transactions={transactions} />
        </div>
    );
}

export default Transactions;
