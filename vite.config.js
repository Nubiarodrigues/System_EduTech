import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import fs from 'fs'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    https: {
      key: fs.readFileSync('D:/system_edutech/cert_mkcert/localhost-key.pem'),
      cert: fs.readFileSync('D:/system_edutech/cert_mkcert/localhost.pem'),
    },
    port: 5173
  }
})
