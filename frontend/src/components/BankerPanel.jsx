function BankerPanel({ sequence }) {
    const isSafe = sequence && sequence.length > 0;

    return (
        <div className="panel" style={{ textAlign: "center", padding: "40px" }}>
            <h2 style={{ marginBottom: "20px", color: "#f8fafc" }}>Banker's Algorithm State Check</h2>
            
            <div style={{ marginBottom: "30px", padding: "20px", background: "#334155", borderRadius: "12px", display: "inline-block" }}>
                {isSafe ? (
                    <h1 style={{ color: "#4ade80", fontSize: "2rem", margin: 0 }}>✅ SYSTEM IS SAFE</h1>
                ) : (
                    <h1 style={{ color: "#f87171", fontSize: "2rem", margin: 0 }}>❌ SYSTEM IS UNSAFE</h1>
                )}
            </div>

            {isSafe && (
                <div style={{ marginTop: "20px" }}>
                    <h3 style={{ color: "#93c5fd", marginBottom: "20px" }}>Recommended Safe Sequence:</h3>
                    <div className="safe-sequence" style={{ justifyContent: "center", flexWrap: "wrap", display: "flex", gap: "10px" }}>
                        {sequence.map((item, index) => (
                            <div key={index} style={{ display: "flex", alignItems: "center" }}>
                                <span style={{
                                    background: "#3b82f6", 
                                    padding: "12px 20px", 
                                    borderRadius: "10px",
                                    fontSize: "1.2rem",
                                    fontWeight: "bold",
                                    boxShadow: "0 4px 6px rgba(0,0,0,0.1)"
                                }}>
                                    P{item}
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
    );
}
export default BankerPanel;
