package io.split.api.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Attribute {
    private String id;
    private String organizationId;
    private String trafficTypeId;
    private String displayName;
    private String description;
    private String dataType;
    private boolean searchable;

    public Attribute() {
        searchable = false;
    }

    private Attribute(Builder builder) {
        this.id = builder.id;
        this.organizationId = builder.organizationId;
        this.trafficTypeId = builder.trafficTypeId;
        this.displayName = builder.displayName;
        this.description = builder.description;
        this.dataType = builder.dataType;
        this.searchable = builder.searchable;
    }

    @JsonProperty
    public String id() {
        return this.id;
    }

    @JsonProperty
    public String organizationId() {
        return this.organizationId;
    }

    @JsonProperty
    public String trafficTypeId() {
        return this.trafficTypeId;
    }

    @JsonProperty
    public String displayName() {
        return this.displayName;
    }

    @JsonProperty
    public String description() {
        return this.description;
    }

    @JsonProperty
    public String dataType() {
        return this.dataType;
    }

    @JsonProperty("isSearchable")
    public boolean isSearchable() {
        return this.searchable;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public void setTrafficTypeId(String trafficTypeId) {
        this.trafficTypeId = trafficTypeId;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public void setSearchable(boolean searchable) {
        this.searchable = searchable;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(Attribute other) {
        return new Builder(other);
    }

    public static class Builder {
        private String id;
        private String organizationId;
        private String trafficTypeId;
        private String displayName;
        private String description;
        private String dataType;
        private boolean searchable;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder organizationId(String organizationId) {
            this.organizationId = organizationId;
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

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder dataType(String dataType) {
            this.dataType = dataType;
            return this;
        }

        public Builder searchable(boolean searchable) {
            this.searchable = searchable;
            return this;
        }

        Builder() {

        }

        Builder(Attribute prototype) {
            id = prototype.id;
            organizationId = prototype.organizationId;
            trafficTypeId = prototype.trafficTypeId;
            displayName = prototype.displayName;
            description = prototype.description;
            dataType = prototype.dataType;
            searchable = prototype.searchable;
        }

        public Attribute build() {
            return new Attribute(this);
        }
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "id='" + id + '\'' +
                ", organizationId='" + organizationId + '\'' +
                ", trafficTypeId='" + trafficTypeId + '\'' +
                ", displayName='" + displayName + '\'' +
                ", description='" + description + '\'' +
                ", dataType='" + dataType + '\'' +
                ", searchable=" + searchable +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attribute attribute = (Attribute) o;

        if (searchable != attribute.searchable) return false;
        if (id != null ? !id.equals(attribute.id) : attribute.id != null) return false;
        if (organizationId != null ? !organizationId.equals(attribute.organizationId) : attribute.organizationId != null)
            return false;
        if (trafficTypeId != null ? !trafficTypeId.equals(attribute.trafficTypeId) : attribute.trafficTypeId != null)
            return false;
        if (displayName != null ? !displayName.equals(attribute.displayName) : attribute.displayName != null)
            return false;
        if (description != null ? !description.equals(attribute.description) : attribute.description != null)
            return false;
        return dataType != null ? dataType.equals(attribute.dataType) : attribute.dataType == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (organizationId != null ? organizationId.hashCode() : 0);
        result = 31 * result + (trafficTypeId != null ? trafficTypeId.hashCode() : 0);
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (dataType != null ? dataType.hashCode() : 0);
        result = 31 * result + (searchable ? 1 : 0);
        return result;
    }
}
