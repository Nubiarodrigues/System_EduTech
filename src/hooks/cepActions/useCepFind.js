import React, { useState } from 'react'
import * as cepService from '../../services/cepService';

const useCepFind = () => {

    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const [address, setAddress] = useState(null);

    const findAddress = async (cep) => {
        setLoading(true);
        setError(null);
        setAddress(null);

        try {
            const response = await cepService.findAddress(cep);
            setAddress(response.data);
            return response.data;
        } catch (err) {
            setError(err);
            return null;
        } finally {
            setLoading(false);
        }
    }

    return { findAddress, loading, error, address };
}

export default useCepFind