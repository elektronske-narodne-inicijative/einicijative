<template>
    <div class="layout-menu-container" @click="onSidebarClick">
        <div class="layout-menu-wrapper">
            <AppSubmenu :items="model" class="layout-menu" :mega="false" :horizontal="horizontal" :menuHoverActive="menuHoverActive" :root="true" @menuitem-click="onMenuItemClick" @root-menuitem-click="onRootMenuItemClick" :isMobile="isMobile" />
        </div>
    </div>
</template>

<script>
import AppSubmenu from './AppSubmenu.vue';

export default {
    emits: ['menuitem-click', 'root-menuitem-click', 'sidebar-click'],
    props: {
        model: Array,
        horizontal: Boolean,
        menuHoverActive: Boolean,
        isMobile: Function,
    },
    methods: {
        onMenuItemClick(event) {
            this.$emit('menuitem-click', event);
        },
        onRootMenuItemClick(event) {
            this.$emit('root-menuitem-click', event);
        },
        onSidebarClick(event) {
            this.$emit('sidebar-click', event);
        },
    },
    components: {
        AppSubmenu: AppSubmenu,
    },
};
</script>

<style scoped></style>
