import {axiosInstance} from "../_base/axios-instance"

export async function getToken({email}){
    const response = await axiosInstance.post("/usuarios/forgot-password",
    {
        email: email
    })
    return response.data
}

export async function changePassword({password, token}){
    const response = await axiosInstance.post("/usuarios/change-password",{
        password: password,
        token: token
    })
    return response.data
}