<template>
    <DetaljiJavneInicijative v-if="ucitavamSifarnike == 5 && parseInt(idTekuceInicijative) != 0" :jwt="jwt" :idInicijative="parseInt(idTekuceInicijative)" :sifarnici="sifarnici" />
    <PodrziMojaListaPotpisa v-if="ucitavamSifarnike == 5 && lista" :jwt="jwt" :sifarnici="sifarnici" />
</template>

<script>
import { ref, onMounted } from 'vue';
import PubService from '@/service/PubService';
import DetaljiJavneInicijative from '@/views/glavne/DetaljiJavneInicijative.vue';
import { useRoute, useRouter } from 'vue-router';
import PodrziMojaListaPotpisa from '@/views/podrzi/PodrziMojaListaPotpisa.vue';

export default {
    components: { PodrziMojaListaPotpisa, DetaljiJavneInicijative },

    data() {
        return {
            ucitavamSifarnike: 0,
            sifarnici: {
                fazeObrade: [],
                tipoviInicijativa: [],
                nivoiVlasti: [],
                opstine: [],
                upravniOkruzi: [],
            },
        };
    },
    pubService: null,

    created() {
        this.pubService = new PubService();
    },

    mounted() {
        this.ucitavamSifarnike = 0;
        this.pubService.getFazeObrade().then((data) => {
            this.sifarnici.fazeObrade = data;
            this.ucitavamSifarnike++;
        });
        this.pubService.getTipoviInicijativa().then((data) => {
            this.sifarnici.tipoviInicijativa = data;
            this.ucitavamSifarnike++;
        });
        this.pubService.getNivoiVlasti().then((data) => {
            this.sifarnici.nivoiVlasti = data;
            this.ucitavamSifarnike++;
        });
        this.pubService.getUpravniOkruzi().then((data) => {
            this.sifarnici.upravniOkruzi = data;
            this.ucitavamSifarnike++;
        });
        this.pubService.getOpstine().then((data) => {
            this.sifarnici.opstine = data;
            this.ucitavamSifarnike++;
        });
    },

    setup() {
        const idTekuceInicijative = ref(0);
        const lista = ref(false);
        const jwt = ref('');
        const router = useRouter();
        const route = useRoute();
        onMounted(async () => {
            await router.isReady();
            const parsedParams = {};
            route.hash
                .split('&')
                .map((part) => part.replace(/^#/, ''))
                .forEach((param) => {
                    const parts = param.split('=');
                    parsedParams[parts[0]] = parts[1];
                });
            jwt.value = parsedParams['access_token'];
            if (parsedParams['state'].match(/P-.*/)) {
                idTekuceInicijative.value = parsedParams['state'].substring(2);
                console.log('idTekuceInicijative=' + idTekuceInicijative.value);
            } else if (parsedParams['state'] === 'L') {
                lista.value = true;
            }
        });
        return { jwt, idTekuceInicijative, lista };
    },
    methods: {},
};
</script>

<style scoped></style>
