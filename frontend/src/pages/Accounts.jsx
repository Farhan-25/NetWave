import { useEffect, useState } from "react";
import api from "../api/api";
import AccountCard from "../components/AccountCard";

function Accounts() {

    const [accounts, setAccounts] =
        useState([]);

    const [formData, setFormData] =
        useState({
            accountHolder: "",
            allocated: "",
            maxRequired: ""
        });

    useEffect(() => {

        fetchAccounts();

    }, []);

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

    const createAccount = async (
        event
    ) => {

        event.preventDefault();

        await api.post(
            "/accounts",
            formData
        );

        fetchAccounts();

        setFormData({
            accountHolder: "",
            allocated: "",
            maxRequired: ""
        });
    };

    const deleteAccount = async (
        id
    ) => {

        await api.delete(
            `/accounts/${id}`
        );

        fetchAccounts();
    };

    return (

        <div>

            <h1 className="page-title">
                Accounts
            </h1>

            <form
                className="transaction-form"
                onSubmit={createAccount}
            >

                <input
                    type="text"
                    name="accountHolder"
                    placeholder="Account Holder"
                    value={formData.accountHolder}
                    onChange={handleChange}
                    required
                />

                <input
                    type="number"
                    name="allocated"
                    placeholder="Balance"
                    value={formData.allocated}
                    onChange={handleChange}
                    required
                />

                <input
                    type="number"
                    name="maxRequired"
                    placeholder="Max Required"
                    value={formData.maxRequired}
                    onChange={handleChange}
                    required
                />

                <button
                    className="primary-btn"
                    type="submit"
                >
                    Add Account
                </button>

            </form>

            <div className="card-grid">

                {
                    accounts.map(
                        (account) => (

                        <div key={account.id}>

                            <AccountCard
                                account={account}
                            />

                            <button
                                className="delete-btn"
                                onClick={() =>
                                    deleteAccount(
                                        account.id
                                    )
                                }
                            >
                                Delete
                            </button>

                        </div>
                    ))
                }

            </div>

        </div>
    );
}

export default Accounts;