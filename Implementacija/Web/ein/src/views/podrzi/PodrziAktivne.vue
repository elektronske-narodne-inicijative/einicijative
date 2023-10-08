<template>
    <JavnaListaInicijativa :listaInicijativa="aktivneInicijative" :sifarnici="sifarnici" :ucitavaSe="ucitavamInicijative" />
</template>

<script>
import JavnaListaInicijativa from '@/views/podrzi/ListaAktivnihInicijativa.vue';
import PubService from '@/service/PubService';

export default {
    components: { JavnaListaInicijativa },
    props: {
        sifarnici: {
            type: Object,
            required: true,
        },
    },

    data() {
        return {
            ucitavamInicijative: true,
            aktivneInicijative: [],
        };
    },
    pubService: null,

    created() {
        this.pubService = new PubService();
    },

    mounted() {
        this.pubService.getAktivneInicijative().then((data) => {
            this.aktivneInicijative = data;
            this.ucitavamInicijative = false;
        });
    },

    methods: {},
};
</script>

<style scoped></style>
