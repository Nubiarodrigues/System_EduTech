import { useState } from "react";
import * as classroomService from "../../services/classroomService";

const useClassroomFindById = () => {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [classroom, setClassroom] = useState({});

  const findById = async (id) => {
    setLoading(true);

    try {
      const response = await classroomService.findById(id);
      setClassroom(response.data);
      return response.data;
    } catch (err) {
      setError(err);
      console.error("Erro ao encontrar a turma com esse ID.", err);
    } finally {
      setLoading(false);
    }
  };

  return { classroom, loading, error, findById };
};

export default useClassroomFindById;
