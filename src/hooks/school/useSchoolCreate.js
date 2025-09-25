import { useState } from "react";
import * as schoolService from "../../services/schoolService";

export function useSchoolActions() {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [school, setSchool] = useState(null);

  const sendData = async (data) => {
    setLoading(true);
    setError(null);

    try {
      const response = await schoolService.create(data);
      setSchool(response.data);
      return true;
    } catch (err) {
      setError(err);
      throw err;
    } finally {
      setLoading(false);
    }
  };

  return { sendData, loading, error, school };
}
