import "./perfil.style.css"
import { detalhar, logout } from "../../../api/index"
import { useGlobalUser } from "../../../context/user.context"
import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"

export function Perfil(){

    const [userDetails, setUserDetails] = useState({id: '', nome: '', telefone: '', email: '', fotoUrl: '', funcao: '', criadoEm: '', atualizadoEm: ''})
    const [user, setUser] = useGlobalUser()
    const [erro, setErro] = useState()
    const navigate = useNavigate()

    async function getUser(){
        try{
            const response = await detalhar()
            setUserDetails(oldUser => ({
                ...oldUser,
                id: response.id,
                nome: response.nome,
                telefone: response.telefone,
                email: response.email,
                fotoUrl: response.fotoUrl,
                funcao: response.funcao,
                criadoEm: response.criadoEm,
                atualizadoEm: response.atualizadoEm
            }))
            
        }catch (error){
            setErro(error.response.data.message)
        }
    }

    async function getLogout(){
        await logout()
        setUser(null)
    }

    useEffect(()=>{
        if(user == null){
            navigate("/")
        }
        getUser()
    }, [])

    return(
        <>
            <main className="perfil__main">
                <img src={userDetails.fotoUrl}></img>
                <p>Id: {userDetails?.id}</p>
                <p>Nome: {userDetails?.nome}</p>
                <p>Telefone: {userDetails?.telefone}</p>
                <p>email: {userDetails?.email}</p>
                <p>Função: {userDetails?.funcao}</p>
                <p>criado em: {userDetails?.criadoEm}</p>
                <p>atualizado em: {userDetails?.atualizadoEm}</p>
                <button>Alterar usuario</button>
                <button onClick={getLogout}>Sair</button>
            </main>
        </>
    )
}