import useClassroomFindAll from "../hooks/classroom/useClassroomFindAll";
import { useEffect, useState } from "react";
import { Icon } from "@iconify/react";
import BtnGeneric from "../components/buttons/BtnGeneric";
import CircleBadge from "../components/indicators/CircleBadge";
import useClassroomFiltered from "../hooks/classroom/useClassroomFiltered";

function ClassesPanel() {
  const { findAll, loading, error, classrooms } = useClassroomFindAll();
  const [filters, setFilters] = useState({ schoolYear:"", shift:"", modality:"" });
  const [appliedFilters, setAppliedFilters] = useState(null);
  const classroomsFiltered = useClassroomFiltered(appliedFilters);

  const handleFilterChange = (key, value) => {
    setFilters((prev) => ({ ...prev, [key]: value }));
  };

  const handleSearch = () => {
    setAppliedFilters(filters); 
  };

  useEffect(() => {
    findAll();
  }, []);

  if (loading) return <p>Carregando...</p>;
  if (error) return <p>Erro: {error.message}</p>;

  const formattedShift = {
    MANHA: "Manhã",
    TARDE: "Tarde",
    NOITE: "Noite",
  };

  return (
    <div className="bg-white flex flex-col gap-4">
      <div className="flex justify-center border border-[#e1dcdc]">
        <div className="bg-white p-4 rounded-full w-24 h-24 flex mt-2 mb-2 items-center justify-center border border-[#e1dcdc]">
          <CircleBadge
            children={classrooms.length}
            size={96}
            bgColor="bg-[#1839E1]"
            textColor="text-white"
          />
        </div>
      </div>

      <div className="flex gap-20">
        <div className="flex flex-col p-4 items-center gap-2">
          <label for="year">Ano</label>
          <select id="year" name="year" value={filters.schoolYear} className="rounded-sm border border-[#e1dcdc] p-2" onChange={(e) => handleFilterChange("schoolYear", e.target.value)}>
            <option value="">Selecione</option>
            <option value="2025">2025</option>
            <option value="2024">2024</option>
            <option value="2023">2023</option>
          </select>
        </div>

        <div className="flex flex-col items-center justify-center gap-2">
          <label for="turn">Turno</label>
          <select id="turn" name="turn" value={filters.shift} className="rounded-sm border border-[#e1dcdc] p-2" onChange={(e) => handleFilterChange("shift", e.target.value)}>
            <option value="">Selecione</option>
            <option value="MANHA">Manhã</option>
            <option value="TARDE">Tarde</option>
            <option value="NOITE">Noite</option>
          </select>
        </div>

        <div className="flex flex-col items-center justify-center gap-2">
          <label for="stage">Modalidade/Etapa</label>
          <select id="stage" name="stage" value={filters.modality} className="rounded-sm border border-[#e1dcdc] p-2" onChange={(e) => handleFilterChange("modality", e.target.value)}>
            <option value="">Selecione</option>
            <option value="ENSINO_INFANTIL">Ensino Infantil</option>
            <option value="ENSINO_FUNDAMENTAL_I">Ensino Fundamental I</option>
            <option value="ENSINO_FUNDAMENTAL_II">Ensino Fundamental II</option>
            <option value="ENSINO_MEDIO">Ensino Médio</option>
            <option value="EDUCACAO_JOVENS_ADULTOS">Educação de Jovens e Adultos</option>
          </select>
        </div>
      </div>

      <div className="flex">
        <input
          type="text"
          placeholder="Busque por turmas..."
          className="font-normal border-[#e1dcdc] w-[50%] h-10 border rounded-md placeholder:text-[#999999] px-4 placeholder:text-[13px] md:text-[16px] xl:placeholder:text-lg"
        />
        <BtnGeneric
          color="search"
          size="md"
          className="ml-4"
          children={"Buscar"}
          onClick={handleSearch}
        />
      </div>

      <div className="border border-[#e1dcdc]">
        <div className="p-4">
          <div className="flex justify-between items-center p-2 border border-[#e1dcdc] bg-[#BABABA] font-semibold mb-2">
            <div>Ano</div>
            <div>Código</div>
            <div>Turno</div>
            <div>Nome</div>
            <div>Capacidade</div>
            <div>Ações</div>
          </div>

          <div>
            {classroomsFiltered?.map((c) => (
              <div key={c.id} className="p-2 flex justify-between border border-[#e1dcdc] hover:bg-[#DFE0DB] cursor-pointer">
                <div>{c.schoolYear}</div>
                <div>{c.id}</div>
                <div>{formattedShift[c.shift]}</div>
                <div>{c.identifierSeries}</div>
                <div>{c.capacity}</div>
                <div className="flex gap-2 items-center">
                  <Icon
                    icon="mingcute:zoom-in-fill"
                    className="text-blue-500"
                  />
                  <Icon icon="mingcute:edit-fill" className="text-green-500" />
                  <Icon
                    icon="material-symbols:delete"
                    className="text-red-500"
                  />
                </div>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}

export default ClassesPanel;
