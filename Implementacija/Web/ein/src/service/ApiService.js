export default class ApiService {
    getHeaders(accessToken) {
        return {
            headers: { Authorization: 'Bearer ' + accessToken },
        };
    }
    getPtpPrefix() {
        return '/niapi/potpisnik';
    }
    ptpDajProfil(accessToken) {
        return fetch(this.getPtpPrefix() + '/profil', this.getHeaders(accessToken))
            .then((res) => res.json())
            .then((d) => d)
            .catch((err) => console.error(err));
    }

    ptpDetaljiPotpisa(accessToken, idInicijative) {
        return fetch(this.getPtpPrefix() + '/inicijativa/' + idInicijative, this.getHeaders(accessToken))
            .then((res) => res.json())
            .then((d) => d)
            .catch((err) => console.error(err));
    }

    ptpListaPotpisa(accessToken) {
        return fetch(this.getPtpPrefix() + '/potpisi', this.getHeaders(accessToken))
            .then((res) => res.json())
            .then((d) => d)
            .catch((err) => console.error(err));
    }

    ptpPotpisiInicijativu(accessToken, idInicijative) {
        return fetch(this.getPtpPrefix() + '/potpis', {
            method: 'POST',
            headers: {
                Authorization: 'Bearer ' + accessToken,
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ idInicijative: idInicijative }),
        })
            .then((res) => res.json())
            .then((d) => d)
            .catch((err) => console.error(err));
    }
}
