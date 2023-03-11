import "./cadastrar.style.css"
import { useState, useEffect } from "react"
import useGlobalUser from "../../../context/user.context"
import { useNavigate } from "react-router-dom"
import { incluir } from "../../../api/index"

export function Cadastrar() {

    const [formInput, setFormInput] = useState({ nome: '', telefone: '', foto: '', funcao: '', email: '', senha: '', erro: '' })
    const [user] = useGlobalUser()
    const navigate = useNavigate()

    function handleChange(event) {

        const { name, value } = event.target
        setFormInput(oldFormInput => ({
            ...oldFormInput,
            [name]: value
        }))
    }

    async function handleSubmit(event) {
        try {
            event.preventDefault()
            const response = await incluir({ nome: formInput.nome, 
                telefone: formInput.telefone, 
                email: formInput.email, 
                senha: formInput.senha, 
                fotoUrl: formInput.foto, 
                funcao: formInput.funcao })
            setFormInput(oldFormInput=> ({
                ...oldFormInput,
                erro: "Usuário cadastrado com sucesso"
            }))
        } catch (error) {
            setFormInput(oldFormInput => ({
                ...oldFormInput,
                erro: error.response.data.message
            }))
        }
    }

    useEffect(() => {
        if (user) {
            navigate("/perfil")
        }
    }, [user])

    return (
        <>
            <main className="cadastrar__main">
                <form className="cadastrar__form" onSubmit={handleSubmit}>
                    <header className="cadastrar__form--header">
                        <h1 className="cadastrar__header--title">Cadastro</h1>
                    </header>
                    <label className="cadastrar__form--label">Nome: </label>
                    <input className="cadastrar__form--input" type="text" onChange={handleChange} name="nome"/>
                    <label className="cadastrar__form--label" >Telefone: </label>
                    <input className="cadastrar__form--input" type="text" onChange={handleChange} name="telefone"/>
                    <label className="cadastrar__form--label">Email: </label>
                    <input className="cadastrar__form--input" type="email" onChange={handleChange} name="email"/>
                    <label className="cadastrar__form--label">Senha: </label>
                    <input className="cadastrar__form--input" type="password" onChange={handleChange} name="senha"/>
                    <label className="cadastrar__form--label" >Foto: </label>
                    <input className="cadastrar__form--input" type="text" onChange={handleChange} name="foto"/>
                    <label className="cadastrar__form--label">Função: </label>
                    <select className="cadastrar__form--input" name="funcao" onChange={handleChange} >
                        <option value="ADMIN">ADMIN</option>
                        <option value="USER">USER</option>
                    </select>

                    <p className="cadastrar__form--error">{formInput.erro}</p>
                    <button className="cadastrar__form--button">Cadastrar</button>
                    <button className="cadastrar__form--button" onClick={() => navigate("/")}>Voltar</button>
                </form>
            </main>
        </>
    )
}