<template>
    <div class="grid">
        <div class="col-12">
            <div class="card">
                <span style="float: initial"> </span>
                <h5>Народна Иницијатива Број {{ det.idInicijative }}</h5>
                <div class="card p-fluid">
                    <div class="field grid">
                        <label for="inicijator" class="col-12 mb-2 md:col-2 md:mb-0">Иницијаторка</label>
                        <div class="col-12 md:col-10">
                            <InputText id="inicijator" type="text" readOnly="true" v-model="det.inicijator" />
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
                        <label for="trnZahteva" class="col-12 mb-2 md:col-2 md:mb-0">Унос започет</label>
                        <div class="col-12 md:col-10">
                            <InputText id="trnZahteva" type="datetime" readOnly="true" v-model="det.trnZahteva" />
                        </div>
                    </div>
                    <div class="field grid">
                        <label for="trnPodnosenja" class="col-12 mb-2 md:col-2 md:mb-0">Поднета</label>
                        <div class="col-12 md:col-10">
                            <InputText id="trnPodnosenja" type="datetime" readOnly="true" v-model="det.trnPodnosenja" />
                        </div>
                    </div>
                    <div class="field grid">
                        <label for="datumPokretanja" class="col-12 mb-2 md:col-2 md:mb-0">Покренута</label>
                        <div class="col-12 md:col-10">
                            <InputText id="datumPokretanja" type="date" readOnly="true" v-model="det.datumPokretanja" />
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
                    <div class="card">
                        <div class="card-title">Дневник промена</div>
                        <DataTable :value="det.promene" class="p-datatable-gridlines" :rows="10" dataKey="trnPromene" :rowHover="true" :loading="det.ucitavaSe" responsiveLayout="scroll">
                            <template #header>
                                <div class="flex justify-content-between flex-column sm:flex-row"></div>
                            </template>
                            <template #empty> Нема промена</template>
                            <template #loading> Подаци се учитавају, молимо сачекајте. </template>
                            <Column field="trnPromene" header="Датум и време" style="min-width: 10rem">
                                <template #body="{ data }">
                                    {{ formatDateTime(data.trnPromene) }}
                                </template>
                            </Column>
                            <Column field="fazaObrade" header="Фаза обраде" style="min-width: 25rem">
                                <template #body="{ data }">
                                    {{ data.fazaObrade }}
                                </template>
                            </Column>
                            <Column field="detaljiPromene" header="Детаљи промене" style="min-width: 75rem">
                                <template #body="{ data }">
                                    {{ data.detaljiPromene }}
                                </template>
                            </Column>
                        </DataTable>
                    </div>
                </div>
            </div>
            <div class="col-12">
                <AkcijaNadInicijativom v-if="this.idInicijative" :idInicijative="this.idInicijative" :sifarnici="sifarnici" :jwt="jwt" :tipListe="tipListe" />
            </div>
        </div>
    </div>
</template>

<script>
import ApiService from '@/service/ApiService';
import { ref } from 'vue';
import AkcijaNadInicijativom from '@/views/skupstina/AkcijaNadInicijativom.vue';

export default {
    components: { AkcijaNadInicijativom },
    props: {
        idInicijative: {
            type: Number,
            required: true,
        },
        tipListe: {
            type: String,
            required: false,
        },
        jwt: {
            type: String,
            required: false,
        },
        sifarnici: {
            type: Object,
            required: true,
        },
    },

    mounted() {},

    setup(props) {
        const apiService = new ApiService();
        const det = ref({ idInicijative: '[Учитава се...]', ucitavaSe: true });
        if (props.jwt !== undefined) {
            apiService.ovlDetaljiInicijative(props.jwt, props.idInicijative).then((data) => {
                if (data === undefined) {
                    console.log('Nije uspeo upit potpisa za inicijativu:', props.idInicijative);
                } else {
                    det.value = data.detaljiInicijative;
                }
            });
        }
        return { det };
    },

    methods: {
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
        formatDateTime(value) {
            return (
                new Date(Date.parse(value, 'yyyy-mm-dd')).toLocaleDateString('sr-RS', {
                    day: '2-digit',
                    month: '2-digit',
                    year: 'numeric',
                }) +
                ' ' +
                new Date(Date.parse(value, 'yyyy-mm-dd')).toLocaleTimeString('sr-RS')
            );
        },
    },
};
</script>

<style scoped></style>
