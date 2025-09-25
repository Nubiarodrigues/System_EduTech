import { useState } from "react";
import useAdminCreate from "../../hooks/admin/useAdminCreate";

const AdminForm = () => {

    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [role, setRole] = useState('');
    const [password, setPassword] = useState('');
    const [idSchool, setIdSchool] = useState('');

    const { sendDataAdmin, loading, error } = useAdminCreate();

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await sendDataAdmin({ name, email, role, password, idSchool });
            console.log(response);
            alert("Cadastro realizado com sucesso!");
        } catch {
            alert("Erro ao enviar dados!");
        }
    }

    return (
        <div>
            <h3>Cadastro do Administrador</h3>

            <form onSubmit={handleSubmit}>

                <label>
                    Nome
                    <input
                        type="text"
                        name="name"
                        value={name}
                        onChange={e => setName(e.target.value)}
                        required />
                </label>

                <label>
                    E-mail:
                    <input
                        type="email"
                        name="email"
                        value={email}
                        onChange={e => setEmail(e.target.value)}
                        required />
                </label>

                <label htmlFor="role">Permissão: </label>
                <select id="role" value={role} onChange={e => setRole(e.target.value)}>
                    <option value="0">Selecione</option>
                    <option value="ADMIN">Administrador</option>
                    <option value="OPERATOR">Operador</option>
                </select>

                <label>
                    Senha:
                    <input
                        type="password"
                        name="password"
                        value={password}
                        onChange={e => setPassword(e.target.value)}
                        required />
                </label>

                <label>
                    Unidade Escolar:
                    <input
                        type="number"
                        name="idSchool"
                        value={idSchool}
                        onChange={e => setIdSchool(e.target.value)}
                        required />
                </label>

                <button type='submit' disabled={loading}>
                    {loading ? "Aguarde..." : "Cadastrar"}
                </button>
                {error && <p>Erro: {error.message}</p>}

            </form>
        </div>
    )
}

export default AdminForm