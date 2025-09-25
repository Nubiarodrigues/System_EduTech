import LoginForm from "../components/forms/LoginForm";
import { Icon } from "@iconify/react";

const Login = () => {
  return (
    <div className="flex">
      <div className="hidden bg-[linear-gradient(to_bottom,#1E3F66,#1A2D59,#0F1B33)] h-screen w-[80%] flex-col lg:block">
        <div className="fixed top-30 left-7 text-[25px] w-[25%] text-white lg:top-15 xl:text-[40px] xl:top-36 xl:left-10 2xl:text-[50px] 2xl:top-45 2xl:left-15">
          <span>
            Organize, acompanhe e
            <span className="text-[#00FFFF]"> fortaleça</span> o ensino
          </span>
        </div>

        <div className="fixed top-60 font-light left-7 text-justify w-[35%] text-white lg:top-45 lg:text-lg xl:top-77 xl:left-10 xl:text-xl 2xl:text-2xl 2xl:top-100 2xl:left-15">
          <span>
            Simplifique a organização da escola, acompanhe cada etapa do ensino
            e contribua para uma educação pública mais eficiente e de qualidade.
          </span>
        </div>

        <div className="flex items-center flex-wrap justify-between w-[90%] gap-4 mx-auto mt-63 text-[13px] font-normal lg:mt-75 xl:mt-103 xl:gap-6 2xl:mt-140 2xl:gap-10">
          <div className="w-[48%] bg-[#E5E5E5]/25 text-white rounded-md text-center p-4 hover:scale-105 hover:bg-[#E5E5E5]/40 transform lg:text-[12px] xl:text-[16px] xl:p-6 2xl:text-[23px] 2xl:p-10">
            <Icon icon="mage:book" className="text-[#00FFFF]" width={20} />
            <p className="text-[#A3CFF5] mb-1">Gestão Escolar</p>
            <span>Organize alunos, turmas e atividades de forma eficiente</span>
          </div>

          <div className="w-[48%] bg-[#E5E5E5]/25 text-white rounded-md text-center p-4 hover:scale-105 hover:bg-[#E5E5E5]/40 transform lg:text-[12px] xl:text-[16px] xl:p-6 2xl:text-[23px] 2xl:p-10">
            <Icon
              icon="icon-park-outline:communication"
              width={20}
              className="text-[#00FFFF]"
            />
            <p className="text-[#A3CFF5] mb-1">Comunicação Interna</p>
            <span>
              Envie avisos e conecte-se com professores, alunos e equipe.
            </span>
          </div>

          <div className="w-[48%] bg-[#E5E5E5]/25 text-white rounded-md text-center p-4 hover:scale-105 hover:bg-[#E5E5E5]/40 transform lg:text-[12px] xl:text-[16px] xl:p-6 2xl:text-[23px] 2xl:p-10">
            <Icon icon="mdi:chart-bar" className="text-[#00FFFF]" width={20} />
            <p className="text-[#A3CFF5] mb-1">Relatório e Monitoramento</p>
            <span>Acompanhe o desempenho e progresso da escola.</span>
          </div>

          <div className="w-[48%] bg-[#E5E5E5]/25 text-white rounded-md text-center p-4 hover:scale-105 hover:bg-[#E5E5E5]/40 transform lg:text-[12px] xl:text-[16px] xl:p-6 2xl:text-[23px] 2xl:p-10">
            <Icon
              icon="hugeicons:tools"
              className="text-[#00FFFF]"
              width={20}
            />
            <p className="text-[#A3CFF5] mb-1">Suporte e Ferramentas</p>
            <span>
              Tenha todas as ferramentas necessárias para otimizar seu dia.
            </span>
          </div>
        </div>
      </div>

      <LoginForm />
    </div>
  );
};

export default Login;
