<template>
    <div class="exception-body notfound">
        <div class="exception-panel">
            <div class="exception-image">
                <img src="/layout/images/exception/icon-404.png" alt="sapphire" />
            </div>

            <div class="exception-detail">
                <h1>PAGE NOT FOUND</h1>
                <p>Requested resource is not available.</p>
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
