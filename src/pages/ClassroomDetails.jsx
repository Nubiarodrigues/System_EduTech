import { Icon } from "@iconify/react";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import BtnGeneric from "../components/buttons/BtnGeneric";
import CircleBadge from "../components/indicators/CircleBadge";
import useClassroomFindById from "../hooks/classroom/useClassroomFindById";
import { useNavigate } from "react-router-dom";

function ClassroomDetails() {
  const { id } = useParams();
  const { classroom, loading, error, findById } = useClassroomFindById();
  const [totalOcupation, setTotalOcupation] = useState(0);
  const navigate = useNavigate();

  useEffect(() => {
    setTotalOcupation(classroom.capacity - classroom.students?.length);
  });

  useEffect(() => {
    findById(id);
  }, [id]);

  const handleAccessStudent = (studentId) => {
    navigate(`/students/${studentId}`);
  };

  if (loading) return <p>Carregando turma...</p>;
  if (error) return <p>Erro ao carregar turma: {error.message}</p>;
  if (!classroom) return null;

  return (
    <div>
      <div className="bg-blue-200 flex justify-between p-4">
        <BtnGeneric
          size="md"
          className="ml-4 bg-blue-950 text-white"
          children={"Diário de classe"}
        />

        <BtnGeneric
          size="md"
          className="ml-4 bg-blue-950 text-white"
          children={"Avisos"}
        />

        <BtnGeneric
          size="md"
          className="ml-4 bg-blue-950 text-white"
          children={"Docentes"}
        />

        <BtnGeneric
          size="md"
          className="ml-4 bg-blue-950 text-white"
          children={"Horários"}
        />
      </div>

      <div className="border flex gap-20 justify-center p-4 mt-10">
        <CircleBadge
          children={"Desempenho da turma"}
          size={96}
          bgColor="bg-[#1839E1]"
          textColor="text-white"
        />

        <CircleBadge
          children={`${totalOcupation}/${classroom.capacity}`}
          size={96}
          bgColor="bg-[#1839E1]"
          textColor="text-white"
        />
      </div>

      <div className="flex mt-10 ">
        <input
          type="text"
          placeholder="Busque por alunos..."
          className="font-normal border-[#e1dcdc] w-[50%] h-10 border rounded-md placeholder:text-[#999999] px-4 placeholder:text-[13px] md:text-[16px] xl:placeholder:text-lg"
        />
        <BtnGeneric
          color="search"
          size="md"
          className="ml-4"
          children={"Buscar"}
        />
      </div>

      <div className="border border-[#bcb9b9] mt-5">
        <div className="grid grid-cols-[3fr_2fr_2fr_2fr_2fr] gap-x-4 items-center  p-2 border border-[#e1dcdc] bg-[#BABABA] font-semibold mb-2">
          <div className="truncate">Nome</div>
          <div>Situação</div>
          <div>CPF</div>
          <div>Matrícula</div>
          <div>Ações</div>
        </div>

        <div>
          {classroom.students?.map((s) => (
            <div
              key={s.id}
              className="grid grid-cols-[3fr_2fr_2fr_2fr_2fr] gap-x-4 border p-2 border-[#e1dcdc] hover:bg-[#DFE0DB] cursor-pointer"
              onClick={() => handleAccessStudent(s.id)}>
              <div>{s.name}</div>
              <div>{s.situation}</div>
              <div>{s.cpf}</div>
              <div>{s.registration}</div>

              <div className="flex gap-2 items-center">
                <Icon icon="tdesign:delete-filled"/>
                <Icon icon="flowbite:profile-card-solid" />
                <Icon icon="fa7-solid:magnifying-glass-plus" />
                <Icon icon="material-symbols:document-search-rounded"/>
                <Icon icon="mdi:clipboard-text" />
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}

export default ClassroomDetails;
