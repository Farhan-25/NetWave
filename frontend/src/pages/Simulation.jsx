import { useState } from "react";
import api from "../api/api";
import SimulationPanel from "../components/SimulationPanel";

function Simulation() {
    const [result, setResult] = useState(null);
    const [loading, setLoading] = useState(false);

    const runSimulation = async () => {
        setLoading(true);
        try {
            const response = await api.post("/simulation/run");
            setResult(response.data);
        } catch (error) {
            console.error("Simulation failed:", error);
            setResult({
                scenario: "Error",
                steps: ["Could not reach backend. Start the Spring Boot server on port 8080."],
                deadlockPrevented: false
            });
        } finally {
            setLoading(false);
        }
    };

    return (
        <div>
            <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center", marginBottom: "25px" }}>
                <h1 className="page-title" style={{ marginBottom: 0 }}>
                    Deadlock Prevention Scenario
                </h1>
                <button className="primary-btn" onClick={runSimulation} disabled={loading} style={{ marginBottom: 0 }}>
                    {loading ? "Running..." : "Run Prevention Demo"}
                </button>
            </div>
            <SimulationPanel result={result} />
        </div>
    );
}
export default Simulation;
