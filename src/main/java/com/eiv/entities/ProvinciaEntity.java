package com.eiv.entities;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.eiv.converters.RegionConverter;
import com.eiv.enums.RegionEnum;

@Entity
@Table(name = "provincias")
public class ProvinciaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "gen_provincias")
    @TableGenerator(
            table = "SEQUENCE_TABLE", name = "gen_provincias", 
            pkColumnName = "seq_name", valueColumnName = "seq_value", 
            pkColumnValue = "provincia_seq",
            allocationSize = 1, initialValue = 1)
    @Column(name = "id_provincia", nullable = false)
    private Long id;
    
    @Column(name = "nombre", nullable = false, unique = true, length = 400)
    private String nombre;
    
    @Convert(converter = RegionConverter.class)
    @Column(name = "region", nullable = false, length = 3)
    private RegionEnum region;
    
    public ProvinciaEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public RegionEnum getRegion() {
        return region;
    }

    public void setRegion(RegionEnum region) {
        this.region = region;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ProvinciaEntity other = (ProvinciaEntity) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProvinciaEntity [id=" + id + ", nombre=" + nombre + "]";
    }
}
