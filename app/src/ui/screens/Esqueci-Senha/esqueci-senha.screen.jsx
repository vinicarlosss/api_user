import { useState } from "react"
import { useNavigate } from "react-router-dom"
import { getToken } from "../../../api/index"
import loadingImage from "../../../assets/img/Loading.gif"
import "./esqueci-senha.style.css"

export function EsqueciSenha(){

    const [ formInput, setFormInput ] = useState({ email: '' })
    const [requestResult, setRequestResult ] = useState('')
    const [loading, setLoading] =  useState(false)
    const navigate = useNavigate()

    function handleChange(event){
        const{name, value} = event.target
        setFormInput(oldFormInput => ({
            ...oldFormInput,
            [name]: value
        }))
    }

    async function handleSubmit(event){
        setLoading(true)
        try{
            event.preventDefault()
            await getToken({email: formInput.email})
            setRequestResult("")
            setLoading(false)
            navigate("/change-password")
        }catch(error){
            setRequestResult(error.response.data.message)
            setLoading(false)
        }
    }
    
    return(
        <>
            <main className="esqueci-senha__main">
                <form className="esqueci-senha__form" onSubmit={handleSubmit}>
                    <label className="esqueci-senha__form--label">Digite seu email:</label>
                    <input type="email" className="esqueci-senha__form--input" name="email" onChange={handleChange}/>
                    <p className="esqueci-senha__form--request-result">{requestResult}</p>
                    <button className="esqueci-senha__form--button">Enviar</button>
                    <button onClick={()=> navigate("/")} className="esqueci-senha__form--button">Voltar</button>
                </form>
                {loading ? <img className="esqueci-senha__form-loading" src={loadingImage} alt="imagem de carregamento"></img>:null}
            </main>
        </>
    )
}