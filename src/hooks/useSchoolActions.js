import { useState } from 'react';
import * as schoolService from '../services/schoolService';

export function useSchoolActions() {

    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const sendData = async (data) => {
        setLoading(true);
        setError(null);

        try {
            const response = await schoolService.create(data);
            setLoading(false);
            return response.data;
        } catch (err) {
            setError(err);
            setLoading(false);
            throw err;
        }
    };

    return { sendData, loading, error };
}