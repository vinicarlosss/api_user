import { createBrowserRouter } from 'react-router-dom'
import * as uiScreens from '../ui/index'

export const router = createBrowserRouter([
    {
        path: "/",
        element: <uiScreens.Login/>
    }
])