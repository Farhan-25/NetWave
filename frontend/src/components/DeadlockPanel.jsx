function DeadlockPanel({ result }) {
    if (!result) {
        return (
            <div className="panel">
                <h2>Wait-For Graph Analysis</h2>
                <p style={{ color: "#94a3b8" }}>
                    Detect circular wait chains in an already-deadlocked system. Unlike prevention
                    (Banker&apos;s Algorithm), detection identifies the cycle after deadlock has formed.
                </p>
            </div>
        );
    }

    const graphEntries = Object.entries(result.waitForGraph || {});

    return (
        <div>
            <div className="panel" style={{ marginBottom: "20px", textAlign: "center" }}>
                <h2 style={{ marginBottom: "20px" }}>Detection Result</h2>
                <div style={{
                    padding: "20px",
                    background: "#334155",
                    borderRadius: "12px",
                    display: "inline-block",
                    marginBottom: "20px"
                }}>
                    {result.deadlocked ? (
                        <h1 style={{ color: "#f87171", fontSize: "2rem", margin: 0 }}>DEADLOCK DETECTED</h1>
                    ) : (
                        <h1 style={{ color: "#4ade80", fontSize: "2rem", margin: 0 }}>NO DEADLOCK</h1>
                    )}
                </div>
                <p style={{ color: "#94a3b8" }}>{result.description}</p>

                {result.deadlocked && result.circularWaitLabels?.length > 0 && (
                    <div style={{ marginTop: "30px" }}>
                        <h3 style={{ color: "#f87171", marginBottom: "16px" }}>Circular Wait Chain:</h3>
                        <div style={{ display: "flex", justifyContent: "center", flexWrap: "wrap", gap: "10px" }}>
                            {result.circularWaitLabels.map((label, index) => (
                                <div key={index} style={{ display: "flex", alignItems: "center" }}>
                                    <span style={{
                                        background: "#dc2626",
                                        padding: "12px 20px",
                                        borderRadius: "10px",
                                        fontWeight: "bold"
                                    }}>
                                        {label}
                                    </span>
                                    {index < result.circularWaitLabels.length - 1 && (
                                        <span style={{ margin: "0 10px", color: "#64748b", fontSize: "1.5rem" }}>→</span>
                                    )}
                                </div>
                            ))}
                        </div>
                    </div>
                )}
            </div>

            {graphEntries.length > 0 && (
                <div className="panel">
                    <h2 style={{ marginBottom: "16px" }}>Wait-For Graph</h2>
                    <p style={{ color: "#94a3b8", marginBottom: "16px" }}>
                        Each row shows which process a node is waiting for.
                    </p>
                    <table className="transaction-table">
                        <thead>
                            <tr>
                                <th>Process</th>
                                <th>Waiting For</th>
                            </tr>
                        </thead>
                        <tbody>
                            {graphEntries.map(([process, waitingFor]) => (
                                <tr key={process}>
                                    <td>{process}</td>
                                    <td>{waitingFor.length > 0 ? waitingFor.join(", ") : "—"}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            )}
        </div>
    );
}
export default DeadlockPanel;
