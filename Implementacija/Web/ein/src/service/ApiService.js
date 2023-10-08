export default class ApiService {
    getHeaders(accessToken) {
        return {
            headers: { Authorization: 'Bearer ' + accessToken },
        };
    }
    getPtpPrefix() {
        return '/niapi/potpisnik';
    }
    getOvlPrefix() {
        return '/niapi/ovlice';
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

    ovlProfil(accessToken) {
        return fetch(this.getOvlPrefix() + '/profil', this.getHeaders(accessToken))
            .then((res) => res.json())
            .then((d) => d)
            .catch((err) => console.error(err));
    }

    ovlDetaljiInicijative(accessToken, idInicijative) {
        return fetch(this.getOvlPrefix() + '/inicijativa/' + idInicijative, this.getHeaders(accessToken))
            .then((res) => res.json())
            .then((d) => d)
            .catch((err) => console.error(err));
    }

    ovlListaZaIshod(accessToken) {
        return fetch(this.getOvlPrefix() + '/inicijative/zaishod', this.getHeaders(accessToken))
            .then((res) => res.json())
            .then((d) => d)
            .catch((err) => console.error(err));
    }

    ovlListaZaOdobrenje(accessToken) {
        return fetch(this.getOvlPrefix() + '/inicijative/zaodobrenje', this.getHeaders(accessToken))
            .then((res) => res.json())
            .then((d) => d)
            .catch((err) => console.error(err));
    }

    ovlOdbijInicijativu(accessToken, idInicijative, obrazlozenje) {
        return fetch(this.getOvlPrefix() + '/inicijativa/' + idInicijative + '/odbij', {
            method: 'PUT',
            headers: {
                Authorization: 'Bearer ' + accessToken,
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ obrazlozenje: obrazlozenje }),
        })
            .then((res) => res.json())
            .then((d) => d)
            .catch((err) => console.error(err));
    }

    ovlOdobriInicijativu(accessToken, idInicijative) {
        return fetch(this.getOvlPrefix() + '/inicijativa/' + idInicijative + '/odobri', {
            method: 'PUT',
            headers: {
                Authorization: 'Bearer ' + accessToken,
                'Content-Type': 'application/json',
            },
        })
            .then((res) => res.json())
            .then((d) => d)
            .catch((err) => console.error(err));
    }

    ovlRegistrujPrihvacenuInicijativu(accessToken, idInicijative, datumSednice, obrazlozenje) {
        return fetch(this.getOvlPrefix() + '/inicijativa/' + idInicijative + '/prihvacena', {
            method: 'PUT',
            headers: {
                Authorization: 'Bearer ' + accessToken,
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ datumSednice: datumSednice, obrazlozenje: obrazlozenje }),
        })
            .then((res) => res.json())
            .then((d) => d)
            .catch((err) => console.error(err));
    }

    ovlRegistrujOdbacenuInicijativu(accessToken, idInicijative, datumSednice, obrazlozenje) {
        return fetch(this.getOvlPrefix() + '/inicijativa/' + idInicijative + '/odbacena', {
            method: 'PUT',
            headers: {
                Authorization: 'Bearer ' + accessToken,
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ datumSednice: datumSednice, obrazlozenje: obrazlozenje }),
        })
            .then((res) => res.json())
            .then((d) => d)
            .catch((err) => console.error(err));
    }
}
