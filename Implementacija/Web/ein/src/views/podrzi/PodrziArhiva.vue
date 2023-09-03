<template>
    <div>
        <div class="card">
            <TabView class="tabview-custom">
                <TabPanel>
                    <template #header>
                        <i class="pi pi-folder"></i>
                        <span>&nbsp;&nbsp;Покренуте ({{ brojPokrenutih }})</span>
                    </template>
                    <JavnaListaInicijativa :listaInicijativa="pokrenuteInicijative" :sifarnici="sifarnici" />
                </TabPanel>
                <TabPanel>
                    <template #header>
                        <i class="pi pi-folder"></i>
                        <span>&nbsp;&nbsp;Усвојене ({{ brojUsvojenih }})</span>
                    </template>
                    <JavnaListaInicijativa :listaInicijativa="usvojeneInicijative" :sifarnici="sifarnici" />
                </TabPanel>
                <TabPanel>
                    <template #header>
                        <i class="pi pi-folder"></i>
                        <span>&nbsp;&nbsp;Одбијене ({{ brojOdbijenih }})</span>
                    </template>
                    <JavnaListaInicijativa :listaInicijativa="odbijeneInicijative" :sifarnici="sifarnici" />
                </TabPanel>
                <TabPanel>
                    <template #header>
                        <i class="pi pi-folder"></i>
                        <span>&nbsp;&nbsp;Неуспешне ({{ brojNeuspesnih }})</span>
                    </template>
                    <JavnaListaInicijativa :listaInicijativa="neuspesneInicijative" :sifarnici="sifarnici" />
                </TabPanel>
                <TabPanel>
                    <template #header>
                        <i class="pi pi-folder"></i>
                        <span>&nbsp;&nbsp;Повучене ({{ brojPovucenih }})</span>
                    </template>
                    <JavnaListaInicijativa :listaInicijativa="povuceneInicijative" :sifarnici="sifarnici" />
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
            pokrenuteInicijative: [],
            usvojeneInicijative: [],
            odbijeneInicijative: [],
            neuspesneInicijative: [],
            povuceneInicijative: [],
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
            for (let i in data) {
                if (data[i].fazaObrade === 'Покренута (скупљено довољно потписа)') {
                    this.pokrenuteInicijative.push(data[i]);
                } else if (data[i].fazaObrade === 'Комплетирана (скупштина одлучивала)' && data[i].prihvacena) {
                    this.usvojeneInicijative.push(data[i]);
                } else if (data[i].fazaObrade === 'Комплетирана (скупштина одлучивала)') {
                    this.odbijeneInicijative.push(data[i]);
                } else if (data[i].fazaObrade === 'Неуспешна (није скупљено довољно потписа)') {
                    this.neuspesneInicijative.push(data[i]);
                } else {
                    this.povuceneInicijative.push(data[i]);
                }
            }
            this.brojPokrenutih = this.pokrenuteInicijative.length;
            this.brojUsvojenih = this.usvojeneInicijative.length;
            this.brojOdbijenih = this.odbijeneInicijative.length;
            this.brojNeuspesnih = this.neuspesneInicijative.length;
            this.brojPovucenih = this.povuceneInicijative.length;
        });
        this.ucitavamInicijative = false;
    },

    methods: {},
};
</script>

<style scoped></style>
