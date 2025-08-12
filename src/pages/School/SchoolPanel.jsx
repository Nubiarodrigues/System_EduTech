import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuthContext } from '../../contexts/auth/AuthContext';
import useSchoolFindById from "../../hooks/schoolActions/useSchoolFindById";
import styles from './SchoolPanel.module.css';

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
    <div className={styles.container_school}>

      <div className={styles.container_details}>
        <p>Nome da instituição: {school?.name || "Carregando..."}</p>
        <p>
          Endereço:{" "}
          {school?.address
            ? `${school.address.street}, ${school.address.number} - ${school.address.neighborhood}, ${school.address.city} - ${school.address.cep}` : "Carregando..."}
        </p>
        <p>Capacidade: </p>
        <p>Modalidade: </p>
        <p>Oferta: {school?.stages || "Carregando..."}</p>
      </div>

      <button onClick={() => navigate('/register-school')}>
        Cadastrar nova escola
      </button>

    </div>
  )
}

export default SchoolPanel;