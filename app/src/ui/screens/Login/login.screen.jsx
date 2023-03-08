import "./login.style.css"

export function Login(){
    return(
        <>
            <main className="login__main">
                <form className="login__form">
                    <header className="login__form--header">
                        <h1 className="login__header--title">Login</h1>
                    </header>
                    <label className="login__form--label">Email: </label>
                    <input className="login__form--input" type="email"/>
                    <label className="login__form--label">Senha: </label>
                    <input className="login__form--input" type="password"/>
                    <p className="login__form--error">erro</p>
                    <button className="login__form--button">Entrar</button>
                </form>
            </main>
        </>
    )
}