import * as loginService from '../services/loginService';
import { useState } from 'react';

export function useLoginActions() {

    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const sendCredentials = async (credentials) => {
        setLoading(true);
        setError(null);

        try {
            const response = await loginService.login(credentials);
            setLoading(false);
            return response.data;
        } catch (err) {
            setError(err);
            console.log("Caiu no Catch");
            setLoading(false);
            return null;
        }
    };

    return { sendCredentials, loading, error };

}