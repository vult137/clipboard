module.exports = {
    devServer: {
        proxy: {
            "/apis": {
                target: "http://localhost:81",
                changeOrigin: true,
                ws:true,
                pathRewrite: {
                    "/apis/": ''
                }
            }
        }
    }
};