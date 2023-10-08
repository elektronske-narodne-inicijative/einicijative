export default class OidcUrlService {
    dajOidcProviderUrlPrefix(stranica) {
        return (
            'https://dev-3l2ntuj6cqt60dix.eu.auth0.com/authorize?response_type=token&client_id=FNqheDYYX8A0raqebQdm631vnwIuGre7&redirect_uri=' +
            encodeURIComponent(window.location.protocol) +
            '%2F%2F' +
            encodeURIComponent(window.location.host) +
            '%2F' +
            stranica +
            '&audience=https%3A%2F%2Ftest-einicijativa.one%2Fniapi'
        );
    }
    dajOidcProviderUrlPrefixPtp() {
        return this.dajOidcProviderUrlPrefix('oidcptp') + '&state=';
    }
    dajOidcUrlZaPotpis(idInicijative) {
        return this.dajOidcProviderUrlPrefixPtp('oidcptp') + 'P-' + idInicijative.toString();
    }
    dajOidcUrlZaListuPotpisa() {
        return this.dajOidcProviderUrlPrefixPtp('oidcptp') + 'PL';
    }
    dajOidcProviderUrlPrefixOvl() {
        return this.dajOidcProviderUrlPrefix('oidcovl');
    }
}
