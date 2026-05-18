import "../styles/cards.css";

function AccountCard({ account }) {

    return (

        <div className="account-card">

            <h3>
                {account.accountHolder}
            </h3>

            <p>
                Balance: ₹{account.allocated}
            </p>

            <p>
                Max Limit: ₹{account.maxRequired}
            </p>

            <p
                className={
                    account.safe
                        ? "safe-text"
                        : "unsafe-text"
                }
            >

                {
                    account.safe
                        ? "SAFE"
                        : "UNSAFE"
                }

            </p>

        </div>
    );
}

export default AccountCard;