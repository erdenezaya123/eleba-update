module.exports = {
  presets: [
    '@vue/cli-plugin-babel/preset'
    // 可以用 '@vue/app' 它是 @vue/babel-preset-app 的缩写
    // 或者用 '@babel/preset-env' 不过就要搭配 @babel/plugin-transform-runtime
  ]
}
// @vue/cli-plugin-babel 是 vue-cli 的 babel 插件，
// 它默认使用 Babel 7 + babel-loader + @vue/babel-preset-app

// @vue/babel-preset-app 是 vue-cli 默认的 babel 预设，
// 它包含 @babel/preset-env 和 @babel/plugin-transform-runtime

// @babel/preset-env 的作用是根据浏览器目标自动确定要应用的转换和 polyfill ，
// 它的 targets 默认为 .browserslistrc 文件内的配置

// @babel/plugin-transform-runtime 会将 helper 和 polyfill 都改为从一个统一的地方引入，
// 并且引入的对象和全局变量是完全隔离的，避免了对全局变量及其原型的污染