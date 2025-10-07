import { useState } from "react";
import * as studentService from "../../services/studentService";

function useStudentFindById() {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState();
  const [student, setStudent] = useState({});

  const findById = async (id) => {
    setLoading(true);

    try {
      const response = await studentService.findById(id);
      setStudent(response.data);
      return response.data;
    } catch (err) {
      setError(err);
      console.error("Erro ao encontrar o estudante com esse ID.", err);
    } finally {
      setLoading(false);
    }
  };

  return { loading, error, findById, student };
}

export default useStudentFindById;
