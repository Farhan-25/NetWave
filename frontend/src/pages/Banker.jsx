import { useEffect, useState } from "react";
import api from "../api/api";
import BankerPanel from "../components/BankerPanel";
function Banker() {
    const [sequence, setSequence] = useState([]);
    useEffect(() => {
        fetchSafeSequence();
    }, []);
    const fetchSafeSequence = async () => {
        try {
            const response = await api.get("/banker/safe-sequence");
            setSequence(response.data);
        } catch (error) {
            console.log("Backend not reachable. Using mock data for Banker.", error);
            setSequence([1, 3, 4, 0, 2]);
        }
    };
    return (
        <div>
            <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center", marginBottom: "25px" }}>
                <h1 className="page-title" style={{ marginBottom: 0 }}>
                    Banker's & Safety Algorithm Dashboard
                </h1>
                <button className="primary-btn" onClick={fetchSafeSequence} style={{ marginBottom: 0 }}>
                    Re-evaluate Safety
                </button>
            </div>
            <BankerPanel sequence={sequence} />
        </div>
    );
}
export default Banker;