import { useEffect, useState } from "react";
import { useAuthContext } from "../contexts/auth/AuthContext";
import useSchoolFindById from "../hooks/school/useSchoolFindById";
import { Icon } from "@iconify/react";

const SchoolPanel = () => {
  const { findById } = useSchoolFindById();
  const { user } = useAuthContext();
  const [school, setSchool] = useState(null);

  const typeSchoolMap = {
    PUBLICA: "Pública",
    PRIVADA: "Privada",
  };

  const stagesMap = {
    ENSINO_INFANTIL: "Ensino Infantil",
    ENSINO_FUNDAMENTAL_I: "Ensino Fundamental I",
    ENSINO_FUNDAMENTAL_II: "Ensino Fundamental II",
    ENSINO_MEDIO: "Ensino Médio",
    EDUCACAO_JOVENS_ADULTOS: "Educação de Jovens e Adultos",
  };

  const handleFindBySchool = async () => {
    console.log("Buscando escola com ID:", user?.idSchool);

    try {
      const schoolData = await findById(user.idSchool);
      setSchool(schoolData);
    } catch (error) {
      console.error("Erro ao buscar escola:", error);
    }
  };

  useEffect(() => {
    if (user?.idSchool) {
      handleFindBySchool();
    } else {
      console.log("Usuário ou idSchool indefinido");
    }
  }, [user]);

  return (
    <div className="text-black flex flex-col items-center">

      <div className="flex">
        <button className="bg-blue-600 text-white hover:bg-blue-700 px-4 py-2 rounded cursor-pointer flex items-center gap-2"><Icon icon="ic:outline-edit-note"/> Editar</button>
      </div>

      <div className="bg-white w-[90%] p-4 m-4 border border-[#07EE5F] flex flex-col">
        <div className="mb-4">
          <h2 className="text-[20px]">{school?.name || "Carregando..."}</h2>
        </div>

        <div className="flex items-center gap-2">
          <Icon icon="ic:round-reduce-capacity" />
          <p>{school?.capacity || "Carregando..."}</p>
        </div>

        <div className="flex items-center gap-2">
          <Icon icon="ic:baseline-public" />
          <p>{school ? typeSchoolMap[school.typeSchool] : "Carregando..."}</p>
        </div>

        <div className="flex items-center gap-2">
          <Icon icon="ic:outline-local-offer" />
          <p>
            {school?.stages?.length
              ? school.stages.map((stage) => stagesMap[stage]).join(", ")
              : "Carregando..."}
          </p>
        </div>

        <div className="flex items-center gap-2">
          <Icon icon="ic:outline-email" />
          <p>{school?.email || "Carregando..."}</p>
        </div>
      </div>

      <div className="bg-white m-4 p-4 border border-[#07EE5F] w-[90%]">
        <div className="mb-4">
          <h2 className="text-[18px]">Localização</h2>
          <hr className="w-[7%]" />
        </div>

        <div className="flex items-center gap-2">
          <Icon icon="fluent-mdl2:street" />
          {school?.address ? `${school.address.street}` : "Carregando..."}
        </div>

        <div className="flex items-center gap-2">
          <Icon icon="iconamoon:location" />
          <p>{school?.address ? `${school.address.cep}` : "Carregando"}</p>
        </div>

        <p className="flex items-center gap-2">
          <Icon icon="mdi:location" />
          {school?.address ? `${school.address.city}` : "Carregando"}
        </p>
      </div>

      <div className="border border-[#07EE5F] m-4 p-4 w-[90%]">
        <div className="mb-4">
          <h2 className="text-[18px]">Corpo diretivo</h2>
          <hr className="w-[10%]" />
        </div>

        <p className="font-bold">Diretores</p>
        <div className="flex items-center gap-2 mb-2">
          <Icon icon="mdi:user-tie" />
          <p>Maria da Silva Rodrigues</p>
        </div>

        <p className="font-bold">Secretário</p>
        <div className="flex items-center gap-2 mb-2">
          <Icon icon="mdi:user-tie" />
          <p>Carlos César da Silva</p>
        </div>

        <p className="font-bold">Coordenadores</p>
        <div className="flex items-center gap-2 mb-2">
          <Icon icon="mdi:user-tie" />
          <p>Maria Josileide de Almeida Freitas</p>
        </div>
      </div>
    </div>
  );
};

export default SchoolPanel;
