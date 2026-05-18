import { useEffect, useState } from "react";
import api from "../api/api";
import SchedulerPanel from "../components/SchedulerPanel";
function Scheduler() {
    const [data, setData] = useState([]);

    const loadSchedule = async () => {
        const response = await api.get("/transactions/schedule");
        setData(response.data);
    };
    return (
        <div>
            <h1 className="page-title">
                Scheduler Page
            </h1>
            <button className="primary-btn" onClick={loadSchedule}>
                Get Optimal Schedule
            </button>
            <SchedulerPanel data={data} />
        </div>
    );
}
export default Scheduler;
