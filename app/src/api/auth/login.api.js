import { axiosInstance } from "../_base/axios-instance"

export async function login({ username, password }) {
  const response = await axiosInstance.post(
    "/login",
    {},
    {
      auth: {
        username,
        password,
      },
    }
  )
  return response.data
}

export async function logout() {
  return await axiosInstance.post("/logout")
}