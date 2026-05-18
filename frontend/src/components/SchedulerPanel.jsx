function SchedulerPanel({ schedule }) {
    if (!schedule) {
        return (
            <div className="panel">
                <h2>Greedy Activity Selection</h2>
                <p style={{ color: "#94a3b8" }}>
                    Click &quot;Compute Optimal Schedule&quot; to find the maximum set of non-conflicting
                    transactions within the time window using the greedy activity-selection algorithm.
                </p>
            </div>
        );
    }

    const selectedIds = new Set(
        (schedule.selectedTransactions || []).map((tx) => tx.id)
    );

    const formatTime = (value) =>
        value ? new Date(value).toLocaleString() : "N/A";

    return (
        <div>
            <div className="panel" style={{ marginBottom: "20px" }}>
                <h2>Schedule Summary</h2>
                <p style={{ color: "#94a3b8", marginBottom: "16px" }}>{schedule.algorithm}</p>
                <div style={{ display: "grid", gridTemplateColumns: "repeat(auto-fit, minmax(180px, 1fr))", gap: "16px" }}>
                    <div style={{ background: "#334155", padding: "16px", borderRadius: "10px", textAlign: "center" }}>
                        <div style={{ fontSize: "2rem", fontWeight: "bold", color: "#60a5fa" }}>
                            {schedule.totalTransactions}
                        </div>
                        <div style={{ color: "#94a3b8" }}>Total Transactions</div>
                    </div>
                    <div style={{ background: "#334155", padding: "16px", borderRadius: "10px", textAlign: "center" }}>
                        <div style={{ fontSize: "2rem", fontWeight: "bold", color: "#4ade80" }}>
                            {schedule.maxNonConflicting}
                        </div>
                        <div style={{ color: "#94a3b8" }}>Max Non-Conflicting</div>
                    </div>
                    <div style={{ background: "#334155", padding: "16px", borderRadius: "10px", textAlign: "center" }}>
                        <div style={{ fontSize: "1rem", fontWeight: "bold", color: "#fbbf24" }}>
                            {formatTime(schedule.windowStart)}
                        </div>
                        <div style={{ color: "#94a3b8" }}>Window Start</div>
                    </div>
                    <div style={{ background: "#334155", padding: "16px", borderRadius: "10px", textAlign: "center" }}>
                        <div style={{ fontSize: "1rem", fontWeight: "bold", color: "#fbbf24" }}>
                            {formatTime(schedule.windowEnd)}
                        </div>
                        <div style={{ color: "#94a3b8" }}>Window End</div>
                    </div>
                </div>
            </div>

            {schedule.totalTransactions === 0 ? (
                <div className="panel">
                    <p style={{ color: "#94a3b8" }}>No transactions found. Create some transfers first to run the scheduler.</p>
                </div>
            ) : (
                <div className="panel">
                    <h2>All Transactions vs Selected Schedule</h2>
                    <p style={{ color: "#94a3b8", marginBottom: "16px" }}>
                        Green rows are selected by the greedy algorithm; grey rows conflict and are excluded.
                    </p>
                    <table className="transaction-table">
                        <thead>
                            <tr>
                                <th>Selected</th>
                                <th>ID</th>
                                <th>From</th>
                                <th>To</th>
                                <th>Amount</th>
                                <th>Start Time</th>
                                <th>End Time</th>
                            </tr>
                        </thead>
                        <tbody>
                            {(schedule.allTransactions || []).map((item) => {
                                const isSelected = selectedIds.has(item.id);
                                return (
                                    <tr
                                        key={item.id}
                                        style={{
                                            background: isSelected ? "rgba(74, 222, 128, 0.15)" : "transparent",
                                            opacity: isSelected ? 1 : 0.6
                                        }}
                                    >
                                        <td>{isSelected ? "✓" : "—"}</td>
                                        <td>{item.id}</td>
                                        <td>{item.fromAccount?.accountHolder ?? "N/A"}</td>
                                        <td>{item.toAccount?.accountHolder ?? "N/A"}</td>
                                        <td>₹{item.amount}</td>
                                        <td>{formatTime(item.startTime)}</td>
                                        <td>{formatTime(item.endTime)}</td>
                                    </tr>
                                );
                            })}
                        </tbody>
                    </table>
                </div>
            )}
        </div>
    );
}
export default SchedulerPanel;
