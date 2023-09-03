<template>
    <div class="exception-body error">
        <div class="exception-panel">
            <div class="exception-image">
                <img src="/layout/images/exception/icon-error.png" alt="sapphire" />
            </div>

            <div class="exception-detail">
                <h1>ERROR OCCURED</h1>
                <p>Something went wrong.</p>
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
