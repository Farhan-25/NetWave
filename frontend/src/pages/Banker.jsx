import { useEffect, useState } from "react";
import api from "../api/api";
import BankerPanel from "../components/BankerPanel";

function Banker() {
    const [state, setState] = useState(null);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        fetchSafeSequence();
    }, []);

    const fetchSafeSequence = async () => {
        setLoading(true);
        try {
            const response = await api.get("/banker/state");
            setState(response.data);
        } catch (error) {
            console.error("Failed to fetch banker state:", error);
            setState({
                safe: false,
                message: "Backend not reachable",
                safeSequence: [],
                allocation: [],
                need: [],
                available: 0,
                accountNames: []
            });
        } finally {
            setLoading(false);
        }
    };

    return (
        <div>
            <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center", marginBottom: "25px" }}>
                <h1 className="page-title" style={{ marginBottom: 0 }}>
                    Banker&apos;s Algorithm — Safe vs Unsafe State
                </h1>
                <button className="primary-btn" onClick={fetchSafeSequence} disabled={loading} style={{ marginBottom: 0 }}>
                    {loading ? "Evaluating..." : "Re-evaluate Safety"}
                </button>
            </div>
            <BankerPanel state={state} />
        </div>
    );
}
export default Banker;
