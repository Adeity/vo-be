## Spuštění
Nastavte parametry v `application.properties`, nebo v souboru `.env`.

Je potřeba se připojit k SQL databázi. Nastavte parametry `DATASOURCE_URL`, `DATASOURCE_USERNAME` a `DATASOURCE_PASSWORD`

Pokud se jedná o běh na lokále, nastavte parametr `server.servlet.session.cookie.secure=true`, 
`localdev=true`,
`fe_addr_one=localhost:8080`

pro fungování ukládáni dotazníků je potřéba nastavit. `vophp.api.accesstoken`