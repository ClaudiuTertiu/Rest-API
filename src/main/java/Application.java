class Application {

    public static void main(String[] args) {
        Configurations.getRestService()
                .createContexts(Configurations.getRegistrationHandler(), Configurations.getProductHandler(),
                        Configurations.getUserHandler(), Configurations.getProductPostHandler());
        Configurations.getRestService().getServer()
                .setExecutor(null);
        Configurations.getRestService().getServer()
                .start();
    }
}