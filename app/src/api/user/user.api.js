import { axiosInstance } from "../_base/axios-instance"

export async function detalhar(){
    const response = await axiosInstance.get(
        "usuarios/detalhar/me"
    )
    return response.data
}

export async function incluir({nome, telefone, email, senha, fotoUrl, funcao}){
    const response = await axiosInstance.post(
        "/usuarios",
        {
            nome: nome,
            telefone: telefone,
            email: email,
            senha: senha,
            fotoUrl: fotoUrl,
            funcao: funcao
        }
    )
    return response.data
}