export default class PubService {
    getDetaljiInicijative(idInicijative) {
        const brojDir = idInicijative % 100;
        let punoImeDatoteke = '';
        if (brojDir < 10) {
            punoImeDatoteke = '/einicijativa/podaci/inicijative/0' + brojDir + '/inicijativa-' + idInicijative + '.json';
        } else {
            punoImeDatoteke = '/einicijativa/podaci/inicijative/' + brojDir + '/inicijativa-' + idInicijative + '.json';
        }
        return fetch(punoImeDatoteke + '?t=' + Date.now().toString())
            .then((res) => res.json())
            .then((d) => d)
            .catch((err) => console.error(err));
    }

    getStatistikeZaNaslovnuStranu() {
        return fetch('/einicijativa/podaci/zajednicko/naslovna-statistike.json' + '?t=' + Date.now().toString())
            .then((res) => res.json())
            .then((d) => d);
    }

    getAktivneInicijative() {
        return fetch('/einicijativa/podaci/zajednicko/aktivne-inicijative.json' + '?t=' + Date.now().toString())
            .then((res) => res.json())
            .then((d) => d);
    }

    getNeaktivneInicijative() {
        return fetch('/einicijativa/podaci/zajednicko/neaktivne-inicijative.json' + '?t=' + Date.now().toString())
            .then((res) => res.json())
            .then((d) => d);
    }

    getFazeObrade() {
        return fetch('/einicijativa/podaci/zajednicko/faze-obrade.json' + '?t=' + Date.now().toString())
            .then((res) => res.json())
            .then((d) => d);
    }

    getNivoiVlasti() {
        return fetch('/einicijativa/podaci/zajednicko/nivoi-vlasti.json' + '?t=' + Date.now().toString())
            .then((res) => res.json())
            .then((d) => d);
    }

    getOpstine() {
        return fetch('/einicijativa/podaci/zajednicko/opstine.json' + '?t=' + Date.now().toString())
            .then((res) => res.json())
            .then((d) => d);
    }

    getTipoviInicijativa() {
        return fetch('/einicijativa/podaci/zajednicko/tipovi-inicijativa.json' + '?t=' + Date.now().toString())
            .then((res) => res.json())
            .then((d) => d);
    }

    getUpravniOkruzi() {
        return fetch('/einicijativa/podaci/zajednicko/upravni-okruzi.json' + '?t=' + Date.now().toString())
            .then((res) => res.json())
            .then((d) => d);
    }
}
