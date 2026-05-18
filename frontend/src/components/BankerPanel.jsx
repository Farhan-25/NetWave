function BankerPanel({ state }) {
    if (!state) {
        return (
            <div className="panel" style={{ textAlign: "center", padding: "40px" }}>
                <p style={{ color: "#94a3b8" }}>Loading system state...</p>
            </div>
        );
    }

    const isSafe = state.safe;
    const sequence = state.safeSequence || [];
    const allocation = state.allocation || [];
    const need = state.need || [];
    const accountNames = state.accountNames || [];

    return (
        <div>
            <div className="panel" style={{ textAlign: "center", padding: "40px", marginBottom: "20px" }}>
                <h2 style={{ marginBottom: "20px", color: "#f8fafc" }}>Banker&apos;s Algorithm State Check</h2>

                <div style={{ marginBottom: "20px", padding: "20px", background: "#334155", borderRadius: "12px", display: "inline-block" }}>
                    {isSafe ? (
                        <h1 style={{ color: "#4ade80", fontSize: "2rem", margin: 0 }}>SYSTEM IS SAFE</h1>
                    ) : (
                        <h1 style={{ color: "#f87171", fontSize: "2rem", margin: 0 }}>SYSTEM IS UNSAFE</h1>
                    )}
                </div>

                <p style={{ color: "#94a3b8", maxWidth: "600px", margin: "0 auto" }}>{state.message}</p>

                {isSafe && sequence.length > 0 && (
                    <div style={{ marginTop: "30px" }}>
                        <h3 style={{ color: "#93c5fd", marginBottom: "20px" }}>Safe Execution Sequence:</h3>
                        <div style={{ justifyContent: "center", flexWrap: "wrap", display: "flex", gap: "10px" }}>
                            {sequence.map((item, index) => (
                                <div key={index} style={{ display: "flex", alignItems: "center" }}>
                                    <span style={{
                                        background: "#3b82f6",
                                        padding: "12px 20px",
                                        borderRadius: "10px",
                                        fontSize: "1.1rem",
                                        fontWeight: "bold"
                                    }}>
                                        {accountNames[item] || `P${item}`}
                                    </span>
                                    {index < sequence.length - 1 && (
                                        <span style={{ margin: "0 10px", color: "#64748b", fontSize: "1.5rem" }}>→</span>
                                    )}
                                </div>
                            ))}
                        </div>
                    </div>
                )}
            </div>

            {allocation.length > 0 && (
                <div className="panel">
                    <h2 style={{ marginBottom: "16px" }}>Resource Allocation Matrix</h2>
                    <p style={{ color: "#94a3b8", marginBottom: "16px" }}>
                        Available resources: ₹{state.available}
                    </p>
                    <table className="transaction-table">
                        <thead>
                            <tr>
                                <th>Process</th>
                                <th>Allocation</th>
                                <th>Need (Max − Allocated)</th>
                            </tr>
                        </thead>
                        <tbody>
                            {allocation.map((alloc, index) => (
                                <tr key={index}>
                                    <td>{accountNames[index] || `P${index}`}</td>
                                    <td>₹{alloc}</td>
                                    <td>₹{need[index]}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            )}
        </div>
    );
}
export default BankerPanel;
