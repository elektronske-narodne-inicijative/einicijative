<template>
    <div>
        <div class="card">
            <TabView class="tabview-custom">
                <TabPanel>
                    <template #header>
                        <i class="pi pi-folder-open"></i>
                        <span>&nbsp;&nbsp;Активне ({{ brojAktivnihInicijativa }})</span>
                    </template>
                    <p><i>*Активне иницијативе можете потписати и не-електронски, на шалтерима пошта и пореских управа (понесите личну карту или пасош!)</i></p>
                    <PodrziAktivne :sifarnici="sifarnici" />
                </TabPanel>
                <TabPanel>
                    <template #header>
                        <i class="pi pi-folder"></i>
                        <span>&nbsp;&nbsp;Архива</span>
                    </template>
                    <PodrziArhiva :sifarnici="sifarnici" />
                </TabPanel>
            </TabView>
        </div>
    </div>
</template>

<script>
import PodrziArhiva from '@/views/podrzi/PodrziArhiva.vue';
import PodrziAktivne from '@/views/podrzi/PodrziAktivne.vue';
import PubService from '@/service/PubService';

export default {
    components: { PodrziAktivne, PodrziArhiva },

    data() {
        return {
            ucitavamSifarnike: true,
            ucitavamInicijative: true,
            sifarnici: {
                fazeObrade: [],
                tipoviInicijativa: [],
                nivoiVlasti: [],
                opstine: [],
                upravniOkruzi: [],
            },
            aktivneInicijative: [],
            brojAktivnihInicijativa: 0,
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
        this.pubService.getAktivneInicijative().then((data) => {
            this.aktivneInicijative = data;
            this.brojAktivnihInicijativa = data.length;
        });

        this.ucitavamInicijative = false;
    },

    methods: {},
};
</script>

<style scoped></style>
