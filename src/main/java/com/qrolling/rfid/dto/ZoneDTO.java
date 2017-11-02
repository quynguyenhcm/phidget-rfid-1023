package com.qrolling.rfid.dto;

/**
 * @author Quy Nguyen (nguyenledinhquy@gmail.com) on 10/31/17
 */

public class ZoneDTO {
    String code;

    String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static final class Builder {
        String code;
        String description;

        private Builder() {
        }

        public static Builder aZoneDTO() {
            return new Builder();
        }

        public Builder withCode(String code) {
            this.code = code;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public ZoneDTO build() {
            ZoneDTO zoneDTO = new ZoneDTO();
            zoneDTO.setCode(code);
            zoneDTO.setDescription(description);
            return zoneDTO;
        }
    }
}
