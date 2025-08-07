import { useState } from "react";
import { useSchoolActions } from '../../hooks/schoolActions/useSchoolCreate';
import styles from './FormRegister.module.css';

const FormRegister = () => {

    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [telephone, setTelephone] = useState('');
    const [cnpj, setCnpj] = useState('');
    const [cep, setCep] = useState('');
    const [address, setAddress] = useState('');
    const [stages, setStages] = useState([]);
    const [typeSchool, setTypeSchool] = useState('');

    const { sendData, loading, error } = useSchoolActions();

    const [selectedStage, setSelectedStages] = useState('')

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            await sendData({ name, email, telephone, cnpj, cep, address, stages, typeSchool });

            setName("");
            setEmail("");
            setTelephone("");
            setCnpj("");
            setCep("");
            setAddress("");
            setStages([]);
            setTypeSchool("");

            alert("Cadastro enviado com sucesso!");
        } catch {
            alert("Erro ao enviar cadastro!!");
        }
    };

    const handleAddStage = (e) => {
        if (selectedStage && !stages.includes(selectedStage)) {
            e.preventDefault();
            setStages([...stages, selectedStage]);
            setSelectedStages('');
        }
    };

    return (
        <div className={styles.cadastro_container}>

            <h2>Formulário de cadastro</h2>

            <form onSubmit={handleSubmit}>
                <div className={styles.grid}>
                    <label>
                        Nome da unidade:
                        <input
                            type="text"
                            name="name"
                            value={name}
                            placeholder="Escola Estadual de Ensino Fundamental e Médio Padre Roma"
                            onChange={e => setName(e.target.value)}
                            required />
                    </label>

                    <label>
                        E-mail:
                        <input
                            type="email"
                            name="email"
                            value={email}
                            placeholder="exemplo@gmail.com"
                            onChange={e => setEmail(e.target.value)}
                            required />
                    </label>

                    <label>
                        Telefone:
                        <input
                            type="text"
                            name="telephone"
                            value={telephone}
                            placeholder="(83) 9 8651-6605"
                            onChange={e => setTelephone(e.target.value)}
                            required />
                    </label>

                    <label>
                        CNPJ:
                        <input
                            type="text"
                            name="cnpj"
                            value={cnpj}
                            placeholder="00.000.000/0000-00"
                            onChange={e => setCnpj(e.target.value)}
                            required />
                    </label>

                    <label>
                        CEP:
                        <input
                            type="text"
                            name="cep"
                            value={cep}
                            placeholder="00000000"
                            onChange={e => setCep(e.target.value)}
                            required />
                    </label>

                    <label>
                        Endereço:
                        <input
                            type="text"
                            name="address"
                            value={address}
                            onChange={e => setAddress(e.target.value)}
                            required />
                    </label>

                    <div className={styles.container_stages}>
                        {stages.map((stage, index) => (
                            <span key={stage}>
                                {stage}{index < stages.length - 1 ? '  ,  ' : ''}
                            </span>
                        ))}
                    </div>

                    <label htmlFor="stages">Etapas de ensino: </label>
                    <select id="stages" value={selectedStage} onChange={(e) => setSelectedStages(e.target.value)}>
                        <option value="0">Selecione</option>
                        <option value="ENSINO_INFANTIL">ENSINO_INFANTIL</option>
                        <option value="ENSINO_FUNDAMENTAL_I">ENSINO_FUNDAMENTAL_I</option>
                        <option value="ENSINO_FUNDAMENTAL_II">ENSINO_FUNDAMENTAL_II</option>
                        <option value="ENSINO_MEDIO">ENSINO_MEDIO</option>
                        <option value="EDUCACAO_JOVENS_ADULTOS">EDUCACAO_JOVENS_ADULTOS</option>
                    </select>

                    <button type="button" className={styles.btn_stages} onClick={handleAddStage} disabled={!selectedStage}>Adicionar</button>

                    <label htmlFor="typeSchool">Tipo de instituição: </label>
                    <select id="typeSchool" value={typeSchool} onChange={e => setTypeSchool(e.target.value)}>
                        <option value="0">Selecione</option>
                        <option value="PUBLICA">PUBLICA</option>
                        <option value="PRIVADA">PRIVADA</option>
                    </select>
                </div>

                <button type="submit" disabled={loading}>
                    {loading ? "Enviando..." : "Enviar"}
                </button>
                {error && <p>Erro: {error.message}</p>}
            </form>
        </div>
    )
}

export default FormRegister