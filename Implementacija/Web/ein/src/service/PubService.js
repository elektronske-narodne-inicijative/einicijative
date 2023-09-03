export default class PubService {
    getDetaljiInicijative(idInicijative) {
        const brojDir = idInicijative % 100;
        let punoImeDatoteke = '';
        if (brojDir < 10) {
            punoImeDatoteke = 'einicijativa/podaci/inicijative/0' + brojDir + '/inicijativa-' + idInicijative + '.json';
        } else {
            punoImeDatoteke = 'einicijativa/podaci/inicijative/' + brojDir + '/inicijativa-' + idInicijative + '.json';
        }
        return fetch(punoImeDatoteke)
            .then((res) => res.json())
            .then((d) => d);
    }

    getStatistikeZaNaslovnuStranu() {
        return fetch('einicijativa/podaci/zajednicko/naslovna-statistike.json')
            .then((res) => res.json())
            .then((d) => d);
    }

    getAktivneInicijative() {
        return fetch('einicijativa/podaci/zajednicko/aktivne-inicijative.json')
            .then((res) => res.json())
            .then((d) => d);
    }

    getNeaktivneInicijative() {
        return fetch('einicijativa/podaci/zajednicko/neaktivne-inicijative.json')
            .then((res) => res.json())
            .then((d) => d);
    }

    getFazeObrade() {
        return fetch('einicijativa/podaci/zajednicko/faze-obrade.json')
            .then((res) => res.json())
            .then((d) => d);
    }

    getNivoiVlasti() {
        return fetch('einicijativa/podaci/zajednicko/nivoi-vlasti.json')
            .then((res) => res.json())
            .then((d) => d);
    }

    getOpstine() {
        return fetch('einicijativa/podaci/zajednicko/opstine.json')
            .then((res) => res.json())
            .then((d) => d);
    }

    getTipoviInicijativa() {
        return fetch('einicijativa/podaci/zajednicko/tipovi-inicijativa.json')
            .then((res) => res.json())
            .then((d) => d);
    }

    getUpravniOkruzi() {
        return fetch('einicijativa/podaci/zajednicko/upravni-okruzi.json')
            .then((res) => res.json())
            .then((d) => d);
    }
}
