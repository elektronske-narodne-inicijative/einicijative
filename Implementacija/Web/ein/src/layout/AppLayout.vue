<template>
    <div :class="containerClass" @click="onWrapperClick">
        <div class="layout-top">
            <AppTopBar
                @menu-click="onMenuClick"
                @usermenu-button-click="onTopbarUserMenuButtonClick"
                @usermenu-click="onTopbarUserMenuClick"
                :menuActive="menuActive"
                :topbarUserMenuActive="topbarUserMenuActive"
                :model="menu"
                :horizontal="horizontal"
                :menuHoverActive="menuHoverActive"
                @sidebar-click="onSidebarClick"
                @menuitem-click="onMenuItemClick"
                @root-menuitem-click="onRootMenuItemClick"
                :isMobile="isMobile"
            />

            <div class="layout-topbar-separator"></div>

            <AppBreadcrumb></AppBreadcrumb>
        </div>

        <div class="layout-content">
            <router-view />
        </div>

        <AppFooter />

        <div class="layout-mask" v-if="menuActive"></div>
    </div>
</template>

<script>
import AppTopBar from './AppTopbar.vue';
import AppFooter from './AppFooter.vue';
import AppBreadcrumb from './AppBreadcrumb.vue';
import EventBus from './event-bus';

export default {
    data() {
        return {
            horizontal: true,
            topbarSize: 'medium',
            topbarColor: 'layout-topbar-night',
            menuColor: 'layout-menu-light',
            menuActive: false,
            menuHoverActive: false,
            topbarUserMenuActive: false,
            compactMode: false,
            menu: [
                { label: 'Насловна', icon: 'pi pi-fw pi-home', to: '/' },
                { label: 'Подржи', icon: 'pi pi-fw pi-pencil', to: '/podrzi' },
                { label: 'Покрени', icon: 'pi pi-fw pi-cog', to: '/pokreni' },
                { label: 'Скупштина', icon: 'pi pi-fw pi-building', to: '/skupstina' },
            ],
        };
    },
    watch: {
        $route() {
            this.menuActive = false;
            this.$toast.removeAllGroups();
        },
    },
    methods: {
        onWrapperClick() {
            if (!this.menuClick) {
                this.menuActive = false;
                this.unblockBodyScroll();

                if (this.horizontal) {
                    EventBus.emit('reset-active-index');
                    this.menuHoverActive = false;
                }
            }

            if (!this.userMenuClick) {
                this.topbarUserMenuActive = false;
            }

            this.userMenuClick = false;
            this.menuClick = false;
        },
        blockBodyScroll() {
            let blockScrollClass = this.horizontal ? 'blocked-scroll-horizontal' : 'blocked-scroll';
            if (document.body.classList) document.body.classList.add(blockScrollClass);
            else document.body.className += ' ' + blockScrollClass;
        },
        unblockBodyScroll() {
            let blockScrollClass = this.horizontal ? 'blocked-scroll-horizontal' : 'blocked-scroll';
            if (document.body.classList) {
                document.body.classList.remove(blockScrollClass);
            } else {
                document.body.className = document.body.className.replace(new RegExp('(^|\\b)' + blockScrollClass.split(' ').join('|') + '(\\b|$)', 'gi'), ' ');
            }
        },
        onMenuClick(event) {
            this.menuClick = true;

            if (!this.horizontal || this.isMobile()) {
                this.menuActive = !this.menuActive;

                if (this.menuActive) {
                    this.blockBodyScroll();
                } else {
                    this.unblockBodyScroll();
                }
            }

            event.preventDefault();
        },
        onTopbarUserMenuButtonClick(event) {
            this.userMenuClick = true;
            this.topbarUserMenuActive = !this.topbarUserMenuActive;

            event.preventDefault();
        },
        onTopbarUserMenuClick(event) {
            this.userMenuClick = true;

            if (event.target.nodeName === 'BUTTON' || event.target.parentNode.nodeName === 'BUTTON') {
                this.topbarUserMenuActive = false;
            }

            event.preventDefault();
        },
        onSidebarClick() {
            this.menuClick = true;
        },
        onRootMenuItemClick(event) {
            this.menuClick = true;

            if (this.horizontal && this.isMobile()) {
                this.menuHoverActive = event.isSameIndex ? false : true;
            } else {
                this.menuHoverActive = !this.menuHoverActive;
            }
        },
        onMenuItemClick(event) {
            if (event.item && !event.item.items) {
                if (!this.horizontal || this.isMobile()) {
                    this.menuActive = false;
                    this.unblockBodyScroll();
                }

                EventBus.emit('reset-active-index');
                this.menuHoverActive = false;
            }
        },
        isMobile() {
            return window.innerWidth <= 1024;
        },
    },
    computed: {
        containerClass() {
            return [
                'layout-container',
                {
                    'layout-menu-horizontal': this.horizontal,
                    'layout-menu-active': this.menuActive,
                    'layout-top-small': this.topbarSize === 'small',
                    'layout-top-medium': this.topbarSize === 'medium',
                    'layout-top-large': this.topbarSize === 'large',
                    'p-input-filled': this.$primevue.config.inputStyle === 'filled',
                    'p-ripple-disabled': this.$primevue.config.ripple === false,
                },
                this.topbarColor,
                this.menuColor,
            ];
        },
    },
    components: {
        AppTopBar: AppTopBar,
        AppFooter: AppFooter,
        AppBreadcrumb: AppBreadcrumb,
    },
};
</script>
