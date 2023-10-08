<template>
    <div class="grid">
        <div class="col-12">
            <div class="card">
                <h5>Моја листа потписа</h5>
                <Button v-if="jwt === undefined" type="button" label="Пријави се да видиш листу" @click="prijaviPotpisnika($event)" />
                <DataTable v-if="jwt !== undefined && lista !== undefined" :value="lista" :paginator="true" class="p-datatable-gridlines" :rows="10" dataKey="idInicijative" :rowHover="true" :loading="ucitavaSe" responsiveLayout="scroll">
                    <template #empty>Нема таквих иницијатива</template>
                    <template #loading> Подаци се учитавају, молимо сачекајте. </template>

                    <Column field="idInicijative" header="Иницијатива" style="min-width: 1rem" bodyStyle="text-align:right" sortable>
                        <template #header>
                            <div class="flex-1 text-right" />
                        </template>
                        <template #body="{ data }">
                            {{ data.idInicijative }}
                        </template>
                    </Column>

                    <Column field="nazivInicijative" header="Назив" style="min-width: 25rem">
                        <template #body="{ data }">
                            {{ data.nazivInicijative }}
                        </template>
                    </Column>

                    <Column field="trnZavodjenjaPotpisa" header="Потписана" dataType="date" style="min-width: 7rem" sortable>
                        <template #body="{ data }">
                            {{ formatDateTime(data.trnZavodjenjaPotpisa) }}
                        </template>
                    </Column>

                    <Column field="idPotpisa" header="Потпис" style="min-width: 1rem">
                        <template #body="{ data }">
                            {{ data.idPotpisa }}
                        </template>
                    </Column>
                </DataTable>
            </div>
        </div>
    </div>
</template>

<script>
import OidcUrlService from '@/service/OidcUrlService';
import ApiService from '@/service/ApiService';
import { ref } from 'vue';

export default {
    components: {},
    props: {
        jwt: {
            type: String,
            required: false,
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
        const lista = ref({});
        const ucitavaSe = ref(true);
        const apiService = new ApiService();
        if (props.jwt !== undefined) {
            apiService.ptpListaPotpisa(props.jwt).then((data) => {
                if (data === undefined) {
                    console.log('Nije uspeo upit liste potpisa');
                } else {
                    lista.value = data.potpisi;
                    ucitavaSe.value = false;
                }
            });
            return { lista, ucitavaSe };
        }
    },

    methods: {
        prijaviPotpisnika(event) {
            const oidcUrlService = new OidcUrlService();
            // redirekt za login
            window.location.href = oidcUrlService.dajOidcUrlZaListuPotpisa();
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
