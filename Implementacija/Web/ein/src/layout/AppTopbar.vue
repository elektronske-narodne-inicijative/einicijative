<template>
    <div class="layout-topbar">
        <button :class="menuButtonClass" @click="onMenuButtonClick">
            <div class="layout-menubutton-icon"></div>
        </button>
        <div class="layout-topbar-grid">
            <button class="layout-logo p-link">
                <img src="/layout/images/logo-white.svg" alt="eInicijative" />
            </button>
            <div class="layout-topbar-grid-column layout-topbar-grid-column-fixed">
                <router-link to="/"> </router-link>
            </div>
            <div class="layout-topbar-grid-column">
                <transition name="layout-menu-container">
                    <AppMenu :model="model" :horizontal="horizontal" :menuHoverActive="menuHoverActive" @menuitem-click="onMenuItemClick" @root-menuitem-click="onRootMenuItemClick" @sidebar-click="onSidebarClick" :isMobile="isMobile"></AppMenu>
                </transition>
            </div>
        </div>
    </div>
</template>

<script>
import AppMenu from './AppMenu.vue';

export default {
    data() {
        return {
            searchText: '',
        };
    },
    emits: ['menu-click', 'usermenu-button-click', 'usermenu-click', 'menuitem-click', 'root-menuitem-click', 'sidebar-click'],
    props: {
        menuActive: Boolean,
        topbarUserMenuActive: Boolean,
        model: Array,
        horizontal: Boolean,
        menuHoverActive: Boolean,
        isMobile: Function,
    },
    methods: {
        onMenuButtonClick(event) {
            this.$emit('menu-click', event);
        },
        onTopbarUserMenuButtonClick(event) {
            this.$emit('usermenu-button-click', event);
        },
        onTopbarUserMenuClick(event) {
            this.$emit('usermenu-click', event);
        },
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
    computed: {
        menuButtonClass() {
            return [
                'layout-menubutton',
                {
                    'layout-menubutton-active': this.menuActive,
                },
            ];
        },
        topbarMenuClass() {
            return [
                'layout-profile-menu fadeInDown ',
                {
                    'layout-profile-menu-active': this.topbarUserMenuActive,
                },
            ];
        },
    },
    components: {
        AppMenu: AppMenu,
    },
};
</script>
