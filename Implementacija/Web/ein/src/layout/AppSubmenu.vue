<template>
    <ul role="menu">
        <template v-for="(item, i) of items">
            <li v-if="visible(item) && !item.separator" :key="item.label || i" :class="[{ 'active-menuitem': activeIndex === i && !item.to && !item.disabled }]" role="none">
                <router-link
                    v-if="item.to"
                    :to="item.to"
                    :style="item.style"
                    :class="[item.class, 'p-ripple', { 'p-disabled': item.disabled }]"
                    :target="item.target"
                    exact
                    @click="onMenuItemClick($event, item, i)"
                    @mouseenter="onMenuItemMouseEnter(i)"
                    role="menuitem"
                    v-ripple
                >
                    <i :class="['layout-menuitem-icon', item.icon]"></i>
                    <span class="menuitem-text">{{ item.label }}</span>
                    <i v-if="item.items" class="pi pi-fw pi-angle-down layout-submenu-toggler"></i>
                    <span v-if="item.badge" class="menuitem-badge">{{ item.badge }}</span>
                </router-link>
                <a
                    v-if="!item.to"
                    :href="item.url || '#'"
                    :style="item.style"
                    :class="[item.class, 'p-ripple', { 'p-disabled': item.disabled }]"
                    :target="item.target"
                    @click="onMenuItemClick($event, item, i)"
                    @mouseenter="onMenuItemMouseEnter(i)"
                    role="menuitem"
                    v-ripple
                >
                    <i :class="['layout-menuitem-icon', item.icon]"></i>
                    <span class="menuitem-text">{{ item.label }}</span>
                    <i v-if="item.items" class="pi pi-fw pi-angle-down layout-submenu-toggler"></i>
                    <span v-if="item.badge" class="menuitem-badge">{{ item.badge }}</span>
                </a>
                <span class="layout-megamenu-submenu-text" v-if="!root && mega">{{ item.label }}</span>
                <transition name="layout-submenu-container">
                    <div v-if="item.items && (mega ? true : activeIndex === i)" :class="['layout-submenu-container', { 'layout-submenu-megamenu-container': item.mega }]" :style="[{ padding: activeIndex === i ? '' : '0' }]">
                        <appsubmenu
                            v-if="item.items"
                            :class="['layout-submenu', { 'layout-megamenu': item.mega }]"
                            :items="visible(item) && item.items"
                            @menuitem-click="$emit('menuitem-click', $event)"
                            :horizontal="horizontal"
                            :mega="item.mega"
                            :menuHoverActive="menuHoverActive"
                            :isMobile="isMobile"
                        ></appsubmenu>
                    </div>
                </transition>
            </li>
            <li class="p-menu-separator" :style="item.style" v-if="visible(item) && item.separator" :key="'separator' + i" role="separator"></li>
        </template>
    </ul>
</template>
<script>
import EventBus from './event-bus';

export default {
    name: 'appsubmenu',
    emits: ['root-menuitem-click', 'menuitem-click'],
    props: {
        items: Array,
        root: {
            type: Boolean,
            default: false,
        },
        horizontal: Boolean,
        menuHoverActive: Boolean,
        mega: Boolean,
        isMobile: Function,
    },
    data() {
        return {
            activeIndex: null,
        };
    },
    mounted() {
        EventBus.on('reset-active-index', () => {
            if (this.horizontal && !this.isMobile()) {
                this.activeIndex = null;
            }
        });
    },
    methods: {
        onMenuItemClick(event, item, index) {
            if (item.disabled) {
                event.preventDefault();
                return;
            }

            //execute command
            if (item.command) {
                item.command({ originalEvent: event, item: item });
                event.preventDefault();
            }

            if (item.items) {
                event.preventDefault();
            }

            if (this.root) {
                this.$emit('root-menuitem-click', {
                    originalEvent: event,
                    isSameIndex: index === this.activeIndex,
                });
            }

            if (item.items) {
                this.activeIndex = index === this.activeIndex ? null : index;
            }

            this.$emit('menuitem-click', {
                originalEvent: event,
                item: item,
            });
        },
        onMenuItemMouseEnter(index) {
            if (this.root && this.menuHoverActive && this.horizontal && !this.isMobile()) {
                this.activeIndex = index;
            }
        },
        visible(item) {
            return typeof item.visible === 'function' ? item.visible() : item.visible !== false;
        },
    },
};
</script>

<style scoped></style>
