import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuthContext } from "../../contexts/auth/AuthContext";
import logo_login from "/src/assets/image/logo_login.png";
import logo from "/src/assets/image/logo.png";
import { Icon } from "@iconify/react";

const LoginForm = () => {
  const navigate = useNavigate();

  const { sendLogin } = useAuthContext();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(null);

    try {
      const result = await sendLogin({ email, password });
      console.log(result);

      if (result) {
        navigate("/");
      }
    } catch {
      setError("Falha ao logar.");
    }
  };

  return (
    <div className="bg-[#1A2D59] text-black w-[100%] h-screen flex items-center flex-col justify-center lg:bg-[#F2F2F2]">
      <div className="p-2 flex flex-col items-center">
        <img
          src={logo}
          alt="logo-login"
          className="block lg:hidden w-60 mb-15 md:w-75"
        />

        <img
          src={logo_login}
          alt="logo-login"
          className="hidden lg:block w-60 mb-15 lg:mb-5 lg:w-70 xl:w-90 2xl:w-120"
        />

        <p className="text-white pb-8 lg:text-black xl:text-xl 2xl:text-2xl 2xl:mb-10">
          Faça seu login para continuar
        </p>
      </div>

      <form onSubmit={handleSubmit} className="w-[90%] lg:w-[70%] text-black xl:text-xl 2xl:text-2xl 2xl:mb-40">
        <label>
          Email
          <div className="flex items-center mb-4 border border-[#e1dcdc] rounded p-1 mt-2">
            <div className="h-10 flex items-center">
              <Icon
                icon="mdi:email-variant"
                className="text-[#999999] ml-2 mr-2"
              />
            </div>
            <input
              type="email"
              name="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
              className="font-normal outline-none ml-4 w-[90%] h-10 placeholder:text-[#999999] md:text-[16px] xl:placeholder:text-lg"
              placeholder="exemplo@gmail.com"
            />
          </div>
        </label>

        <label>
          Senha
          <div className="flex items-center border border-[#e1dcdc] rounded p-1 mt-2">
            <div className="h-10 flex items-center">
              <Icon icon="uil:padlock" className="text-[#999999] ml-2 mr-2" />
            </div>

            <input
              type="password"
              name="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
              className="font-normal outline-none ml-4 w-[90%] h-10 placeholder:text-[#999999] md:text-[16px] xl:placeholder:text-lg"
              placeholder="********"
            />
          </div>
        </label>

        <div className="flex mt-4 justify-between items-center xl:text-xl 2xl:text-2xl">
          <div className="w-[60%] text-[#074F8A] mt-2 mb-2">
            <div className="flex items-center w-[20%] cursor-pointer">
              <input
                type="checkbox"
                id="remember"
                className="m-0 appearance-none w-4 h-4 border rounded checked:bg-blue-600 checked:border-blue-600"/>
              <label htmlFor="remember" className="ml-2 text-sm whitespace-nowrap cursor-pointer ">
                Lembrar de mim
              </label>
            </div>
          </div>

          <p className="text-[#074F8A] w-[40%] text-right cursor-pointer text-sm">
            Esqueceu a senha?
          </p>
        </div>

        <button
          type="submit"
          className="bg-[#074F8A] text-white font-medium w-[100%] flex justify-center p-2 mt-4 rounded-md"
        >
          Acessar
        </button>
        {error && <p>Erro: {error.message}</p>}
      </form>
    </div>
  );
};

export default LoginForm;
