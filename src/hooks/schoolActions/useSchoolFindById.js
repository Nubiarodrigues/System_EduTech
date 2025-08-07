import { useState } from "react"
import * as schoolService from '../../services/schoolService';

const useSchoolFindByIdAction = () => {

    const [school, setSchool] = useState(null);
    const [error, setError] = useState(null);

    const findById = async (id) => {
        try {
            const response = await schoolService.findById(id);
            setSchool(response.data);
            return response.data;
        } catch (err) {
            setError(err);
            throw err;
        }

    }

    return { findById, error };
}

export default useSchoolFindByIdAction