import * as adminService from '../../services/adminService';
import { useState } from 'react';

const useAdminUpdate = () => {

    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const sendDataAdmin = async (id, data) => {
        setLoading(true);
        setError(null);

        try {
            const response = await adminService.update(id, data);
            setLoading(false);
            return response.data;
        } catch (err) {
            setError(err);
            setLoading(false);
        }
    }
    return { sendDataAdmin, loading, error }
}

export default useAdminUpdate