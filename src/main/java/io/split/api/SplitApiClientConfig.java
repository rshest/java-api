package io.split.api;


import java.io.IOException;
import java.util.Properties;

/**
 * Configurations for the SplitClient.
 *
 * @author adil
 */
public class SplitApiClientConfig {
    private final String _endpoint;

    private final int _connectionTimeout;
    private final boolean _debugEnabled;

    // To be set during startup
    public static String splitSdkVersion;
    private final int _readTimeout;

    public static Builder builder() {
        return new Builder();
    }

    private SplitApiClientConfig(
            String endpoint,
            int connectionTimeout,
            int readTimeout,
            boolean debugEnabled
    ) {
        _endpoint = endpoint;
        _readTimeout = readTimeout;
        _connectionTimeout = connectionTimeout;
        _debugEnabled = debugEnabled;

        Properties props = new Properties();
        try {
            props.load(this.getClass().getClassLoader().getResourceAsStream("version.properties"));
        } catch (IOException e) {
            throw new IllegalStateException("cannot find client version in classpath", e);
        }
        splitSdkVersion = "undefined";

        if (props.getProperty("sdk.version") != null) {
            splitSdkVersion = "java-" + props.getProperty("sdk.version");
        }
    }

    public String endpoint() {
        return _endpoint;
    }

    public int connectionTimeout() {
        return _connectionTimeout;
    }

    public boolean debugEnabled() {
        return _debugEnabled;
    }

    public int readTimeout() {
        return _readTimeout;
    }

    public static final class Builder {
        private String _endpoint = "https://api.split.io/internal/api/v1/";
        private int _connectionTimeout = 15000;
        private boolean _debugEnabled = false;
        private int _readTimeout = 15000;

        public Builder() {
        }

        /**
         * The rest endpoint that sdk will hit for latest features and segments.
         *
         * @param endpoint MUST NOT be null
         * @return
         */
        public Builder endpoint(String endpoint) {
            _endpoint = endpoint;
            return this;
        }

        /**
         * Http client connection timeout. Default value is 15000ms.
         *
         * @param ms MUST be greater than 0.
         * @return Builder
         */
        public Builder connectionTimeout(int ms) {
            _connectionTimeout = ms;
            return this;
        }

        /**
         * Http client read timeout. Default value is 15000ms.
         *
         * @param ms MUST be greater than 0.
         * @return this Builder instance
         */
        public Builder readTimeout(int ms) {
            _readTimeout = ms;
            return this;
        }

        public Builder enableDebug() {
            _debugEnabled = true;
            return this;
        }

        public SplitApiClientConfig build() {
            if (_connectionTimeout <= 0) {
                throw new IllegalArgumentException("connectionTimeOutInMs must be > 0: " + _connectionTimeout);
            }

            if (_endpoint == null) {
                throw new IllegalArgumentException("endpoint must not be null");
            }

            return new SplitApiClientConfig(
                    _endpoint,
                    _connectionTimeout,
                    _readTimeout,
                    _debugEnabled
            );
        }

    }


}
