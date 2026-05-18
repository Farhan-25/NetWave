import { useState } from "react";
import api from "../api/api";
import SimulationPanel from "../components/SimulationPanel";
function Simulation() {
    const [message, setMessage] = useState("");
    const runSimulation = async () => {
        const response = await api.post(
            "/simulation/run"
        );
        setMessage(response.data);
    };
    return (
        <div>
            <h1 className="page-title">
                Concurrent Simulation
            </h1>
            <button
                className="primary-btn"
                onClick={runSimulation}
            >
                Run Simulation
            </button>
            <SimulationPanel message={message} />
        </div >
    );
}
export default Simulation;