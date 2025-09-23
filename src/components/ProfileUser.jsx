import { useAuthContext } from "../contexts/auth/AuthContext";

const ProfileUser = () => {
  const { user } = useAuthContext();

  if (!user) return <p>Usuário não está logado.</p>;

  return (
    <div>
      <form className={styles.form}>
        <div>
          <button type="button"></button>
        </div>

        <label>
          Nome
          <input type="text" name="name" value={user.name} />
        </label>

        <label>
          E-mail
          <input type="email" name="email" value={user.email} />
        </label>

        <label>
          Permissão
          <select id="role" value={user.role}>
            <option value="ADMIN">Administrador</option>
            <option value="OPERATOR">Operador</option>
          </select>
        </label>

        <label>
          Senha
          <input type="password" name="password" value={user.password} />
        </label>
      </form>
    </div>
  );
};

export default ProfileUser;
