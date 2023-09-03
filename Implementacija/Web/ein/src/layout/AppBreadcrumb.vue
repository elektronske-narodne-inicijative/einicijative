<template>
    <div class="layout-breadcrumb">
        <Breadcrumb :home="home" :model="items"></Breadcrumb>
    </div>
</template>

<script>
export default {
    data() {
        return {
            home: { icon: 'pi pi-home', to: '/' },
            items: [],
        };
    },
    watch: {
        $route() {
            this.watchRouter();
        },
    },
    created() {
        this.watchRouter();
    },
    methods: {
        watchRouter() {
            if (this.$router.currentRoute.value.meta.breadcrumb) {
                this.items = [];
                const x = this.$router.currentRoute.value.meta.breadcrumb[0];
                for (let pro in x) {
                    this.items.push({ label: x[pro] });
                }
            }
        },
    },
};
</script>

<style scoped>
::v-deep(.p-breadcrumb ol li .p-menuitem-link .p-menuitem-text),
::v-deep(.p-breadcrumb ol li .p-menuitem-link .p-menuitem-icon),
::v-deep(.p-breadcrumb ol li.p-menuitem-separator) {
    color: white;
}
</style>
