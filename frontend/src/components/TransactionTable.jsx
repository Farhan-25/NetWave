import "../styles/table.css";

function TransactionTable({
    transactions
}) {

    return (

        <table className="transaction-table">

            <thead>

            <tr>
                <th>ID</th>
                <th>From</th>
                <th>To</th>
                <th>Amount</th>
                <th>Status</th>
                <th>Time</th>
            </tr>

            </thead>

            <tbody>

            {
                transactions.map(
                    (transaction) => (

                    <tr key={transaction.id}>
                        <td>{transaction.id}</td>
                        <td>{transaction.fromAccount ? transaction.fromAccount.accountHolder : "N/A"}</td>
                        <td>{transaction.toAccount ? transaction.toAccount.accountHolder : "N/A"}</td>
                        <td>₹{transaction.amount}</td>
                        <td>{transaction.status}</td>
                        <td>{transaction.startTime ? new Date(transaction.startTime).toLocaleString() : "N/A"}</td>
                    </tr>
                ))
            }

            </tbody>

        </table>
    );
}

export default TransactionTable;