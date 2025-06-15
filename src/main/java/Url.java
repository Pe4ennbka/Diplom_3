public enum Url {
    MAIN("https://stellarburgers.nomoreparties.site/"),
    PersonalAccountAuto("https://stellarburgers.nomoreparties.site/account/profile"),
    Registration("https://stellarburgers.nomoreparties.site/register"),
    LogIn("https://stellarburgers.nomoreparties.site/login"),
    ForgotPassword("https://stellarburgers.nomoreparties.site/forgot-password");

    private final String url;

    Url(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
