<template>
    <div class="dashboard">
        <h1>Електронске Народне Иницијативе</h1>
        <div class="grid dashboard-grid">
            <div class="col-12 md:col-6 overview-box statistike">
                <div class="card">
                    <div class="card-title">Текуће статистике</div>
                    <p>
                        Број активних: <router-link to="/podrzi"> {{ statistike.brojAktivnih }} </router-link>
                    </p>
                    <p>Број покренутих: {{ statistike.brojPokrenutih }}</p>
                    <p>Број комплетираних: {{ statistike.brojKompletiranih }}</p>
                    <p>Најпопуларније у последњих сат времена:</p>
                    <DataTable :value="statistike.petTrenutnoNajpopularnijih" class="p-datatable-gridlines" :rows="5" dataKey="ID" :rowHover="true" :loading="loading1" responsiveLayout="scroll">
                        <template #header>
                            <div class="flex justify-content-between flex-column sm:flex-row"></div>
                        </template>
                        <template #empty> Нема активних у последњем сату</template>
                        <template #loading> Подаци се учитавају, молимо сачекајте. </template>
                        <Column field="ID" header="Број" style="min-width: 3rem" bodyStyle="text-align:left">
                            <template #body="{ data }">
                                {{ data.ID }}
                            </template>
                        </Column>
                        <Column field="brPotpisa" header="#п/с" style="min-width: 3rem" bodyStyle="text-align:right">
                            <template #body="{ data }">
                                {{ formatNumber(data.brojPotpisa) }}
                            </template>
                        </Column>
                        <Column field="naziv" header="Назив" style="min-width: 25rem">
                            <template #body="{ data }">
                                {{ data.Naziv }}
                            </template>
                        </Column>
                    </DataTable>
                </div>
            </div>
            <div class="col-12 md:col-6 overview-box ukratko">
                <div class="card">
                    <div class="card-title">Укратко</div>
                    <p>
                        Народна Иницијатива је уставна категорија, механизам директне демократије, уређен детаљније
                        <a target="_blank" href="https://www.pravno-informacioni-sistem.rs/SlGlasnikPortal/eli/rep/sgrs/skupstina/zakon/2021/119/1">Законом о Референдуму и Народној Иницијативи</a>.
                    </p>
                    <p>
                        Члан 56:
                        <i>
                            "Народном иницијативом грађани предлажу доношење, измену, допуну или престанак важења Устава, закона, других прописа и општих аката из надлежности Народне скупштине, односно статута, других прописа и општих аката из
                            надлежности скупштине аутономне покрајине и јединице локалне самоуправе и подносе друге предлоге у складу са Уставом и законом, односно статутом аутономне покрајине и јединице локалне самоуправе."</i
                        >
                    </p>
                    <p>
                        Члан 50:
                        <i>
                            "Када најмање 100.000 бирача поднесе захтев за расписивање референдума, председник Народне скупштине доставља тај захтев народним посланицима, Влади и одбору Народне скупштине надлежном за уставна питања (у даљем тексту:
                            надлежни одбор Народне скупштине)."</i
                        >
                    </p>
                    <p>Чланови 53. и 55: <i>"У складу са Законом о Референдуму и Народној Иницијативи, грађани могу уложити захтев за референдум одговарајућој скупштини."</i></p>
                    <p>&nbsp;</p>
                    <p>
                        Идеја је да овај сајт, једном када буде имплементиран у потпуности, у спрези са другим дигиталним услугама државних органа Србије (еид, еуправа), омогући покретање и потписивање електронских народних иницијатива.<br /><br />Више
                        о овом пројекту софтвера отвореног кода (open source software) можете наћи на Github репоу <a href="https://github.com/elektronske-narodne-inicijative/einicijative" target="_blank">einicijative</a>.
                    </p>
                    <p style="color: darkred"><i>*** Ова рана демо верзија сајта привремено користи (осим у цитираним члановима закона изнад) женски род као неутрални/заједнички, као малу показну вежбу родне асиметричности језика. *** </i></p>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import PubService from '@/service/PubService';

export default {
    data() {
        return {
            statistike: {},
            loading1: true,
            pubService: null,
        };
    },

    created() {
        this.pubService = new PubService();
    },

    mounted() {
        this.pubService.getStatistikeZaNaslovnuStranu().then((data) => (this.statistike = data));
        this.loading1 = false;
    },

    methods: {
        formatNumber(value) {
            return value.toLocaleString('sr-RS');
        },
    },
};
</script>

<style lang="scss" scoped></style>
