<template>
    <DetaljiJavneInicijative v-if="ucitavamSifarnike == 5 && parseInt(idTekuceInicijative) != 0" :idInicijative="parseInt(idTekuceInicijative)" :sifarnici="sifarnici" />
</template>

<script>
import { ref, onMounted } from 'vue';
import PubService from '@/service/PubService';
import DetaljiJavneInicijative from '@/views/glavne/DetaljiJavneInicijative.vue';
import { useRoute } from 'vue-router';

export default {
    components: { DetaljiJavneInicijative },

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
        onMounted(async () => {
            const route = useRoute();
            idTekuceInicijative.value = route.params.id;
        });
        return { idTekuceInicijative };
    },
    methods: {},
};
</script>

<style scoped></style>
