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
                        <div class="col-12 md:col-6 overview-box glasovinamapiprocenat">
                            <div class="card">
                                <div class="card-title">Густина: % гласача</div>
                                <GlasoviNaMapi v-if="maxProcenat" :geografija="mapaGeografije" :maxProcenat="maxProcenat" :maxBrojPotpisa="maxBrojPotpisa" :procentualnaToplota="true" />
                            </div>
                        </div>
                        <div class="col-12 md:col-6 overview-box glasovinamapiapsolutno">
                            <div class="card">
                                <div class="card-title">Густина: број потписа</div>
                                <GlasoviNaMapi v-if="maxProcenat" :geografija="mapaGeografije" :maxProcenat="maxProcenat" :maxBrojPotpisa="maxBrojPotpisa" :procentualnaToplota="false" />
                            </div>
                        </div>
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
                                <div class="card-title">Број по општини</div>
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
import { ref, toRaw } from 'vue';
import GlasoviNaMapi from '@/views/glavne/GlasoviNaMapi.vue';

export default {
    components: { GlasoviNaMapi },
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
        function procenat4decimale(a, b) {
            return Number((Math.round(((a * 100) / b) * 10000) / 10000).toFixed(4));
        }
        function dodajGradUMapuGeografije(mapaGeografije, postojiOpstina, idGrada, imeGrada, ukupnoPotpisa, ukupnoGlasaca) {
            mapaGeografije.value[idGrada].nazivOpstine = imeGrada;
            mapaGeografije.value[idGrada].idOpstine = idGrada;
            mapaGeografije.value[idGrada].brojPotpisa = ukupnoPotpisa;
            mapaGeografije.value[idGrada].ukupnoGlasaca = ukupnoGlasaca;
            mapaGeografije.value[idGrada].procenatPotpisalo = procenat4decimale(ukupnoPotpisa, ukupnoGlasaca);
        }
        const pubService = new PubService();
        const urlZaDirektniPristup = window.location.protocol + '//' + window.location.host + '/#/podrzi/' + props.idInicijative.toString() + '/detalji';
        const det = ref({ idInicijative: '[Учитава се...]', ucitavaSe: true });
        const det2 = ref({});
        const naziviOpstina = ref({});
        const brGlasacaUOpstinama = ref({});
        const maxBrojPotpisa = ref(0);
        const maxProcenat = ref(0.0);
        const mapaGeografije = ref({});
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
                    det.value.potpisiGeografija[gsindex].ukupnoGlasaca = brGlasacaUOpstinama.value[det.value.potpisiGeografija[gsindex].idOpstine];
                    det.value.potpisiGeografija[gsindex].procenatPotpisalo = (det.value.potpisiGeografija[gsindex].brojPotpisa * 100.0) / brGlasacaUOpstinama.value[det.value.potpisiGeografija[gsindex].idOpstine];
                    det.value.potpisiGeografija[gsindex].procenatPotpisalo = Number((Math.round(det.value.potpisiGeografija[gsindex].procenatPotpisalo * 10000) / 10000).toFixed(4));
                }
                for (let pgindex in det.value.potpisiGeografija) {
                    mapaGeografije.value[det.value.potpisiGeografija[pgindex].idOpstine] = det.value.potpisiGeografija[pgindex];
                }
                // Beograd
                let postojiOpstina = 0;
                let ukupnoPotpisa = 0;
                let ukupnoGlasaca = 0;
                let opstine = [70092, 70106, 70114, 70122, 70149, 70157, 70165, 70173, 70181, 70190, 70203, 70211, 70220, 70238, 70246, 70254, 71293];
                for (let i in opstine) {
                    if (mapaGeografije.value[opstine[i]]) {
                        postojiOpstina = opstine[i];
                        ukupnoPotpisa = ukupnoPotpisa + mapaGeografije.value[opstine[i]].brojPotpisa;
                        ukupnoGlasaca = ukupnoGlasaca + mapaGeografije.value[opstine[i]].ukupnoGlasaca;
                    }
                }
                if (postojiOpstina !== 0) {
                    mapaGeografije.value[100001] = structuredClone(toRaw(mapaGeografije.value[postojiOpstina]));
                    dodajGradUMapuGeografije(mapaGeografije, postojiOpstina, 100001, 'Београд', ukupnoPotpisa, ukupnoGlasaca);
                }
                // Niš
                postojiOpstina = 0;
                ukupnoPotpisa = 0;
                ukupnoGlasaca = 0;
                opstine = [71285, 71307, 71315, 71323, 71331, 71358];
                for (let i in opstine) {
                    if (mapaGeografije.value[opstine[i]]) {
                        postojiOpstina = opstine[i];
                        ukupnoPotpisa = ukupnoPotpisa + mapaGeografije.value[opstine[i]].brojPotpisa;
                        ukupnoGlasaca = ukupnoGlasaca + mapaGeografije.value[opstine[i]].ukupnoGlasaca;
                    }
                }
                if (postojiOpstina !== 0) {
                    mapaGeografije.value[100002] = structuredClone(toRaw(mapaGeografije.value[postojiOpstina]));
                    dodajGradUMapuGeografije(mapaGeografije, postojiOpstina, 100002, 'Ниш', ukupnoPotpisa, ukupnoGlasaca);
                }
                // Novi Sad
                postojiOpstina = 0;
                ukupnoPotpisa = 0;
                ukupnoGlasaca = 0;
                opstine = [80284, 80519];
                for (let i in opstine) {
                    if (mapaGeografije.value[opstine[i]]) {
                        postojiOpstina = opstine[i];
                        ukupnoPotpisa = ukupnoPotpisa + mapaGeografije.value[opstine[i]].brojPotpisa;
                        ukupnoGlasaca = ukupnoGlasaca + mapaGeografije.value[opstine[i]].ukupnoGlasaca;
                    }
                }
                if (postojiOpstina !== 0) {
                    mapaGeografije.value[100003] = structuredClone(toRaw(mapaGeografije.value[postojiOpstina]));
                    dodajGradUMapuGeografije(mapaGeografije, postojiOpstina, 100003, 'Нови Сад', ukupnoPotpisa, ukupnoGlasaca);
                }
                // Vranje
                postojiOpstina = 0;
                ukupnoPotpisa = 0;
                ukupnoGlasaca = 0;
                opstine = [70432, 71358];
                for (let i in opstine) {
                    if (mapaGeografije.value[opstine[i]]) {
                        postojiOpstina = opstine[i];
                        ukupnoPotpisa = ukupnoPotpisa + mapaGeografije.value[opstine[i]].brojPotpisa;
                        ukupnoGlasaca = ukupnoGlasaca + mapaGeografije.value[opstine[i]].ukupnoGlasaca;
                    }
                }
                if (postojiOpstina !== 0) {
                    mapaGeografije.value[100004] = structuredClone(toRaw(mapaGeografije.value[postojiOpstina]));
                    dodajGradUMapuGeografije(mapaGeografije, postojiOpstina, 100004, 'Врање', ukupnoPotpisa, ukupnoGlasaca);
                }
                // Užice
                postojiOpstina = 0;
                ukupnoPotpisa = 0;
                ukupnoGlasaca = 0;
                opstine = [71145, 71366];
                for (let i in opstine) {
                    if (mapaGeografije.value[opstine[i]]) {
                        postojiOpstina = opstine[i];
                        ukupnoPotpisa = ukupnoPotpisa + mapaGeografije.value[opstine[i]].brojPotpisa;
                        ukupnoGlasaca = ukupnoGlasaca + mapaGeografije.value[opstine[i]].ukupnoGlasaca;
                    }
                }
                if (postojiOpstina !== 0) {
                    mapaGeografije.value[100005] = structuredClone(toRaw(mapaGeografije.value[postojiOpstina]));
                    dodajGradUMapuGeografije(mapaGeografije, postojiOpstina, 100005, 'Ужице', ukupnoPotpisa, ukupnoGlasaca);
                }
                for (let gsindex in mapaGeografije.value) {
                    if (maxBrojPotpisa.value < mapaGeografije.value[gsindex].brojPotpisa) {
                        maxBrojPotpisa.value = mapaGeografije.value[gsindex].brojPotpisa;
                    }
                    if (maxProcenat.value < mapaGeografije.value[gsindex].procenatPotpisalo) {
                        maxProcenat.value = mapaGeografije.value[gsindex].procenatPotpisalo;
                    }
                }
            }
        });
        return { det, mapaGeografije, maxBrojPotpisa, maxProcenat, urlZaDirektniPristup };
    },

    mounted() {},

    methods: {
        prijaviPotpisnika(event, idInicijative) {
            // redirekt za prijavu na eid.gov.rs - test varijanta sa Auth0
            window.location.href =
                'https://dev-3l2ntuj6cqt60dix.eu.auth0.com/authorize?response_type=token&client_id=FNqheDYYX8A0raqebQdm631vnwIuGre7&redirect_uri=https%3A%2F%2Ftest-einicijativa.one%2F&audience=https%3A%2F%2Ftest-einicijativa.one%2Fniapi';
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
