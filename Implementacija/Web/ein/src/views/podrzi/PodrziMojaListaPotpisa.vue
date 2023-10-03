<template>
    <div class="grid">
        <div class="col-12">
            <div class="card">
                <p>
                    Ово је страница где ће пријављена грађанка моћи да види листу свих иницијатива које је потписала у дефинисаном року (нпр. последњих 12 месеци), као механизам контроле процеса и система. Приступ овој страници ће захтевати пријаву
                    високог нивоа поузданости преко сервиса <a href="https://eid.gov.rs" target="_blank">eid.gov.rs</a>.
                </p>
            </div>
        </div>
    </div>
</template>

<script>
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
    pubService: null,

    methods: {},
};
</script>

<style scoped></style>
