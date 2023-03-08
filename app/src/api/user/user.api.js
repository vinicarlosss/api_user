import { axiosInstance } from "../_base/axios-instance"

export async function detalhar(){
    const response = await axiosInstance.get(
        "usuarios/detalhar/me"
    )
    return response.data
}