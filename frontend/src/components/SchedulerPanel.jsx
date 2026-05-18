function SchedulerPanel({ data }) {
    return (
        <div className="panel">
            <h2>Greedy Activity Selection</h2>
            <p>
                Maximum Non-Conflicting Transactions
            </p>
            {data.length > 0 && (
                <table className="transaction-table">
                    <thead>
                        <tr>
                            <th>Transaction ID</th>
                            <th>Amount</th>
                            <th>Start Time</th>
                            <th>End Time</th>
                        </tr>
                    </thead>
                    <tbody>
                        {data.map((item) => (
                            <tr key={item.id}>
                                <td>{item.id}</td>
                                <td>₹{item.amount}</td>
                                <td>{item.startTime ? new Date(item.startTime).toLocaleString() : "N/A"}</td>
                                <td>{item.endTime ? new Date(item.endTime).toLocaleString() : "N/A"}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}
        </div>
    );
}
export default SchedulerPanel;