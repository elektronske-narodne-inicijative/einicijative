<template>
    <div class="grid">
        <div class="col-12">
            <div class="card">
                <span style="float: initial"><Button v-if="det.fazaObrade === 'Активна (прикупљање потписа у току)'" type="button" label="Пријави се да би потписала" @click="prijaviPotpisnika($event, det.idInicijative)" /></span>
                <span style="float: right"
                    >Линк за директан приступ: <a :href="urlZaDirektniPristup">{{ urlZaDirektniPristup }}</a></span
                >
                <h5>Народна Иницијатива Број {{ det.idInicijative }}</h5>
                <div class="card p-fluid">
                    <div class="field grid">
                        <label for="imePrezimeInicijatora" class="col-12 mb-2 md:col-2 md:mb-0">Иницијаторка</label>
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
                        <div class="card-title">Чланице иницијативног одбора</div>
                        <DataTable :value="det.clanoviInicijativnogOdbora" class="p-datatable-gridlines" :rows="10" dataKey="emailZaKontakt" :rowHover="true" :loading="det.ucitavaSe" responsiveLayout="scroll">
                            <template #header>
                                <div class="flex justify-content-between flex-column sm:flex-row"></div>
                            </template>
                            <template #empty> Нема чланица одбора</template>
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
                                    <Column field="opsegGodina" header="Старост" style="min-width: 5rem">
                                        <template #body="{ data }">
                                            {{ data.opsegGodina }}
                                        </template>
                                    </Column>
                                    <Column field="brojPotpisaZena" header="#М" style="min-width: 3rem" bodyStyle="text-align:right">
                                        <template #header>
                                            <div class="flex-1 text-right" />
                                        </template>
                                        <template #body="{ data }">
                                            {{ formatNumber(data.brojPotpisaZena) }}
                                        </template>
                                    </Column>
                                    <Column field="brojPotpisaMuskaraca" header="#Ж" style="min-width: 3rem" bodyStyle="text-align:right">
                                        <template #header>
                                            <div class="flex-1 text-right" />
                                        </template>
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
                                    <Column field="brojPotpisa" header="#" bodyStyle="text-align:right" sortable>
                                        <template #header>
                                            <div class="flex-1 text-right" />
                                        </template>
                                        <template #body="{ data }">
                                            {{ formatNumber(data.brojPotpisa) }}
                                        </template>
                                    </Column>
                                    <Column field="procenatPotpisalo" header="%" bodyStyle="text-align:right" sortable>
                                        <template #header>
                                            <div class="flex-1 text-right" />
                                        </template>
                                        <template #body="{ data }">
                                            {{ data.procenatPotpisalo }}
                                        </template>
                                    </Column>
                                    <Column field="nazivOpstine" header="Општина" sortable>
                                        <template #body="{ data }">
                                            {{ data.nazivOpstine }}
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
        const urlZaDirektniPristup = window.location.protocol + '//' + window.location.host + '/#/podrzi/' + props.idInicijative.toString() + '/detalji';
        const det = ref({ idInicijative: '[Учитава се...]', ucitavaSe: true });
        const naziviOpstina = ref({});
        const brGlasacaUOpstinama = ref({});
        for (let ops in props.sifarnici.opstine) {
            naziviOpstina.value[props.sifarnici.opstine[ops].idOpstine] = props.sifarnici.opstine[ops].opis;
            brGlasacaUOpstinama.value[props.sifarnici.opstine[ops].idOpstine] = props.sifarnici.opstine[ops].brojRegistrovanihGlasaca;
        }
        pubService.getDetaljiInicijative(props.idInicijative).then((data) => {
            if (data === undefined) {
                det.value.idInicijative = '[' + props.idInicijative + ': Није пронађена]';
                console.log('Nema podataka o inicijativi:', props.idInicijative);
            } else {
                det.value = data;
                for (let gsindex in det.value.potpisiGeografija) {
                    det.value.potpisiGeografija[gsindex].nazivOpstine = naziviOpstina.value[det.value.potpisiGeografija[gsindex].idOpstine];
                    det.value.potpisiGeografija[gsindex].procenatPotpisalo = (det.value.potpisiGeografija[gsindex].brojPotpisa * 100.0) / brGlasacaUOpstinama.value[det.value.potpisiGeografija[gsindex].idOpstine];
                    det.value.potpisiGeografija[gsindex].procenatPotpisalo = (Math.round(det.value.potpisiGeografija[gsindex].procenatPotpisalo * 10000) / 10000).toFixed(4);
                }
            }
        });
        return { det, urlZaDirektniPristup };
    },

    mounted() {},

    methods: {
        prijaviPotpisnika(event, idInicijative) {
            // redirekt za prijavu na eid.gov.rs
            console.log(event, idInicijative);
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
