export default (config, env, helpers) => {
  config.output.publicPath = '/f1-schedule-app/';

  if (config.devServer !== undefined) {
    config.devServer.devMiddleware.publicPath = "/f1-schedule-app/";
  }

  config.plugins.push(
    new helpers.webpack.DefinePlugin({
      'process.env.PUBLIC_PATH': JSON.stringify(config.output.publicPath || '/')
    })
  );
};