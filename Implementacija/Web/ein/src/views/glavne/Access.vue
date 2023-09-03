<template>
    <div class="exception-body accessdenied">
        <div class="exception-panel">
            <div class="exception-image">
                <img src="/layout/images/exception/icon-access.png" alt="sapphire" />
            </div>

            <div class="exception-detail">
                <h1>ACCESS DENIED</h1>
                <p>You do not have the necessary permissons.</p>
                <Button label="GO TO DASHBOARD" @click="goDashboard" />
            </div>
        </div>
    </div>
</template>

<script>
export default {
    methods: {
        goDashboard() {
            window.location = '/#/';
        },
    },
};
</script>

<style scoped></style>
