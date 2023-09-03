<template>
    <div class="grid">
        <div class="col-12">
            <div class="card">
                <h5>Народна Иницијатива Бр. {{ det.idInicijative }}</h5>
                <div class="card p-fluid">
                    <div class="field grid">
                        <label for="imePrezimeInicijatora" class="col-12 mb-2 md:col-2 md:mb-0">Иницијатор</label>
                        <div class="col-12 md:col-10">
                            <InputText id="imePrezimeInicijatora" type="text" readOnly="true" v-model="det.imePrezimeInicijatora" />
                        </div>
                    </div>
                    <div class="field grid">
                        <label for="tipInicijative" class="col-12 mb-2 md:col-2 md:mb-0">Тип</label>
                        <div class="col-12 md:col-10">
                            <InputText id="tipInicijative" type="text" readOnly="true" v-model="det.tipInicijative" />
                        </div>
                    </div>
                    <div class="field grid">
                        <label for="nivoVlasti" class="col-12 mb-2 md:col-2 md:mb-0">Ниво власти</label>
                        <div class="col-12 md:col-10">
                            <InputText id="nivoVlasti" type="text" readOnly="true" v-model="det.nivoVlasti" />
                        </div>
                    </div>
                    <div class="field grid">
                        <label for="nazivInicijative" class="col-12 mb-2 md:col-2 md:mb-0">Назив</label>
                        <div class="col-12 md:col-10">
                            <InputText id="nazivInicijative" type="text" readOnly="true" v-model="det.nazivInicijative" />
                        </div>
                    </div>
                    <div class="field grid">
                        <label for="tekstInicijative" class="col-12 mb-2 md:col-2 md:mb-0">Текст</label>
                        <div class="col-12 md:col-10">
                            <Textarea id="tekstInicijative" rows="4" readOnly="true" v-model="det.tekstInicijative" />
                        </div>
                    </div>
                    <div class="field grid">
                        <label for="fazaObrade" class="col-12 mb-2 md:col-2 md:mb-0">Фаза обраде</label>
                        <div class="col-12 md:col-10">
                            <InputText id="fazaObrade" type="text" readOnly="true" v-model="det.fazaObrade" />
                        </div>
                    </div>
                    <div class="field grid">
                        <label for="datumAktiviranja" class="col-12 mb-2 md:col-2 md:mb-0">Активна од</label>
                        <div class="col-12 md:col-10">
                            <InputText id="datumAktiviranja" type="date" readOnly="true" v-model="det.datumAktiviranja" />
                        </div>
                    </div>
                    <div class="field grid">
                        <label for="emailZaKontakt" class="col-12 mb-2 md:col-2 md:mb-0">Контакт емаил</label>
                        <div class="col-12 md:col-10">
                            <InputText id="emailZaKontakt" type="text" readOnly="true" v-model="det.emailZaKontakt" />
                        </div>
                    </div>
                    <div v-if="det.prilozi && det.prilozi.length > 0" class="field grid">
                        <label for="prilozi" class="col-12 mb-2 md:col-2 md:mb-0">Прилози</label>
                        <div class="col-12 md:col-10">
                            <DataTable :value="det.prilozi" class="p-datatable-gridlines" :rows="5" dataKey="sortiranje" :rowHover="true" :loading="det.ucitavaSe" responsiveLayout="scroll">
                                <template #header>
                                    <div class="flex justify-content-between flex-column sm:flex-row"></div>
                                </template>
                                <template #empty> Нема прилога</template>
                                <template #loading> Подаци се учитавају, молимо сачекајте. </template>
                                <Column field="nazivPriloga" header="(прилози се отварају у засебним прозорима)" style="min-width: 25rem">
                                    <template #body="{ data }">
                                        <a :href="data.urlPriloga" target="_blank">
                                            {{ data.nazivPriloga }}
                                        </a>
                                    </template>
                                </Column>
                            </DataTable>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-title">Чланови иницијативног одбора</div>
                        <DataTable :value="det.clanoviInicijativnogOdbora" class="p-datatable-gridlines" :rows="10" dataKey="emailZaKontakt" :rowHover="true" :loading="det.ucitavaSe" responsiveLayout="scroll">
                            <template #header>
                                <div class="flex justify-content-between flex-column sm:flex-row"></div>
                            </template>
                            <template #empty> Нема чланова одбора</template>
                            <template #loading> Подаци се учитавају, молимо сачекајте. </template>
                            <Column field="imePrezime" header="Име и презиме" style="min-width: 25rem">
                                <template #body="{ data }">
                                    {{ data.imePrezime }}
                                </template>
                            </Column>
                            <Column field="emailZaKontakt" header="Емаил адреса за контакт" style="min-width: 25rem">
                                <template #body="{ data }">
                                    {{ data.emailZaKontakt }}
                                </template>
                            </Column>
                            <Column field="godinaRodjenja" header="Година рођења" style="min-width: 4rem" bodyStyle="text-align:right">
                                <template #body="{ data }">
                                    {{ data.godinaRodjenja }}
                                </template>
                            </Column>
                        </DataTable>
                    </div>
                    <div class="grid dashboard-grid">
                        <div class="col-12 md:col-6 overview-box statistike">
                            <div class="card">
                                <div class="card-title">Демографија потписа</div>
                                <DataTable :value="det.potpisiDemografija" class="p-datatable-gridlines" :rows="10" dataKey="opsegGodina" :rowHover="true" :loading="det.ucitavaSe" responsiveLayout="scroll">
                                    <template #header>
                                        <div class="flex justify-content-between flex-column sm:flex-row"></div>
                                    </template>
                                    <template #empty> Нема потписа</template>
                                    <template #loading> Подаци се учитавају, молимо сачекајте. </template>
                                    <Column field="opsegGodina" header="Старост" style="min-width: 25rem">
                                        <template #body="{ data }">
                                            {{ data.opsegGodina }}
                                        </template>
                                    </Column>
                                    <Column field="brojPotpisaZena" header="#мушкарци" style="min-width: 3rem" bodyStyle="text-align:right">
                                        <template #body="{ data }">
                                            {{ formatNumber(data.brojPotpisaZena) }}
                                        </template>
                                    </Column>
                                    <Column field="brojPotpisaMuskaraca" header="#жене" style="min-width: 3rem" bodyStyle="text-align:right">
                                        <template #body="{ data }">
                                            {{ formatNumber(data.brojPotpisaMuskaraca) }}
                                        </template>
                                    </Column>
                                </DataTable>
                            </div>
                        </div>
                        <div class="col-12 md:col-6 overview-box geografija">
                            <div class="card">
                                <div class="card-title">Географија потписа</div>
                                <DataTable :value="det.potpisiGeografija" class="p-datatable-gridlines" :rows="10" dataKey="ID" :rowHover="true" :loading="det.ucitavaSe" responsiveLayout="scroll">
                                    <template #header>
                                        <div class="flex justify-content-between flex-column sm:flex-row"></div>
                                    </template>
                                    <template #empty> Нема потписа</template>
                                    <template #loading> Подаци се учитавају, молимо сачекајте. </template>
                                    <Column field="brojPotpisa" header="#потписа" style="min-width: 3rem" bodyStyle="text-align:right" sortable>
                                        <template #body="{ data }">
                                            {{ formatNumber(data.brojPotpisa) }}
                                        </template>
                                    </Column>
                                    <Column field="idOpstine" header="Општина" style="min-width: 25rem" sortable>
                                        <template #body="{ data }">
                                            {{ imeOpstine(data.idOpstine) }}
                                        </template>
                                    </Column>
                                </DataTable>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import PubService from '@/service/PubService';
import { ref } from 'vue';

export default {
    props: {
        idInicijative: {
            type: Number,
            required: true,
        },
        sifarnici: {
            type: Object,
            required: true,
        },
    },
    data() {
        return {};
    },
    setup(props) {
        const pubService = new PubService();
        const det = ref({ idInicijative: '[Учитава се...]', ucitavaSe: true });
        pubService.getDetaljiInicijative(props.idInicijative).then((data) => {
            det.value = data;
            det.value.ucitavaSe = false;
            console.log('Učitani podaci o inicijativi:', props.idInicijative);
        });
        let opstine = [];
        for (let ops in props.sifarnici.opstine) {
            opstine[props.sifarnici.opstine[ops].idOpstine] = props.sifarnici.opstine[ops].opis;
        }
        return { det, opstine };
    },

    mounted() {},
    methods: {
        imeOpstine(idOpstine) {
            return this.opstine[idOpstine];
        },
        formatNumber(value) {
            return value.toLocaleString('sr-RS');
        },
        formatDate(value) {
            return new Date(Date.parse(value, 'yyyy-mm-dd')).toLocaleDateString('sr-RS', {
                day: '2-digit',
                month: '2-digit',
                year: 'numeric',
            });
        },
    },
};
</script>

<style scoped></style>
