import { useState } from "react";
import * as schoolService from "../../services/schoolService";

const useSchoolFindByIdAction = () => {
  const [school, setSchool] = useState(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  const findByIdSchool = async (id) => {
    setLoading(true);

    try {
      const response = await schoolService.findById(id);
      setSchool(response.data);
      return response.data;
    } catch (err) {
      setError(err);
      throw err;
    } finally {
      setLoading(false);
    }
  };

  return { findByIdSchool, school, error, loading };
};

export default useSchoolFindByIdAction;
