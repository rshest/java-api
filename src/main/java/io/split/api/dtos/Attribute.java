package io.split.api.dtos;

public class Attribute {
    private String id;
    private String trafficTypeId;
    private String dataType;
    private String displayName;
    private String description;
    private boolean isSearchable;

    public Attribute() {

    }

    private Attribute(Builder builder) {
        this.id = builder.id;
        this.displayName = builder.displayName;
        this.dataType = builder.dataType;
        this.description = builder.description;
        this.trafficTypeId = builder.trafficTypeId;
        this.isSearchable = builder.isSearchable;
    }

    public String id() {
        return id;
    }

    public String trafficTypeId() {
        return trafficTypeId;
    }

    public String dataType() {
        return dataType;
    }

    public String displayName() {
        return displayName;
    }

    public String description() {
        return description;
    }

    public boolean searchable() {
        return isSearchable;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTrafficTypeId(String trafficTypeId) {
        this.trafficTypeId = trafficTypeId;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSearchable(boolean searchable) {
        isSearchable = searchable;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(Attribute other) {
        return new Builder(other);
    }

    public static class Builder {
        private String id;
        private String trafficTypeId;
        private String dataType;
        private String displayName;
        private String description;
        private boolean isSearchable;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder trafficType(TrafficType trafficType) {
            this.trafficTypeId = trafficType.id();
            return this;
        }

        public Builder trafficTypeId(String trafficTypeId) {
            this.trafficTypeId = trafficTypeId;
            return this;
        }

        public Builder displayName(String displayName) {
            this.displayName = displayName;
            return this;
        }

        public Builder dataType(String dataType) {
            this.dataType = dataType;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder isSearchable(boolean isSearchable) {
            this.isSearchable = isSearchable;
            return this;
        }

        Builder() {

        }

        Builder(Attribute prototype) {
            id = prototype.id;
            trafficTypeId = prototype.trafficTypeId;
            dataType = prototype.dataType;
            displayName = prototype.displayName;
            description = prototype.description;
            isSearchable = prototype.isSearchable;
        }

        public Attribute build() {
            return new Attribute(this);
        }
    }
}
