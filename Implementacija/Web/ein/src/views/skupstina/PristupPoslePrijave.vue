<template>
    <h5>Пријава овлашћеног лица...</h5>
</template>

<script>
import { onMounted, inject } from 'vue';
import { useRoute, useRouter } from 'vue-router';

export default {
    components: {},

    setup() {
        const jwt = inject('jwt');
        const router = useRouter();
        const route = useRoute();
        onMounted(async () => {
            await router.isReady();
            const parsedParams = {};
            route.hash
                .split('&')
                .map((part) => part.replace(/^#/, ''))
                .forEach((param) => {
                    const parts = param.split('=');
                    parsedParams[parts[0]] = parts[1];
                });
            jwt.value = parsedParams['access_token'];
        });
        router.push({ path: 'skupstina' });
        return { jwt };
    },
    methods: {},
};
</script>

<style scoped></style>
