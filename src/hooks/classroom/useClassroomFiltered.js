import { useEffect } from "react";
import useClassroomFindAll from "./useClassroomFindAll";
import * as classroomService from "../../services/classroomService";

function useClassroomFiltered(filters) {
  const { classrooms, setClassrooms } = useClassroomFindAll();

  useEffect(() => {
    async function fetchData() {
      try {
        const params = new URLSearchParams(filters);
        const response = await classroomService.findAllFiltered(params);
        setClassrooms(response.data);
      } catch (error) {
        console.error("Erro ao buscar turmas filtradas:", error);
      }
    }

    fetchData();
  }, [filters]);

  return classrooms;
}

export default useClassroomFiltered;
