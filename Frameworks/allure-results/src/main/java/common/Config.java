package common;

public class Config {
    public static final String PLATFORM_AND_BROWSER = "winChrome";

    /**
     * Clear browser cookies and storage after each iteration
     * if true - clear cookies
     */
    public static final Boolean CLEAR_COOKIES_AND_STORAGE = true;

    /**
     * To keep the browser open after suite
     * if true - browser close
     */
    public static final Boolean HOLD_BROWSER_OPEN = true;

}
