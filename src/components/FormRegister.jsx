import { useEffect, useState } from "react";
import { useSchoolActions } from '../hooks/schoolActions/useSchoolCreate';
import useCepFind from "../hooks/cepActions/useCepFind";

const FormRegister = () => {

    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [telephone, setTelephone] = useState('');
    const [cnpj, setCnpj] = useState('');

    const [address, setAddress] = useState({
        cep: '',
        street: '',
        number: '',
        complement: '',
        neighborhood: '',
        city: '',
        state: ''
    });

    const [stages, setStages] = useState([]);
    const [typeSchool, setTypeSchool] = useState('');

    const { sendData, loading, error } = useSchoolActions();
    const { findAddress } = useCepFind();

    const [selectedStage, setSelectedStages] = useState('')

    const handleSubmit = async (e) => {
        e.preventDefault();

        console.log({
            name,
            email,
            telephone,
            cnpj,
            address,
            stages,
            typeSchool
        });

        try {
            await sendData({ name, email, telephone, cnpj, address, stages, typeSchool });
            alert("Cadastro realizado com sucesso!!")
        } catch (error) {
            console.error(error.response?.data || error);
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

    const handleAddComponentsAdress = (e) => {
        const { name, value } = e.target;
        setAddress(prev => ({
            ...prev,
            [name]: value
        }));
    }

    useEffect(() => {
        const cep = address.cep.replace(/\D/g, '');
        if (cep.length === 8) {
            findAddress(cep).then((data) => {
                if (data) {
                    setAddress(prev => ({
                        ...prev,
                        street: data.logradouro,
                        neighborhood: data.bairro,
                        city: data.localidade,
                        state: data.uf,
                        complement: data.complemento
                    }));
                }
            });
        }
    }, [address.cep]);

    return (
        <div>

            <h2>Formulário de cadastro</h2>

            <form onSubmit={handleSubmit}>
                <div>
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
                            value={address.cep}
                            onChange={(e) => setAddress(prev => ({ ...prev, cep: e.target.value }))}
                            required />
                    </label>

                    <label>
                        Rua:
                        <input
                            name="street"
                            value={address.street}
                            onChange={handleAddComponentsAdress}
                            required />
                    </label>

                    <label>
                        Número:
                        <input
                            name="number"
                            value={address.number}
                            onChange={handleAddComponentsAdress}
                            required />
                    </label>

                    <label>
                        Bairro:
                        <input
                            name="neighborhood"
                            value={address.neighborhood}
                            onChange={handleAddComponentsAdress}
                            required />
                    </label>

                    <label>
                        Complemento:
                        <input
                            name="complement"
                            value={address.complement}
                            onChange={handleAddComponentsAdress}
                            required />
                    </label>

                    <label>
                        Cidade:
                        <input
                            name="city"
                            value={address.city}
                            onChange={handleAddComponentsAdress}
                            required />
                    </label>

                    <label>
                        Estado:
                        <input
                            name="state"
                            value={address.state}
                            onChange={handleAddComponentsAdress}
                            required />
                    </label>

                    <div>
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

                    <button type="button"  onClick={handleAddStage} disabled={!selectedStage}>Adicionar</button>

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