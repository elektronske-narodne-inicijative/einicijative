<template>
    <div class="grid">
        <div class="col-12">
            <div class="card">
                <DataTable :value="listaInicijativa" :paginator="true" class="p-datatable-gridlines" :rows="10" dataKey="brInicijative" :rowHover="true" :loading="ucitavaSe" responsiveLayout="scroll">
                    <template #empty> Нема таквих иницијатива</template>
                    <template #loading> Подаци се учитавају, молимо сачекајте. </template>

                    <Column field="idInicijative" header="Детаљи" style="min-width: 1rem" bodyStyle="text-align:center">
                        <template #body="{ data }">
                            <Button type="button" :label="data.idInicijative.toString()" @click="prikaziDetalje($event, data.idInicijative)" style="width: 100%" />
                        </template>
                    </Column>

                    <Column field="nazivInicijative" header="Назив" style="min-width: 25rem" filter>
                        <template #body="{ data }">
                            {{ data.nazivInicijative }}
                        </template>
                    </Column>

                    <Column field="tipInicijative" header="Тип" style="min-width: 7rem" filter>
                        <template #body="{ data }">
                            {{ data.tipInicijative }}
                        </template>
                    </Column>

                    <Column field="datumAktiviranja" header="Започета" dataType="date" style="min-width: 7rem" sortable>
                        <template #body="{ data }">
                            {{ formatDateTime(data.trnZahteva) }}
                        </template>
                    </Column>

                    <Column field="datumAktiviranja" header="Поднета" dataType="date" style="min-width: 7rem" sortable>
                        <template #body="{ data }">
                            {{ formatDateTime(data.trnPodnosenja) }}
                        </template>
                    </Column>

                    <Column field="brInicijatora" header="Бр. иницијатора" style="min-width: 1rem" bodyStyle="text-align:right" sortable>
                        <template #header>
                            <div class="flex-1 text-right" />
                        </template>
                        <template #body="{ data }">
                            {{ formatNumber(data.brInicijatora) }}
                        </template>
                    </Column>

                    <Column field="datumPokretanja" header="Покренута" dataType="date" style="min-width: 7rem" sortable>
                        <template #body="{ data }">
                            {{ formatDate(data.datumPokretanja) }}
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
                </DataTable>
            </div>
        </div>
    </div>
    <OverlayPanel ref="detaljiInicijative" appendTo="body" :showCloseIcon="true" id="detaljiInicijative" style="width: 90%">
        <DetaljiInicijative v-if="this.idTekuceInicijative" :tip-liste="tipListe" :idInicijative="this.idTekuceInicijative" :sifarnici="sifarnici" :jwt="jwt" />
    </OverlayPanel>
</template>

<script>
import DetaljiInicijative from '@/views/skupstina/DetaljiInicijative.vue';

export default {
    components: { DetaljiInicijative },
    props: {
        jwt: {
            type: String,
            required: true,
        },
        ucitavaSe: {
            type: Boolean,
            default: true,
            required: true,
        },
        // "Odobrenje" / "Ishod"
        tipListe: {
            type: String,
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
            idTekuceInicijative: 0,
        };
    },

    methods: {
        formatNumber(value) {
            return value.toLocaleString('sr-RS');
        },
        formatDate(value) {
            if (value !== null) {
                return new Date(Date.parse(value, 'yyyy-mm-dd')).toLocaleDateString('sr-RS', {
                    day: '2-digit',
                    month: '2-digit',
                    year: 'numeric',
                });
            } else {
                return '';
            }
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
        prikaziDetalje(event, idInicijative) {
            this.idTekuceInicijative = idInicijative;
            this.$refs.detaljiInicijative.toggle(event);
        },
    },
};
</script>

<style scoped></style>
