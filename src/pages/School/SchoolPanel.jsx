import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuthContext } from '../../contexts/auth/AuthContext';
import useSchoolFindById from "../../hooks/schoolActions/useSchoolFindById";


const SchoolPanel = () => {

  const navigate = useNavigate();
  const { findById } = useSchoolFindById();
  const { user } = useAuthContext();
  const [school, setSchool] = useState(null);

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
    <div className='text-white block'>

      <div className='bg-amber-950 m-4 p-4'>
        <h2 className='text-2xl'>{school?.name || "Carregando..."}</h2>

        <p>Capacidade: </p>
        <p>Modalidade: </p>
        <p>Oferta: {school?.stages || "Carregando..."}</p>
      </div>

      <div className='bg-amber-950 m-4 p-4'>
        <h2>Localização</h2>
        <p>
          Endereço:{" "}
          {school?.address
            ? `${school.address.street}` : "Carregando..."}
        </p>
        <p>
          Cep:{" "}
          {school?.address ? `${school.address.cep}` : "Carregando"}
        </p>

        <p>
          Cidade:{" "}
          {school?.address ? `${school.address.city}` : "Carregando"}
        </p>
      </div>
    </div>
  )
}

export default SchoolPanel;

