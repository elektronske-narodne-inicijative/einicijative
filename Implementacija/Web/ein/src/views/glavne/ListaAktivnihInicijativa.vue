<template>
    <div class="grid">
        <div class="col-12">
            <div class="card">
                <DataTable
                    :value="listaInicijativa"
                    :paginator="true"
                    class="p-datatable-gridlines"
                    :rows="10"
                    dataKey="brInicijative"
                    :rowHover="true"
                    v-model:filters="filters1"
                    :loading="ucitavaSe"
                    :filters="filters1"
                    responsiveLayout="scroll"
                    :globalFilterFields="['nazivInicijative', 'imePrezimeInicijatora']"
                >
                    <template #header>
                        <div class="flex justify-content-between flex-column sm:flex-row">
                            <span class="p-input-icon-left mb-2">
                                <i class="pi pi-search" />
                                <InputText v-model="filters1['global'].value" placeholder="Назив или иницијаторка" style="width: 100%" />
                            </span>
                        </div>
                    </template>
                    <template #empty> Нема таквих иницијатива</template>
                    <template #loading> Подаци се учитавају, молимо сачекајте. </template>

                    <Column field="idInicijative" header="Детаљи" style="min-width: 1rem" bodyStyle="text-align:center">
                        <template #body="{ data }">
                            <Button type="button" :label="data.idInicijative.toString()" @click="prikaziDetalje($event, data.idInicijative)" style="width: 100%" />
                        </template>
                    </Column>

                    <Column field="brPotpisa" header="Потписа" style="min-width: 1rem" bodyStyle="text-align:right" sortable>
                        <template #header>
                            <div class="flex-1 text-right" />
                        </template>
                        <template #body="{ data }">
                            {{ formatNumber(data.brPotpisa) }}
                        </template>
                    </Column>

                    <Column field="nazivInicijative" header="Назив" style="min-width: 25rem" filter>
                        <template #body="{ data }">
                            {{ data.nazivInicijative }}
                        </template>
                        <template #filter="{ filterModel }">
                            <InputText type="text" v-model="filterModel.value" class="p-column-filter" placeholder="Назив" />
                        </template>
                    </Column>

                    <Column field="imePrezimeInicijatora" header="Иницијаторка" style="min-width: 7rem" filter>
                        <template #body="{ data }">
                            {{ data.imePrezimeInicijatora }}
                        </template>
                        <template #filter="{ filterModel }">
                            <InputText type="text" v-model="filterModel.value" class="p-column-filter" placeholder="Иницијаторка" />
                        </template>
                    </Column>

                    <Column field="nivoVlasti" header="Ниво власти" style="min-width: 1rem" sortable>
                        <template #body="{ data }">
                            {{ data.nivoVlasti }}
                        </template>
                    </Column>

                    <Column field="datumAktiviranja" header="Активирана" dataType="date" style="min-width: 7rem" sortable>
                        <template #body="{ data }">
                            {{ formatDate(data.datumAktiviranja) }}
                        </template>
                    </Column>

                    <Column field="brDanaDoZavrsetka" header="Остало дана" style="min-width: 3rem" bodyStyle="text-align:right" sortable>
                        <template #header>
                            <div class="flex-1 text-right" />
                        </template>
                        <template #body="{ data }">
                            {{ formatNumber(data.brDanaDoZavrsetka) }}
                        </template>
                    </Column>
                </DataTable>
            </div>
        </div>
    </div>
    <OverlayPanel ref="detaljiInicijative" appendTo="body" :showCloseIcon="true" id="detaljiInicijative" style="width: 100%; height: 100%">
        <DetaljiJavneInicijative v-if="this.idTekuceInicijative" :idInicijative="this.idTekuceInicijative" :sifarnici="sifarnici" />
    </OverlayPanel>
</template>

<script>
import { FilterOperator, FilterMatchMode } from 'primevue/api';
import DetaljiJavneInicijative from '@/views/glavne/DetaljiJavneInicijative.vue';

export default {
    components: { DetaljiJavneInicijative },
    props: {
        ucitavaSe: {
            type: Boolean,
            default: true,
            required: true,
        },
        listaInicijativa: {
            type: Array,
            required: true,
        },
        sifarnici: {
            type: Object,
            required: true,
        },
    },
    data() {
        return {
            inicijativa: {},
            loading1: true,
            filters1: null,
            idTekuceInicijative: 0,
        };
    },
    created() {
        this.initFilters1();
    },
    mounted() {
        this.loading1 = false;
    },
    methods: {
        initFilters1() {
            this.filters1 = {
                global: { value: null, matchMode: FilterMatchMode.CONTAINS },
                nazivInicijative: { operator: FilterOperator.OR, constraints: [{ value: null, matchMode: FilterMatchMode.CONTAINS }] },
                imePrezimeInicijatora: { operator: FilterOperator.OR, constraints: [{ value: null, matchMode: FilterMatchMode.CONTAINS }] },
            };
        },
        clearFilter1() {
            this.initFilters1();
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
        prikaziDetalje(event, idInicijative) {
            this.idTekuceInicijative = idInicijative;
            this.$refs.detaljiInicijative.toggle(event);
        },
    },
};
</script>

<style scoped></style>
