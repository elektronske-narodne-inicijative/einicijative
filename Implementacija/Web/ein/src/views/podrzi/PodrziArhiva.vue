<template>
    <div>
        <div class="card">
            <TabView class="tabview-custom">
                <TabPanel>
                    <template #header>
                        <i class="pi pi-folder"></i>
                        <span>&nbsp;&nbsp;Покренуте ({{ brojPokrenutih }})</span>
                    </template>
                    <JavnaListaInicijativa :listaInicijativa="neaktivneInicijative" :sifarnici="sifarnici" />
                </TabPanel>
                <TabPanel>
                    <template #header>
                        <i class="pi pi-folder"></i>
                        <span>&nbsp;&nbsp;Усвојене ({{ brojUsvojenih }})</span>
                    </template>
                    <JavnaListaInicijativa :listaInicijativa="neaktivneInicijative" :sifarnici="sifarnici" />
                </TabPanel>
                <TabPanel>
                    <template #header>
                        <i class="pi pi-folder"></i>
                        <span>&nbsp;&nbsp;Одбијене ({{ brojOdbijenih }})</span>
                    </template>
                    <JavnaListaInicijativa :listaInicijativa="neaktivneInicijative" :sifarnici="sifarnici" />
                </TabPanel>
                <TabPanel>
                    <template #header>
                        <i class="pi pi-folder"></i>
                        <span>&nbsp;&nbsp;Неуспешне ({{ brojNeuspesnih }})</span>
                    </template>
                    <JavnaListaInicijativa :listaInicijativa="neaktivneInicijative" :sifarnici="sifarnici" />
                </TabPanel>
                <TabPanel>
                    <template #header>
                        <i class="pi pi-folder"></i>
                        <span>&nbsp;&nbsp;Повучене ({{ brojPovucenih }})</span>
                    </template>
                    <JavnaListaInicijativa :listaInicijativa="neaktivneInicijative" :sifarnici="sifarnici" />
                </TabPanel>
            </TabView>
        </div>
    </div>
</template>

<script>
import JavnaListaInicijativa from '@/views/glavne/ListaNeaktivnihInicijativa.vue';
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
            neaktivneInicijative: [],
            brojPokrenutih: 0,
            brojUsvojenih: 0,
            brojOdbijenih: 0,
            brojNeuspesnih: 0,
            brojPovucenih: 0,
        };
    },
    pubService: null,

    created() {
        this.pubService = new PubService();
    },

    mounted() {
        this.pubService.getNeaktivneInicijative().then((data) => {
            this.neaktivneInicijative = data;
        });
        this.ucitavamInicijative = false;
    },

    methods: {},
};
</script>

<style scoped></style>
