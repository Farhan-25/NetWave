import { useState } from "react";
import api from "../api/api";
import DeadlockPanel from "../components/DeadlockPanel";

function Deadlock() {
    const [result, setResult] = useState(null);
    const [loading, setLoading] = useState(false);

    const detectDeadlock = async () => {
        setLoading(true);
        try {
            const response = await api.get("/deadlock/detect");
            setResult(response.data);
        } catch (error) {
            console.error("Deadlock detection failed:", error);
            setResult({
                deadlocked: false,
                description: "Could not reach backend",
                circularWaitChain: [],
                circularWaitLabels: [],
                waitForGraph: {}
            });
        } finally {
            setLoading(false);
        }
    };

    return (
        <div>
            <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center", marginBottom: "25px" }}>
                <h1 className="page-title" style={{ marginBottom: 0 }}>
                    Deadlock Detection — Circular Wait Chain
                </h1>
                <button className="primary-btn" onClick={detectDeadlock} disabled={loading} style={{ marginBottom: 0 }}>
                    {loading ? "Detecting..." : "Detect Deadlock"}
                </button>
            </div>
            <DeadlockPanel result={result} />
        </div>
    );
}
export default Deadlock;
