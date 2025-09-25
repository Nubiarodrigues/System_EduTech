import * as classroomService from "../../services/classroomService";
import { useState } from "react";

const useClassroomFindAll = () => {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [classrooms, setClassrooms] = useState([]);

  const findAll = async () => {
    setLoading(true);
    setError(null);

    try {
      const response = await classroomService.findAll();
      setClassrooms(response.data);
    } catch (err) {
      setError(err);
      console.log(err);
    } finally {
      setLoading(false);
    }
  };
  return { findAll, loading, error, classrooms, setClassrooms };
};

export default useClassroomFindAll;
