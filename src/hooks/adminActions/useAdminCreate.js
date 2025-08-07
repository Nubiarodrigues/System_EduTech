import { useState } from "react";
import * as adminService from '../../services/adminService';

const useAdminCreate = () => {

    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const sendDataAdmin = async (data) => {
        setLoading(true);
        setError(null);

        try {
            const response = await adminService.create(data);
            setLoading(false);
            return response.data;
        } catch (err) {
            setError(err);
            setLoading(false);
        }
    }
    return { sendDataAdmin, loading, error }
}

export default useAdminCreate