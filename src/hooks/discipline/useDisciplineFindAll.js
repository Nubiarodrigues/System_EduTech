import { useState } from "react";
import * as disciplineService from "../../services/disciplineService";

function useDisciplineFindAll() {
  const [disciplines, setDisciplines] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const findAllDiscipline = async () => {
    setLoading(true);

    try {
      const response = await disciplineService.findAll();
      setDisciplines(response.data);
      return response.data;
    } catch (err) {
      setError(err);
    } finally {
      setLoading(false);
    }
  };

  return { disciplines, findAllDiscipline, loading, error };
}

export default useDisciplineFindAll;
