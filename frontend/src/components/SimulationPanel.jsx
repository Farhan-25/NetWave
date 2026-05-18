function SimulationPanel({ result }) {
    if (!result) {
        return (
            <div className="panel">
                <h2>Banker&apos;s Algorithm — Deadlock Prevention</h2>
                <p style={{ color: "#94a3b8" }}>
                    Run the demo to see how concurrent transfer requests are evaluated. When a request
                    would lead to an unsafe state, the Banker&apos;s Algorithm rejects it — preventing deadlock.
                </p>
            </div>
        );
    }

    const formatSequence = (sequence) => {
        if (!sequence || sequence.length === 0) {
            return "NONE (unsafe)";
        }
        return sequence.map((p) => `P${p}`).join(" → ");
    };

    return (
        <div>
            <div className="panel" style={{ marginBottom: "20px" }}>
                <h2>{result.scenario}</h2>

                <div style={{
                    margin: "20px 0",
                    padding: "20px",
                    background: result.deadlockPrevented ? "rgba(74, 222, 128, 0.15)" : "#334155",
                    borderRadius: "12px",
                    border: result.deadlockPrevented ? "1px solid #4ade80" : "1px solid #475569"
                }}>
                    {result.deadlockPrevented ? (
                        <>
                            <h3 style={{ color: "#4ade80", marginBottom: "8px" }}>Deadlock Prevented</h3>
                            <p><strong>Rejected transfer:</strong> {result.rejectedTransfer}</p>
                            <p><strong>Reason:</strong> {result.reason}</p>
                        </>
                    ) : (
                        <>
                            <h3 style={{ color: "#fbbf24", marginBottom: "8px" }}>No Rejection in Demo</h3>
                            <p>{result.reason}</p>
                        </>
                    )}
                </div>

                <div style={{ display: "grid", gridTemplateColumns: "1fr 1fr", gap: "16px", marginBottom: "20px" }}>
                    <div style={{ background: "#334155", padding: "16px", borderRadius: "10px" }}>
                        <h4 style={{ color: "#93c5fd", marginBottom: "8px" }}>Safe Sequence (Before)</h4>
                        <p>{formatSequence(result.safeSequenceBefore)}</p>
                    </div>
                    <div style={{ background: "#334155", padding: "16px", borderRadius: "10px" }}>
                        <h4 style={{ color: "#93c5fd", marginBottom: "8px" }}>Safe Sequence (After Request)</h4>
                        <p>{formatSequence(result.safeSequenceAfter)}</p>
                    </div>
                </div>
            </div>

            <div className="panel">
                <h2>Step-by-Step Execution</h2>
                <ol style={{ paddingLeft: "20px", lineHeight: "1.8", color: "#cbd5e1" }}>
                    {(result.steps || []).map((step, index) => (
                        <li key={index} style={{ marginBottom: "8px" }}>{step}</li>
                    ))}
                </ol>
            </div>
        </div>
    );
}
export default SimulationPanel;
