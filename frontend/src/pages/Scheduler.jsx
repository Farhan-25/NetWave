import { useState } from "react";
import api from "../api/api";
import SchedulerPanel from "../components/SchedulerPanel";

function Scheduler() {
    const [schedule, setSchedule] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const loadSchedule = async () => {
        setLoading(true);
        setError(null);
        try {
            const response = await api.get("/transactions/schedule");
            setSchedule(response.data);
        } catch (err) {
            console.error("Failed to load schedule:", err);
            setError("Could not load schedule. Ensure the backend is running.");
            setSchedule(null);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div>
            <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center", marginBottom: "25px" }}>
                <h1 className="page-title" style={{ marginBottom: 0 }}>
                    Activity Selection Scheduler
                </h1>
                <button className="primary-btn" onClick={loadSchedule} disabled={loading} style={{ marginBottom: 0 }}>
                    {loading ? "Computing..." : "Compute Optimal Schedule"}
                </button>
            </div>
            {error && (
                <div style={{ padding: "15px", marginBottom: "20px", borderRadius: "8px", background: "#7f1d1d", color: "#fecaca" }}>
                    {error}
                </div>
            )}
            <SchedulerPanel schedule={schedule} />
        </div>
    );
}
export default Scheduler;
