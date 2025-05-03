package com.miprojecto.esp32.api.conexionesp32.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class DataRequest {
    private String Estado;

    public String getEstado() {
        return this.Estado;
    }
}
