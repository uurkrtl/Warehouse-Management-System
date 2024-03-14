import './PageNotFound404.css'

function PageNotFound404() {
    return (
        <div className="body">
            <div className="container">
                <h1>404 - Seite nicht gefunden</h1>
                <p>Leider konnten wir die gewünschte Seite nicht finden.</p>
                <p>Klicken Sie <a href="/"> hier um zur Startseite zurückzukehren</a>.</p>
            </div>
        </div>
    );
}

export default PageNotFound404;