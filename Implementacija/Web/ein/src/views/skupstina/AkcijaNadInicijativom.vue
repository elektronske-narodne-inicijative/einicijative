<template>
    <div v-if="tipListe === 'Ishod'" class="card p-fluid">
        <h4>Унос исхода гласања у скупштини</h4>
        <div class="grid">
            <div class="field">
                <label for="datumSednice">Датум Седнице Скупштине:</label>
                <Calendar :showIcon="true" :showButtonBar="true" v-model="datumSednice"></Calendar>
            </div>
            <div class="field col-12">
                <label for="obrazlozenje">Образложење</label>
                <Textarea id="obrazlozenje" rows="8" v-model="obrazlozenje" />
            </div>
        </div>
        <Button type="button" icon="pi pi-thumbs-up" class="mr-2 mb-2" label="Прихваћена" @click="registrujPrihvacenuInicijativu($event, jwt, idInicijative, datumSednice, obrazlozenje)" />
        <p />
        <Button type="button" icon="pi pi-thumbs-down" class="p-button-secondary mr-2 mb-2" label="Одбачена" @click="registrujOdbacenuInicijativu($event, jwt, idInicijative, datumSednice, obrazlozenje)" />
    </div>
    <div v-if="tipListe === 'Odobrenje'" class="card p-fluid">
        <h4>Одобрење за прикупљање потписа:</h4>
        <div class="grid">
            <div class="field col-12">
                <label for="obrazlozenje">Образложење</label>
                <Textarea id="obrazlozenje" rows="8" v-model="obrazlozenje" />
            </div>
        </div>
        <Button type="button" icon="pi pi-check" class="mr-2 mb-2" label="Одобри" @click="odobriInicijativu($event, jwt, idInicijative, obrazlozenje)" />
        <p />
        <Button type="button" icon="pi pi-trash" class="p-button-secondary mr-2 mb-2" label="Одбиј" @click="odbijInicijativu($event, jwt, idInicijative, obrazlozenje)" />
    </div>
</template>

<script>
import ApiService from '@/service/ApiService';
import { inject, ref } from 'vue';
import router from '@/router';
import OidcUrlService from '@/service/OidcUrlService';

export default {
    props: {
        idInicijative: {
            type: Number,
            required: true,
        },
        jwt: {
            type: String,
            required: false,
        },
        tipListe: {
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
        const datumSednice = ref(new Date());
        const obrazlozenje = ref('');
        return { datumSednice, obrazlozenje };
    },

    methods: {
        osveziObrasce() {
            // redirekt za login
            const oidcUrlService = new OidcUrlService();
            window.location.href = oidcUrlService.dajOidcProviderUrlPrefixOvl();
        },
        registrujPrihvacenuInicijativu(event, jwt, idInicijative, datumSednice, obrazlozenje) {
            const apiService = new ApiService();
            const oidcUrlService = new OidcUrlService();
            apiService.ovlRegistrujPrihvacenuInicijativu(jwt, idInicijative, datumSednice, obrazlozenje).then((data) => {
                if (data === undefined) {
                    console.log('Nije uspela registracija prihvaćene inicijative!');
                    // pretpostavka: istekla sesija
                    window.location.href = oidcUrlService.dajOidcProviderUrlPrefixOvl();
                } else {
                    this.osveziObrasce();
                }
            });
        },
        registrujOdbacenuInicijativu(event, jwt, idInicijative, datumSednice, obrazlozenje) {
            const apiService = new ApiService();
            const oidcUrlService = new OidcUrlService();
            apiService.ovlRegistrujOdbacenuInicijativu(jwt, idInicijative, datumSednice, obrazlozenje).then((data) => {
                if (data === undefined) {
                    console.log('Nije uspela registracija odbačene inicijative!');
                    // pretpostavka: istekla sesija
                    window.location.href = oidcUrlService.dajOidcProviderUrlPrefixOvl();
                } else {
                    this.osveziObrasce();
                }
            });
        },
        odobriInicijativu(event, jwt, idInicijative, obrazlozenje) {
            const apiService = new ApiService();
            const oidcUrlService = new OidcUrlService();
            apiService.ovlOdobriInicijativu(jwt, idInicijative, obrazlozenje).then((data) => {
                if (data === undefined) {
                    console.log('Nije uspelo odobrenje inicijative!');
                    // pretpostavka: istekla sesija
                    window.location.href = oidcUrlService.dajOidcProviderUrlPrefixOvl();
                } else {
                    this.osveziObrasce();
                }
            });
        },
        odbijInicijativu(event, jwt, idInicijative, obrazlozenje) {
            const apiService = new ApiService();
            const oidcUrlService = new OidcUrlService();
            apiService.ovlOdbijInicijativu(jwt, idInicijative, obrazlozenje).then((data) => {
                if (data === undefined) {
                    console.log('Nije uspelo odbijanjeinicijative!');
                    // pretpostavka: istekla sesija
                    window.location.href = oidcUrlService.dajOidcProviderUrlPrefixOvl();
                } else {
                    this.osveziObrasce();
                }
            });
        },
    },
};
</script>

<style scoped></style>
