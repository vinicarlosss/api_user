import { createBrowserRouter } from 'react-router-dom'
import { PrivateRoute } from "./private-route.component" 
import * as uiScreens from '../ui/index'

export const router = createBrowserRouter([
    {
        path: "/",
        element: <uiScreens.Login/>
    },
    {
        path: "/perfil",
        element: <PrivateRoute Screen={uiScreens.Perfil}/>
    }
])