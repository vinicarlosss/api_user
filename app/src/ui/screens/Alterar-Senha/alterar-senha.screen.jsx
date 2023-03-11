import { useState } from "react"
import { useNavigate } from "react-router-dom"
import { changePassword } from "../../../api"
import "./alterar-senha.style.css"

export function AlterarSenha(){

    const [formInput, setFormInput] = useState({token: '', senha: ''})
    const [requestResult, setRequestResult] = useState('')
    const navigate = useNavigate()

    function handleChange(event){
        const{name, value} = event.target
        setFormInput(oldFormInput => ({
            ...oldFormInput,
            [name]: value
        }))
    }

    async function handleSubmit(event){
        try{
            event.preventDefault()
            await changePassword({password: formInput.senha, token: formInput.token})
            setRequestResult("Senha alterada com sucesso")
        }catch(error){
            setRequestResult(error.response.data.message)
        }
    }

    return(
        <>
            <main className="alterar-senha__main">
                <form className="alterar-senha__form" onSubmit={handleSubmit}>
                    <p className="alterar-senha__form--advice">Você tem 5 minutos para realizar a alteração de senha com o token enviado para o seu email</p>
                    <label className="alterar-senha__form--label">Token: </label>
                    <input className="alterar-senha__form--input" type="text" name="token" onChange={handleChange}/>
                    <label className="alterar-senha__form--label">Senha: </label>
                    <input className="alterar-senha__form--input" type="password" name="senha" onChange={handleChange}/>
                    <p className="alterar-senha__form--request-result">{requestResult}</p>
                    <button className="alterar-senha__form--button">Alterar</button>
                    <button onClick={()=> navigate("/esqueci-senha")} className="alterar-senha__form--button">Voltar</button>
                </form>

            </main>
        </>
    )
}