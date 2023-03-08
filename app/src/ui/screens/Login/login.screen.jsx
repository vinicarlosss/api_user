import "./login.style.css"
import { login } from '../../../api/index'
import { useEffect, useState } from "react"
import { useGlobalUser } from "../../../context/user.context"
import { useNavigate } from "react-router-dom"

export function Login() {

    const [formInput, setFormInput] = useState({ email: '', senha: '', erro: '' })
    const [user, setUser] = useGlobalUser()
    const navigate = useNavigate()

    async function handleSubmit(event) {
        try {
            event.preventDefault()
            const response = await login({ username: formInput.email, password: formInput.senha })
            setFormInput(oldFormInput => ({
                ...oldFormInput,
                erro: ''
            }))
            let user = { email: formInput.email }
            setUser(user)
        } catch (error) {
            setFormInput(oldFormInput => ({
                ...oldFormInput,
                erro: 'Usuário ou senha inválidos'
            }))
        }
    }

    function handleChange(event) {
        const { name, value } = event.target
        setFormInput(oldFormInput => ({
            ...oldFormInput,
            [name]: value
        }))
    }

    useEffect(() => {
        if (user) {
            navigate("/perfil")
        }
    }, [user])
    return (
        <>
            <main className="login__main">
                <form className="login__form" onSubmit={handleSubmit}>
                    <header className="login__form--header">
                        <h1 className="login__header--title">Login</h1>
                    </header>
                    <label className="login__form--label">Email: </label>
                    <input className="login__form--input" type="email" name="email" onChange={handleChange} />
                    <label className="login__form--label">Senha: </label>
                    <input className="login__form--input" type="password" name="senha" onChange={handleChange} />
                    <p className="login__form--error">{formInput.erro}</p>
                    <button className="login__form--button">Entrar</button>
                </form>
                <button onClick={()=>navigate("/cadastrar")} className="login__form--button">Cadastrar</button>
            </main>
        </>
    )
}