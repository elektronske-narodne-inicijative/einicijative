import { createRouter, createWebHistory } from 'vue-router';
import AppLayout from '@/layout/AppLayout.vue';
const routes = [
    {
        path: '/',
        component: AppLayout,
        children: [
            {
                path: '/',
                name: 'Насловна',
                exact: true,
                component: () => import('@/views/Naslovna.vue'),
                meta: {
                    breadcrumb: [{ label: 'Насловна' }],
                },
            },
            {
                path: '/podrzi',
                name: 'podrzi',
                component: () => import('@/views/podrzi/PodrziGlavni.vue'),
                meta: {
                    breadcrumb: [{ label: 'Подржи' }],
                },
            },
            {
                path: '/oidcptp',
                name: 'oidcptp',
                component: () => import('@/views/podrzi/PristupPoslePrijave.vue'),
                meta: {
                    breadcrumb: [{ parent: 'Подржи', label: 'Пријављен' }],
                },
                params: true,
                query: true,
            },
            {
                path: '/oidcovl',
                name: 'oidcovl',
                component: () => import('@/views/skupstina/PristupPoslePrijave.vue'),
                meta: {
                    breadcrumb: [{ parent: 'Скупштина', label: 'Пријављен' }],
                },
                params: true,
                query: true,
            },

            {
                path: '/podrzi/:id/detalji',
                name: 'detalji',
                component: () => import('@/views/podrzi/DetaljiDirektanPristup.vue'),
                meta: {
                    breadcrumb: [{ parent: 'Подржи', label: 'Детаљи' }],
                },
                params: true,
            },
            {
                path: '/pokreni',
                name: 'pokreni',
                component: () => import('@/views/pokreni/PokreniGlavni.vue'),
                meta: {
                    breadcrumb: [{ label: 'Покрени' }],
                },
            },
            {
                path: '/skupstina',
                name: 'skupstina',
                component: () => import('@/views/Skupstina/SkupstinaGlavni.vue'),
                meta: {
                    breadcrumb: [{ label: 'Скупштина' }],
                },
            },
        ],
    },
    {
        path: '/error',
        name: 'error',
        component: () => import('@/views/glavne/Error.vue'),
    },
    {
        path: '/notfound',
        name: 'notfound',
        component: () => import('@/views/glavne/NotFound.vue'),
    },
    {
        path: '/access',
        name: 'access',
        component: () => import('@/views/glavne/Access.vue'),
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
    scrollBehavior() {
        return { left: 0, top: 0 };
    },
});

export default router;
