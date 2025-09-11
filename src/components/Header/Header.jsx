import { useState } from 'react';
import { useAuthContext } from '../../contexts/auth/AuthContext';
import { UserMenuModal } from '../Modal/UserMenu/UserMenuModal';

export default function Header() {

    const [modalOpen, setModalOpen] = useState(false);

    const { user, isLoadingLoggedUser } = useAuthContext();

    if (isLoadingLoggedUser) return <p>Carregando usuário...</p>;
    if (!user) return <p>Usuário não está logado.</p>;

    const firstName = user.name.split(" ")[0];

    return (
        <header className=''>
            <div className="flex justify-end items-center gap-2 cursor-pointer" onClick={() => setModalOpen(!modalOpen)}>
                
                <div className=''>
                    <p className="font-semibold">Olá, {firstName}!</p>
                    <p className="text-sm">MINHA CONTA</p>
                </div>
            </div>

            <UserMenuModal
                isOpen={modalOpen}
                setOpen={setModalOpen}
                name={user.name}
                role={user.role}
                registration={user.registration}
            />
        </header>

    );
}
