<template>
    <div class="grid">
        <div v-if="jwt === undefined || jwt === ''" class="col-12">
            <Button type="button" label="Пријава за овлашћена лица" @click="prijaviOvlascenoLice($event)" />
            <p />
            <div class="card">
                <h5>Функције за представнице скупштинских органа</h5>
                <p>
                    Ово је део сајта у коме су функције које користе овлашћена лица Народне Скупштине или покрајинских или општинских скупштина. Приступ овом делу сајта захтева пријаву високог нивоа поузданости преко сервиса
                    <a href="https://eid.gov.rs" target="_blank">eid.gov.rs</a>.
                </p>
            </div>
        </div>
        <div v-if="jwt !== undefined && jwt !== ''" class="col-12">
            <div class="card p-fluid">
                <div class="formgrid grid">
                    <div class="field col">
                        <label for="imePrezime">Ваше име и презиме:</label>
                        <InputText id="imePrezime" type="text" readOnly="true" disabled="true" v-model="profil.imePrezime" />
                    </div>
                    <div class="field col">
                        <label for="emailAdresa">Ваша емаил адреса:</label>
                        <InputText id="emailAdresa" type="text" readOnly="true" disabled="true" v-model="profil.emailAdresa" />
                    </div>
                    <div class="field col">
                        <label for="nivoUprave">Ви представљате ниво управе:</label>
                        <InputText id="nivoUprave" type="text" readOnly="true" disabled="true" v-model="profil.nivoUprave" />
                    </div>
                    <div class="field col">
                        <label for="opisJediniceUprave">(и јединицу управе:)</label>
                        <InputText id="opisJediniceUprave" type="text" readOnly="true" disabled="true" v-model="profil.opisJediniceUprave" />
                    </div>
                </div>
                <h4>Народне иницијативе на чекању:</h4>
                <TabView class="tabview-custom">
                    <TabPanel>
                        <template #header>
                            <i class="pi pi-check"></i>&nbsp;<i class="pi pi-times"></i>
                            <span>&nbsp;&nbsp;Одобрење за прикупљање потписа</span>
                        </template>
                        <ListaInicijativa v-if="ucitavamSifarnike >= 5" :listaInicijativa="listaZaOdobrenje" :sifarnici="sifarnici" :ucitavaSe="listaZaOdobrenjeSeUcitava" tip-liste="Odobrenje" :jwt="jwt" />
                    </TabPanel>
                    <TabPanel>
                        <template #header>
                            <i class="pi pi-thumbs-up"></i>&nbsp;<i class="pi pi-thumbs-down"></i>
                            <span>&nbsp;&nbsp;Исход расправе у скупштини</span>
                        </template>
                        <ListaInicijativa v-if="ucitavamSifarnike >= 5" :listaInicijativa="listaZaIshod" :sifarnici="sifarnici" :ucitavaSe="listaZaIshodSeUcitava" tip-liste="Ishod" :jwt="jwt" />
                    </TabPanel>
                </TabView>
            </div>
        </div>
    </div>
</template>

<script>
import { inject, ref } from 'vue';
import OidcUrlService from '@/service/OidcUrlService';
import ApiService from '@/service/ApiService';
import ListaInicijativa from '@/views/skupstina/ListaInicijativa.vue';
import PubService from '@/service/PubService';

export default {
    components: { ListaInicijativa },
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
        const apiService = new ApiService();
        const profil = ref({ imePrezime: '[Учитава се...]', ucitavaSe: true });
        const jwt = inject('jwt');
        const listaZaOdobrenje = ref({});
        const listaZaOdobrenjeSeUcitava = ref(true);
        const listaZaIshod = ref({});
        const listaZaIshodSeUcitava = ref(true);
        if (jwt.value !== undefined && jwt.value !== '') {
            apiService.ovlProfil(jwt.value).then((data) => {
                if (data === undefined) {
                    // TODO: nije ovlašćeno lice - prikaži "nemate prava..."
                    console.log('Nije uspeo upit profila ovlašćenog lica!');
                } else {
                    profil.value = data;
                }
            });
            apiService.ovlListaZaOdobrenje(jwt.value).then((data) => {
                if (data === undefined) {
                    console.log('Nije uspeo upit liste za odobrenje!');
                } else {
                    listaZaOdobrenje.value = data.inicijativeZaOdobrenje;
                    listaZaOdobrenjeSeUcitava.value = false;
                }
            });
            apiService.ovlListaZaIshod(jwt.value).then((data) => {
                if (data === undefined) {
                    console.log('Nije uspeo upit liste za ishod!');
                } else {
                    listaZaIshod.value = data.inicijativeZaIshod;
                    listaZaIshodSeUcitava.value = false;
                }
            });
        }
        return { jwt, profil, listaZaOdobrenje, listaZaOdobrenjeSeUcitava, listaZaIshod, listaZaIshodSeUcitava };
    },

    methods: {
        prijaviOvlascenoLice(event) {
            const oidcUrlService = new OidcUrlService();
            // redirekt za login
            window.location.href = oidcUrlService.dajOidcProviderUrlPrefixOvl();
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
