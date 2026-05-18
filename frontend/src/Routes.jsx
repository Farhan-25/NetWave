import Dashboard from "./pages/Dashboard";
import Accounts from "./pages/Accounts";
import Transactions from "./pages/Transactions";

const routes = [
    {
        path: "/",
        element: <Dashboard />
    },
    {
        path: "/accounts",
        element: <Accounts />
    },
    {
        path: "/transactions",
        element: <Transactions />
    }
];
export default routes;