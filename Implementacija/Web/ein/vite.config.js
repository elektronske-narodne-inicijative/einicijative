import { fileURLToPath, URL } from 'node:url';

import { defineConfig } from 'vite';
import vue from '@vitejs/plugin-vue';

// https://vitejs.dev/config/
export default defineConfig(() => {
    return {
        plugins: [vue()],
        resolve: {
            alias: {
                '@': fileURLToPath(new URL('./src', import.meta.url)),
            },
        },
		server: {
			proxy: {
				'/einicijativa': {
					target: 'https://test-einicijativa.one/',
					changeOrigin: true,
					secure: true
				},
				'/niapi': {
					target: 'https://test-einicijativa.one/',
					changeOrigin: true,
					secure: true
				}
			}
		}
    };
});
