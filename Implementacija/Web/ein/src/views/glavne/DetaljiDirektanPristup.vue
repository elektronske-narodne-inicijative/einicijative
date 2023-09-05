<template>
    <DetaljiJavneInicijative v-if="idTekuceInicijative" :idInicijative="idTekuceInicijative" :sifarnici="sifarnici" />
</template>

<script>
import PubService from '@/service/PubService';
import DetaljiJavneInicijative from '@/views/glavne/DetaljiJavneInicijative.vue';
import { useRoute } from 'vue-router';

export default {
    components: { DetaljiJavneInicijative },

    data() {
        return {
            ucitavamSifarnike: true,
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
        this.pubService.getFazeObrade().then((data) => (this.sifarnici.fazeObrade = data));
        this.pubService.getTipoviInicijativa().then((data) => (this.sifarnici.tipoviInicijativa = data));
        this.pubService.getNivoiVlasti().then((data) => (this.sifarnici.nivoiVlasti = data));
        this.pubService.getUpravniOkruzi().then((data) => (this.sifarnici.upravniOkruzi = data));
        this.pubService.getOpstine().then((data) => (this.sifarnici.opstine = data));
        this.ucitavamSifarnike = false;
    },

    setup() {
        const route = useRoute();
        const idTekuceInicijative = parseInt(route.params.id);
        console.log('idInicijative=' + idTekuceInicijative.toString());
        return { idTekuceInicijative };
    },

    methods: {},
};
</script>

<style scoped></style>
